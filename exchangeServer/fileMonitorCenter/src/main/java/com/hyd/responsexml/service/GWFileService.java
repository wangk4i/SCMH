package com.hyd.responsexml.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.hyd.responsexml.util.FileUtils;
import com.hyd.responsexml.util.RedisUtils;
import com.hyd.responsexml.vo.BatchMsgVO;
import com.hyd.responsexml.vo.MessageDO;
import com.hyd.responsexml.vo.MessageVO;
import org.apache.commons.collections4.map.LRUMap;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by xieshuai on 2020/6/19 上午 10:29
 * 响应xml返回值到cdc响应服务器
 */
@Service
public class GWFileService {

    @Autowired
    private RedisUtils redisUtils;

    @Value("${config.redis_zset_key}")
    private String FILE_ZSET_KEY;
    @Value("${config.max_file_count}")
    private Integer MAX_FILE_COUNT;
    /**
     * 待确认文件夹路径
     */
    @Value("${config.unconfirm-folder}")
    private String unconfirmPath;

    @Value("${config.confirm-folder}")
    private String confirmPath;

//    /**
//     * redis延迟消息通道名
//     */
//    @Value("${config.redis_channel}")
//    private String USER_CHANNEL;

    private Logger logger = LoggerFactory.getLogger(GWFileService.class);

    //private LRUMap<String, String> batchCacheMap = new LRUMap<>(2000);

    private static List<BatchMsgVO> batchCache=new ArrayList<>(2000);

//    @Autowired
//    private DelayingQueueService delayingQueueService;


    /**
     * 第一步， 从Redis中查询0，20条数据（小于20条数据时查询所有）
     * 第二步， 根据查询到的文件名， 查询数据并返回到请求接口
     * @return
     */
    public MessageVO buildFeedbackFiles(){
        //设置返回值变量
        MessageVO result = new MessageVO();
        Set<String> fileNames = redisUtils.getZSetByInterval(FILE_ZSET_KEY, 0, MAX_FILE_COUNT);
        if(fileNames.isEmpty()){
            return result;
        }
        //删除0.MAX_FILE_COUNT 区间数量的fileName
        redisUtils.delzSet(FILE_ZSET_KEY, 0, MAX_FILE_COUNT);
        String uuid = UUID.randomUUID().toString();
        result.setUuid(uuid);

        Set<String> fileDataList = new HashSet<>();
        //缓存
        BatchMsgVO batchItem=new BatchMsgVO();
        batchItem.setBatchID(uuid);
        batchItem.setCreateTime(LocalDateTime.now());
        batchItem.setFiles(fileNames.stream().collect(Collectors.toList()));
        batchCache.add(batchItem);
        //获取文件流
        for(String fileName: fileNames){
            Map<String, String> map =  new HashMap<>();
            File file = new File(unconfirmPath+ fileName+ ".txt");
            map.put("fileName", fileName);
            map.put("fileSteam", FileUtils.readToString(file));
            fileDataList.add(JSON.toJSONString(map));
        }
        //设置响应流和uuid
        result.setMessageSet(fileDataList);
        //batchCacheMap.put(uuid, JSON.toJSONString(fileNames));

//        //设置异步定时器操作，从删除fileName开始计时，五分钟后重新将数据fileNames
//        ScheduledExecutorService service = new ScheduledThreadPoolExecutor(1,
//                new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
//        service.schedule(() -> {
//            //异步超时方法
//            this.timeOut(uuid);
//        }, 1000*60*5, TimeUnit.MILLISECONDS);

        //将当前ID放入redis实现的延迟消息队列中 延迟时间五分钟
        //this.sendMessage(uuid, 5*60);
        return result;
    }

    /**
     * 第三步， 收到二次确认接口，调用该接口后，将文件从待确认文件夹移入已确认文件夹。
     * @param batchid
     */
    @Async
    public void confirmBatchFiles(String batchid){
        if(Strings.isEmpty(batchid)) return;
        //获取指定文件
        Optional<BatchMsgVO> item=batchCache.stream().filter(m->m.getBatchID().equals(batchid)).findFirst();
        if(!item.isPresent()){
            return;
        }
        BatchMsgVO current=item.get();
        batchCache.remove(current);
        if(current.getFiles()==null || current.getFiles().isEmpty()) return;
        //移入已确认文件夹
        for(String fileName: current.getFiles()){
            FileUtils.move(unconfirmPath+ fileName +".xml", confirmPath+fileName+".xml");
            FileUtils.move(unconfirmPath+ fileName +".txt", confirmPath+fileName+".txt");
        }

    }

    /**
    * 过期处理
    *
    * */
    @Scheduled(fixedDelay = 120000)
    public void  overdueCenter(){
        LocalDateTime timeoutTime=LocalDateTime.now().minusMinutes(5);
        List<BatchMsgVO> retryList=batchCache.stream().filter(m->m.getCreateTime().isBefore(timeoutTime))
                                    .collect(Collectors.toList());
        if(retryList==null || retryList.isEmpty()){
            return;
        }
        retryList.forEach(item->{
            batchCache.remove(item);
            if(item.getFiles()==null || item.getFiles().isEmpty()){
                return;
            }
            item.getFiles().forEach(fileid->{
                redisUtils.addzSet(FILE_ZSET_KEY, fileid);
            });
        });
    }

//    /**
//     * 超时补偿机制 当从redis中删除这些文件名开始，五分钟后从cache中获取文件名，重新入队
//     * @param batchid
//     */
//    private void timeOut(String batchid){
//        //由于超过五分钟没有收到确认消息,从reqCache中获取这些数据的ID，重新放入redis中
//        Set fileNames = JSONArray.parseObject(batchCacheMap.get(batchid), Set.class);
//        //reqCache已经没有批次号，说明已被手动确认且移除，直接return;
//        if(fileNames==null) {
//            return;
//        }
//        if(fileNames.isEmpty()) {
//            batchCacheMap.remove(batchid);
//            return;
//        }
//        //reqCache中这些文件id还存在，说明五分钟内未收到确认消息，redis重新入队且删除reqCache
//        fileNames.forEach(fileName->redisUtils.addzSet(FILE_ZSET_KEY, (String)fileName));
//        //从reqCache中移除当前批次号
//        batchCacheMap.remove(batchid);
//    }

//    /**
//     *
//     * Redis超时队列， 放入UUID 和超时时间到Redis队列中
//     * @param messageContent
//     */
//    public void sendMessage(String messageContent, long delay) {
//        try {
//            if (messageContent != null) {
//                String seqId = UUID.randomUUID().toString();
//                MessageDO message = new MessageDO();
//                //时间戳默认为毫秒 延迟5s即为 5*1000
//                long time = System.currentTimeMillis();
//                LocalDateTime dateTime = Instant.ofEpochMilli(time).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
//                message.setDelayTime(time + (delay * 1000));
//                message.setCreateTime(dateTime);
//                message.setBody(messageContent);
//                message.setId(seqId);
//                message.setChannel(USER_CHANNEL);
//                delayingQueueService.push(message);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    /**
//     * 定时消费队列中的数据  间隔1/s一次
//     * zset会对score进行排序 让最早消费的数据位于最前
//     * 拿最前的数据跟当前时间比较 时间到了则消费
//     */
//    @Scheduled(cron = "*/1 * * * * *")
//    public void consumer(){
//        List<MessageDO> msgList = delayingQueueService.pull();
//        if (null != msgList) {
//            long current = System.currentTimeMillis();
//            msgList.stream().forEach(msg -> {
//                // 已超时的消息拿出来消费
//                if (current >= msg.getDelayTime()) {
//                    try {
//                        logger.info("消费消息存入UUID：{}:消息创建时间：{},消费时间：{}", msg.getBody(), msg.getCreateTime(), LocalDateTime.now());
//                        //调用超时方法
//                        timeOut(msg.getBody());
//                    } catch (Exception e){
//                        logger.info("超时消息消费异常{}", e);
//                    }
//                    //移除消息
//                    delayingQueueService.remove(msg);
//                }
//            });
//        }
//    }

}

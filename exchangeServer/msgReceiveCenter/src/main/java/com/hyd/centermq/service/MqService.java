package com.hyd.centermq.service;

import com.hyd.centermq.client.QueueConfig;
import com.hyd.centermq.client.QueueItem;
import com.hyd.centermq.domain.enumtion.ExchangeType;
import com.hyd.centermq.domain.info.MessageInfo;
import com.hyd.centermq.utils.FileUtils;
import com.alibaba.fastjson.JSONArray;
import com.hyd.centermq.utils.RabbitUtils;
import com.hyd.centermq.utils.XmlUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.concurrent.TimeoutException;


/**
 * Created by xieshuai on 2020/5/29 15:03
 */
@Slf4j
@Service
public class MqService{

    @Autowired
    private RabbitUtils rabbitClient;

    @Value("${sysconfig.rabbit.maxwait}")
    private Integer maxwait;

    @Value("${sysconfig.rabbit.backupfolder}")
    private String back_folder;

    @Value("${sysconfig.rabbit.organcode}")
    private String organ_code;

    @Value("${queuefile}")
    private String queueFile;

    /**
     * 处理同步消息
     */
    @Async
    public void syncMessage(MessageInfo info){
        //消息成功写入Rabbit标识
        //TODO 2020-7-10由于当前RabbitMQ队列是延迟加载，在发送消息时，若队列不存在，再创建队列
        // 所以在系统第一次初始化不发送直接查询队列数量会报错，暂时不走查询逻辑。
//        boolean flag = true;
//        try {
////            if(this.queryRabbitMQCount(info)>maxwait){
////                flag = false;
////            }else {
//                //当前消息未被接收且队列空闲，直接写入RabbitMQ
//                this.sendRabbitMQ(info);
//            //}
//        }catch (Exception e){
//            flag = false;
//            e.printStackTrace();
//        }finally {
//            if(!flag){
//                log.info("Rabbit服务不可用或超出阈值,消息持久化到文件中");
//                this.writeFile(info);
//            }
//        }
        this.sendRabbitMQ(info);
    }

    /**
     * 根据队列名，交换机名，交换类型名称查询RabbitMQ现存消息数量
     * @param queueName    队列名称
     * @param exchangeType 交换机类型
     * @return
     * @throws IOException
     * @throws TimeoutException
     */
    protected Integer queryRabbitMQCount(String queueName, ExchangeType exchangeType) throws IOException, TimeoutException {
        return rabbitClient.getQueueCount(queueName, exchangeType);
    }

    /**
     * 根据 分类查询 RabbitMQ现存消息数量
     * @param info
     * @return
     * @throws IOException
     * @throws TimeoutException
     */
    protected Integer queryRabbitMQCount(MessageInfo info) throws IOException, TimeoutException {
        //获取队列名称
        String queue_name = null;

        //获取队列名称
        QueueConfig queueConfig = (QueueConfig) XmlUtils.convertXmlFileToObject(QueueConfig.class, queueFile);
        for (QueueItem config : queueConfig.getConfigs()) {
            if(info.getMsgtype().equals(config.getMsgType())){
                queue_name = config.getQueueName();
            }
        }

        //查询指定数量
        return rabbitClient.getQueueCount(queue_name, ExchangeType.DIRECT);
    }

    /**
     * 发送消息到RabbitMQ
     * @return
     */
    protected void sendRabbitMQ(MessageInfo info){
            String queue_name = null;
            //获取队列名称
            QueueConfig queueConfig = (QueueConfig) XmlUtils.convertXmlFileToObject(QueueConfig.class, queueFile);
            for (QueueItem config : queueConfig.getConfigs()) {
                if(info.getMsgtype().equals(config.getMsgType())){
                    queue_name = config.getQueueName();
                    break;
                }
            }
            //获取备份文件名
            StringBuffer sb = new StringBuffer(back_folder);
            sb.append("\\");
            //获得文件
            String prefix = queue_name;
            //获取队列名称
            sb.append("mq_");
            sb.append(prefix);
            sb.append("_");
            sb.append(organ_code);
            sb.append(info.getId());
            sb.append(".json");

            rabbitClient.convertAndSend(queue_name, JSONArray.toJSONString(info), sb.toString());
    }


    /**
     * 读取文件中的同步内容，发送到RabbitMQ
     * 文件读写规则
     *
     */
    public Boolean readFileByRabbitMQ(File file, String queueName){
        try {
            //判断当前RabbitMQ是否处于活动状态且 RabbitMQ 的待处理消息未超过阈值
//            if(this.queryRabbitMQCount( queueName, ExchangeType.DIRECT)>maxwait){
//                return false;
//            }

            String jsonStr =  FileUtils.readJsonFile(file);
            boolean b = rabbitClient.convertAndSend(queueName, jsonStr);
            //发送成功后,删除当前文件
            if(b){
                FileUtils.delectFile(file);
            }
            return b;
        }catch (Exception e){
            log.info("Rabbit服务不可用或消息发送失败");
            return false;
        }

    }

    /**
     * 将同步内容写入文件
     * @return
     */
    @Async
    protected boolean writeFile(MessageInfo info){
        //创建文件名（业务分类-进行数据交换的机构代码-系统当前时间）
        String content = JSONArray.toJSONString(info);
        StringBuffer sb = new StringBuffer(back_folder);
        sb.append("\\");
        //获得文件前缀
        String prefix = null;
        //获取队列名称
        QueueConfig queueConfig = (QueueConfig) XmlUtils.convertXmlFileToObject(QueueConfig.class, queueFile);
        for (QueueItem config : queueConfig.getConfigs()) {
            if(info.getMsgtype().equals(config.getMsgType())){
                prefix = config.getQueueName();
            }
        }
        sb.append("mq_");
        sb.append(prefix);
        sb.append(organ_code);
        sb.append(info.getId());
        sb.append(".json");
        File file = new File(sb.toString());
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            //
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(content);
            bw.close();
        }catch (IOException e){
            e.printStackTrace();
            log.info("消息写入文件失败:"+info.getId());
            return false;
        }
        return true;
    }

}

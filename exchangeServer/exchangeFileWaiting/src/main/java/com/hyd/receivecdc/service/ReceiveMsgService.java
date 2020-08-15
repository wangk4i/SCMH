package com.hyd.receivecdc.service;

import com.alibaba.fastjson.JSONObject;
import com.hyd.receivecdc.util.FileUtils;
import com.hyd.receivecdc.util.HttpClientUtils;
import com.hyd.receivecdc.util.RabbitUtils;
import com.hyd.receivecdc.vo.MessageVO;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

/**
 * Created by xieshuai on 2020/7/1 上午 10:56
 */
@Service
public class ReceiveMsgService {

    private Logger log = LoggerFactory.getLogger(ReceiveMsgService.class);

    @Value("${config.pollFileUri}")
    private String pollFileUri;

    @Value("${config.confirmFileUri}")
    private String confirmFileUri;

    @Value("${config.backupFolder}")
    private String backupFolder;

    @Value("${config.rabbit.queueName}")
    private String queueName;

    @Value("${config.waitingFolder}")
    private String waitingPath;

    @Value("${config.receivedFolder}")
    private String receivedPath;



    @Autowired
    private RabbitUtils rabbitUtils;

    /**
     * 轮询返回文件
     * 应答确认批次信息
     * 异步发送文件ID进MQ
     */
    @Scheduled(fixedDelay = 2000,initialDelay=1000)
    public void pollingFiles(){
        try {
            //发送Http请求获取所有的xml返回值信息
            String returnString = HttpClientUtils.post(pollFileUri);
            if(null==returnString|| "".equals(returnString)){
                return;
            }
            //序列化为消息对象
            MessageVO msgItem = JSONObject.parseObject(returnString, MessageVO.class);
            if(Strings.isEmpty(msgItem.getUuid()) || msgItem.getMessageSet()==null || msgItem.getMessageSet().size()==0) {
                return;
            }
            List<String> fileIDs=new ArrayList<>(msgItem.getMessageSet().size());
            for(String lineString : msgItem.getMessageSet()){
                Map map = JSONObject.parseObject(lineString, Map.class);
                String fileName = (String) map.get("fileName");
                String fileContext=(String) map.get("fileSteam");
                //设置文件名称
                String fileStoreName = waitingPath + fileName + ".txt";
                String fileWaitingName = waitingPath + fileName + ".json";
                //如果文件存在 则放弃
                if(Files.exists(Paths.get(fileStoreName))){
                    continue;
                }
                //保存结果消息
                writeReceiveFile(fileContext, fileStoreName);
                //如果文件已移除 则放弃
                if(Files.exists(Paths.get(fileWaitingName))){
                    continue;
                }
                //转移文件
                FileUtils.move(waitingPath+fileName+".xml", receivedPath+fileName+".xml");
                FileUtils.move(waitingPath+fileName+".json", receivedPath+fileName+".json");
                fileIDs.add(fileName);
            }
            if(fileIDs.size()>0) pushFileIdInQueue(fileIDs);
            //再次发送Http请求到省疾控交换环境，表示消息已成功处理
            Map<String, String> dataMap = new HashMap<>();
            dataMap.put("uuid", msgItem.getUuid());
            HttpClientUtils.ajaxPost(confirmFileUri, dataMap);
        }catch (Exception e){
            //e.printStackTrace();
            log.warn("", e);
        }
    }


    /**
     * 扫描文件ID入队失败文件夹，重新发送到队列
     */
    @Scheduled(fixedDelay = 5*60*1000,initialDelay=10000)
    public void scanErrFileIds(){
        //1.扫描指定文件夹。获取所有Xml文件
        List<File> files = FileUtils.traverseFolder(backupFolder);
        if(null==files||files.size()==0){
            return;
        }
        //2.遍历所有文件夹，将文件解析为流
        for(File file: files){
            //获取file内容
            String fileContext = FileUtils.fileToString(file);
            FileUtils.delectFile(file);
            if(Strings.isEmpty(fileContext)) continue;
            String[] strArr=fileContext.split("\n");
            if(strArr.length==0) continue;
            List<String> fileIDList=Arrays.stream(strArr)
                                        .filter(f->!Strings.isEmpty(f))
                                        .collect(Collectors.toList());

            if(fileIDList.size()>0) pushFileIdInQueue(fileIDList);
        }
    }


    /**
     * 将同步内容写入文件
     * @return
     */
    @Async
    void writeReceiveFile(String fileStream, String filePath){
        //创建文件名（业务分类-进行数据交换的机构代码-系统当前时间）
        File file = new File(filePath);
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(fileStream);
            bw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Async
    void pushFileIdInQueue(List<String> fileIDs){
        for(String fileIDNum : fileIDs) {
            try {
                //发送到RabbitMQ
                rabbitUtils.convertAndSend(queueName, fileIDNum, backupFolder);
            } catch (IOException | TimeoutException ignored) {
                writeErrorFileID(fileIDNum);
            }
        }
    }

    void writeErrorFileID(String fileID){
        //创建文件名（业务分类-进行数据交换的机构代码-系统当前时间）
        DateTimeFormatter fullFormat = DateTimeFormatter.ofPattern("yyMMddHHmmss");
        String fileName=String.format("faild{}.log",LocalDateTime.now().format(fullFormat));
        String fileFullName=backupFolder+fileName;
        File file = new File(fileFullName);
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(),true);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(fileID);
            bw.write("\n");
            bw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

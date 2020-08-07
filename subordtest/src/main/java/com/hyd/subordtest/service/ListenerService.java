package com.hyd.subordtest.service;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.JsonParser;
import com.hyd.subordtest.domain.enumtion.MqEnum;
import com.hyd.subordtest.utils.FileUtils;
import com.hyd.subordtest.utils.RabbitUtils;
import com.hyd.subordtest.utils.TextFileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Hashtable;

/**
 * Created by xieshuai on 2020/6/12 17:05
 */

@Slf4j
@Component
public class ListenerService {

    @Value("${config.build}")
    private String listenerFolder;

    @Value("${config.queue.xmlfile}")
    private String XMLID_SENDING;

    @Autowired
    private RabbitUtils rabbitUtils;


    /**
     * 每30/s执行一次当前任务，在上一次任务执行完成之后
     * 扫描指定文件夹，遍历文件夹内所有xml,解析并发送到RabbitMQ
     * 每次执行完成，删除指定文件。
     * 发送到RabbitMQ失败，会异步重新写入指定文件夹
     */
    /*@Scheduled(fixedDelay = 30000)
    public void definiteTimeSendMessageByFolder(){
        //1.扫描指定文件夹。获取所有Xml文件
        List<File> files = FileUtils.traverseFolder(listenerFolder);
        if(null==files||files.size()==0){
            return;
        }
        //2.遍历所有文件夹，将文件解析为流
        for(int i=0; i<files.size(); i++){
            File file = files.get(i);
            String fileName = file.getName();
            *//**
             * 2020-7-3 09:46:22 由于当前扫描文件夹发送到Rabbit时采用异步发送消息方式
             * 无论消息发送成功 or 失败，执行sendXmlToRabbit方法前会删除当前文件
             * 执行sendXmlToRabbit采用异步方式，调用此方法时，文件监听流程默认已完成
             * 发送方内部实现ACK机制，发送失败或异常时，会重新写入当前文件。
             * 所以当前方法无法保证消息的唯一性*//*

            String typeName = file.getName().substring(0, file.getName().indexOf("_"));
            String xmlStream;
            String xmlId;
            SAXReader saxReader = new SAXReader();
            Document document;
            try {
                document = saxReader.read(file);
                xmlStream = document.asXML();
                Element root = document.getRootElement();
                //获得Header节点
                Element headerElement = root.element("Header");
                //获得XmlID
                Element xmlidElement = headerElement.element("DocumentId");
                xmlId = xmlidElement.getText();
            }catch (Exception e){
                log.info("解析XML时出现异常,文件名:{},时间:{}", fileName, StringUtils.getDateStr());
                continue;
            }

            //删除当前File 由于发送到RabbitMQ时采用异步ACK，发送失败时重新写入文件，所有删除要在发送之前
            FileUtils.delectFile(file);
            //发送到RabbitMQ
           // sendXmlToRabbit(xmlId, xmlStream ,typeName, fileName);

        }
    }*/


    public void sendXml(File file){
        String fileName = file.getName();
        String xmlId = fileName.substring(0, fileName.lastIndexOf(".xml"));
        String msgjson = FileUtils.readJsonFile(listenerFolder + "\\" + xmlId + ".json");
        Integer type = new JsonParser().parse(msgjson).getAsJsonObject().get("msgtype").getAsInt();
        String xmlStream = TextFileUtils.readXmlFile(file);

        // 删除当前File 由于发送到RabbitMQ时采用异步ACK，发送失败时重新写入文件，所有删除要在发送之前
        FileUtils.delectFile(file);
        // 发送Xml流 到RabbitMQ
        sendXmlToRabbit(xmlId, xmlStream ,type, fileName);
        // 发送文件名id
        rabbitUtils.sendXmlId(XMLID_SENDING, xmlId);
    }


    /**
     * 将流发送到Rabbit
     */
    @Async
    public void sendXmlToRabbit(String xmlId, String sendSteam, Integer typeName, String fileName){
        //判断当前操作的typename
        switch (typeName){
            case 1:
                sendDocumentXmlToRabbit(xmlId, sendSteam, fileName);
                break;
            case 2:
                sendReportcardToRabbit(xmlId, sendSteam, fileName);
                break;
            case 3:
                sendDischargeToRabbit(xmlId, sendSteam, fileName);
                break;
            case 4:
                sendFollowupXmlToRabbit(xmlId, sendSteam, fileName);
                break;
            case 5:
                sendEmergencyXmlToRabbit(xmlId, sendSteam, fileName);
                break;
            default:
                break;
        }
    }

    @Async
    public void sendDocumentXmlToRabbit(String xmlId, String sendStream, String fileName){
        Hashtable<String, String> map = new Hashtable<>();
        map.put("xmlId", xmlId);
        map.put("xmlStream",sendStream);
        //map转换为流
        String jsonStr = JSONArray.toJSONString(map);
        // 发送xml流
        rabbitUtils.convertAndSend(MqEnum.MQ_PATINFO, jsonStr, fileName);
    }
    @Async
    public void sendReportcardToRabbit(String xmlId,  String sendStream, String fileName){
        Hashtable<String, String> map = new Hashtable<>();
        map.put("xmlId", xmlId);
        map.put("xmlStream",sendStream);
        String jsonStr = JSONArray.toJSONString(map);
        rabbitUtils.convertAndSend(MqEnum.MQ_REPORT, jsonStr, fileName);
    }
    @Async
    public void sendDischargeToRabbit(String xmlId,  String sendStream, String fileName){
        Hashtable<String, String> map = new Hashtable<>();
        map.put("xmlId", xmlId);
        map.put("xmlStream",sendStream);
        String jsonStr = JSONArray.toJSONString(map);
        rabbitUtils.convertAndSend(MqEnum.MQ_DISCHARGE, jsonStr, fileName);
    }
    @Async
    public void sendFollowupXmlToRabbit(String xmlId,  String sendStream, String fileName){
        Hashtable<String, String> map = new Hashtable<>();
        map.put("xmlId", xmlId);
        map.put("xmlStream",sendStream);
        String jsonStr = JSONArray.toJSONString(map);
        rabbitUtils.convertAndSend(MqEnum.MQ_FOLLOWUP, jsonStr, fileName);
    }
    @Async
    public void sendEmergencyXmlToRabbit(String xmlId,  String sendStream, String fileName){
        Hashtable<String, String> map = new Hashtable<>();
        map.put("xmlId", xmlId);
        map.put("xmlStream",sendStream);
        String jsonStr = JSONArray.toJSONString(map);
        rabbitUtils.convertAndSend(MqEnum.MQ_EMERGENCY, jsonStr, fileName);
    }

}

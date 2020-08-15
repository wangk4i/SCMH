package com.hyd.cdcserver.listener;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import com.hyd.cdcserver.util.FileUtils;
import com.hyd.cdcserver.util.HttpClientUtils;
import com.hyd.cdcserver.util.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import java.io.*;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xieshuai on 2020/6/4 11:16
 */
@Slf4j
@Component
public class RabbitListeners {

    @Value("${config.request-url}")
    private String requestUrl;

    @Value("${config.sendXmlinterface}")
    private String sendxmlUrl;

    @Value("${config.build}")
    private String buildPath;

    @Value("${config.sending}")
    private String sendingPath;

    @Value("${config.waiting}")
    private String waitingPath;


    //同步锁
    private final ReentrantLock lock=new ReentrantLock();



    @RabbitListener(queues = "${config.queue.xmlfile}", returnExceptions =  "false", concurrency = "2-10")
    public void getMessage(String xmlId) {
        this.dealAndSend(xmlId);
    }


    /**
     * 异步发送Ajax请求
     * @param url
     * @param jsonStr
     */
    @Async
    public void sendAjax(String url, String jsonStr) throws IOException {
        HttpClientUtils.ajaxPostJson(url, jsonStr);
    }

    public String sendAjax(String url, Map<String, String> dataMap) throws IOException {
       return HttpClientUtils.ajaxPostJson(url, JSON.toJSONString(dataMap));
    }



    @Async
    protected void dealAndSend(String xmlId){
        //移动到02并删除01
        try {
            FileUtils.move(buildPath+"\\"+xmlId+".xml",  sendingPath+"\\"+xmlId+".xml");
            FileUtils.move(buildPath+"\\"+xmlId+".json", sendingPath+"\\"+xmlId+".json");
            String xmlStream = FileUtils.readFile(sendingPath+"\\"+xmlId+".xml");
            Map<String, String> map = new LinkedHashMap<>();
            map.put("xmlId", xmlId);
            map.put("xmlStream",xmlStream);
            String httpResult="";
            try {
                httpResult=sendAjax(sendxmlUrl, map);
            }catch (IOException e){
                e.printStackTrace();
                // 发送失败, 写入日志
                LogUtil.failSendLog(xmlId);
                return;
            }
            if(!"1".equals(httpResult)){
                // 发送失败, 写入日志
                LogUtil.failSendLog(xmlId);
                return;
            }
            // ajax发送成功 移动到03删除02
            FileUtils.move(sendingPath+"\\"+xmlId+".xml", waitingPath+"\\"+xmlId+".xml");
            FileUtils.move(sendingPath+"\\"+xmlId+".json", waitingPath+"\\"+xmlId+".json");
        } catch (Exception e) {
            LogUtil.warn("", e);
        }
    }



    public String readXmlFile(File file){
        SAXReader saxReader = new SAXReader();
        Document document = null;
        try {
            document = saxReader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document.asXML();
    }




}

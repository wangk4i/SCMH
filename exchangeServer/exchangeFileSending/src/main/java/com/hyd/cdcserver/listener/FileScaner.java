package com.hyd.cdcserver.listener;
import com.hyd.cdcserver.util.FileUtils;
import com.hyd.cdcserver.util.RabbitUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by xieshuai on 2020/6/30 下午 3:12
 */
@Slf4j
@Component
public class FileScaner {

    private long day = 60*1000*60*24; // 1天

    @Value("${config.request-url}")
    private String requestUrl;

    @Value("${config.sendXmlinterface}")
    private String sendxmlUrl;

    @Value("${config.sending}")
    private String sendingPath;

    @Value("${config.waiting}")
    private String waitingPath;

    private String logFilePath = sendingPath + "\\log";



    /**
     * http请求失败后的处理
     * 5分钟扫描一次发送失败日志
     */
    @Scheduled(fixedDelay = 5*60*1000)
    public void pollFailXml() throws ParseException {
        List<File> list = FileUtils.traverseFolder(logFilePath);
        if (list == null || list.size() == 0){
            return;
        }
        Collections.sort(list);

        for (File file : list) {
            String logContent = FileUtils.readFile(file);
            String[] failXmlIds = logContent.split("\\n");
            for (String xmlid : failXmlIds) {
                String xmlCreateTime = xmlid.substring(xmlid.lastIndexOf("-"));
                Date createTime = new SimpleDateFormat("yyyyMMddHHmmssSSS").parse(xmlCreateTime);
                if (day < System.currentTimeMillis() - createTime.getTime()) {
                    // 时间过期，不再入队
                    continue;
                }
                // 未过期，重新入队
                RabbitUtils.sendXmlId(xmlid);
            }
            file.delete();
        }

    }






    /*private final BloomFilter<String> bloomFilter = BloomFilter.create(new Funnel<String>() {
        private static final long serialVersionUID = 1L;
        @Override
        public void funnel(String arg0, PrimitiveSink arg1) {
            arg1.putString(arg0, Charsets.UTF_8);
        }
    }, 1024*1024*32);


    @Scheduled(fixedDelay = 30000)
    public void cycleSendFileMessage(){
        log.info("开始扫描文件夹");
        //Map<String, String> map = new HashMap<>();
        Hashtable<String, String> map = new Hashtable<>();
        Thread thread = new Thread();
        thread.setDaemon(true);
        thread.start();
        thread.run();
        //判断省疾控环境是否ping通
        if(HttpClientUtils.testUrlWithTimeOut(requestUrl, 2000)){
            //扫描指定文件夹
            List<File> list = FileUtils.traverseFolder(logFilePath);
            if(null==list||list.size()==0){
                //当前文件夹内无内容,当前线程休眠10min
                log.info("当前文件夹内无内容,线程休眠10min");
                try {
                    Thread.sleep(1000*60*10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return;
            }
            for (File file : list) {
                //获取当前文件名
                String fileName = Arrays.toString(file.getName().split("."));
                //判断当前文件是否 已发送

                //重新发送Http请求
                map.put("xmlId", fileName);
                map.put("xmlStream", FileUtils.readFile(file));
                //读取文件内容
                try {
                    sendAjax(sendxmlUrl, JSONObject.toJSONString(map));
                } catch (IOException e) {
                    log.info("Http发送到省疾控失败{}", fileName);
                    continue;
                }
                //删除当前文件
                FileUtils.delectFile(file);
                this.dealWithMessage(fileName);

            }
        }else {
            //当前线程休眠10min
            log.info("省疾控网络服务不可用，线程休眠10min");
            try {
                Thread.sleep(1000*60*10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.gc();
    }


    @Async
    public void sendAjax(String url, String jsonStr) throws IOException {
        HttpClientUtils.ajaxPostJson(url, jsonStr);
    }

    @Async
    protected void dealWithMessage(String xmlId){
        //ajax发送成功 移动到03删除02
        FileUtils.move(sendingPath+"\\"+xmlId+".xml", waitingPath+"\\"+xmlId+".xml");
        FileUtils.move(sendingPath+"\\"+xmlId+".json", waitingPath+"\\"+xmlId+".json");
    }*/



}

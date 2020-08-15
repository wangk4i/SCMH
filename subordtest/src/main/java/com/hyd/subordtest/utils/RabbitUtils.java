package com.hyd.subordtest.utils;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Created by xieshuai on 2020/6/11 16:46
 */
@Slf4j
@Component
public class RabbitUtils {

    @Value("${config.build}")
    private String back_folder;

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private Integer port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.virtual-host}")
    private String virtual_host;



    public void sendXmlId(String queueName, String fileId){
        // 创建连接
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(username);
        factory.setPassword(password);
        factory.setVirtualHost(virtual_host);
        factory.setHost(host);
        factory.setPort(port);

        Connection  conn = null;
        try {
            conn = factory.newConnection();
        } catch (IOException | TimeoutException e) {
            log.info("当前Rabbit服务不可用");
            writeLog(fileId);
            return;
        }

        // 创建信道
        Channel channel = null;
        if(null==conn){
            return;
        }
        try {
            channel = conn.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 声明队列
        try {
            assert channel != null;
            channel.queueDeclare(queueName, true, false, false, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 开启发送方确认模式
        try {
            channel.confirmSelect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            channel.basicPublish("", queueName, null, fileId.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //异步监听确认和未确认的消息
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                writeLog(fileId);
                log.info("消息数量{}未能成功接收,将写入文件夹",deliveryTag);
            }
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                log.info("消息数量{}成功接收,标识{}", deliveryTag, multiple);
            }
        });
    }



    @Async
    protected boolean writeLog(String fileId){
        //创建文件名（业务分类-进行数据交换的机构代码-系统当前时间）

        String d = new SimpleDateFormat("yyyyMMddHH").format(new Date());

        String logPath = back_folder + "\\log";
        File LogFolder = new File(logPath);
        if (!LogFolder.exists()){
            LogFolder.mkdir();
        }

        StringBuffer sb = new StringBuffer();
        String t = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        sb.append(t).append("\t: ").append(fileId).append("\n");
        String content = sb.toString();

        File file = new File(LogFolder + "\\Mq_"+ d+".log");
        RandomAccessFile mm = null;
        boolean flag = false;

        FileOutputStream o = null;
        try {
            o = new FileOutputStream(file,true);
            o.write(content.getBytes("utf-8"));
            o.close();
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
            log.info("消息写入文件失败:");
        } finally {
            if (mm != null) {
                try {
                    mm.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }
}

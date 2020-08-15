package com.hyd.subordmq.utils;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Created by xieshuai on 2020/6/11 16:46
 */
@Slf4j
@Component
public class RabbitUtils {

    private static String back_folder;

    private static String host;

    private static Integer port;

    private static String username;

    private static String password;

    private static String virtual_host;

    private static String MQ_SENDING;

    @Value("${config.build}")
    public void setBack_folder(String back_folder) {
        RabbitUtils.back_folder = back_folder;
    }
    @Value("${spring.rabbitmq.host}")
    public void setHost(String host) {
        RabbitUtils.host = host;
    }
    @Value("${spring.rabbitmq.port}")
    public void setPort(Integer port) {
        RabbitUtils.port = port;
    }
    @Value("${spring.rabbitmq.username}")
    public void setUsername(String username) {
        RabbitUtils.username = username;
    }
    @Value("${spring.rabbitmq.password}")
    public void setPassword(String password) {
        RabbitUtils.password = password;
    }
    @Value("${spring.rabbitmq.virtual-host}")
    public void setVirtual_host(String virtual_host) {
        RabbitUtils.virtual_host = virtual_host;
    }
    @Value("${config.queue.xmlfile}")
    public void setMqSending(String mqSending) {
        MQ_SENDING = mqSending;
    }


    public void convertAndSend(String queueName, String message){
        // 创建连接
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(username);
        factory.setPassword(password);
        factory.setVirtualHost(virtual_host);
        factory.setHost(host);
        factory.setPort(port);

        Connection  conn;
        try {
            conn = factory.newConnection();
        } catch (IOException | TimeoutException e) {
            log.info("当前Rabbit服务不可用");
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
            channel.basicPublish("", queueName, null, message.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //异步监听确认和未确认的消息
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                //序列化为
                Map map = JSON.parseObject(message, Map.class);
                log.info("消息数量{}未能成功接收,将写入文件夹",deliveryTag);
            }
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                log.info("消息数量{}成功接收,标识{}", deliveryTag, multiple);
            }
        });
    }

    public static void sendXmlId(String xmlId){
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
            LogUtil.sendRabbitErr(xmlId, back_folder+"\\log");
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
            channel.queueDeclare(MQ_SENDING, true, false, false, null);
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
            channel.basicPublish("", MQ_SENDING, null, xmlId.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //异步监听确认和未确认的消息
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                LogUtil.sendRabbitErr(xmlId, back_folder+"\\log");
                log.info("消息数量{}未能成功接收,将写入文件夹",deliveryTag);
            }
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                log.info("消息数量{}成功接收,标识{}", deliveryTag, multiple);
            }
        });
    }


}

package com.hyd.centermq.utils;

import com.alibaba.fastjson.JSON;
import com.hyd.centermq.domain.enumtion.ExchangeType;
import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Created by xieshuai on 2020/6/11 16:46
 */
@Slf4j
@Component
public class RabbitUtils {

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



    /**
     * 查询队列中的消息数量
     * @param queue 队列名字
     * @param exchangeType 交换机类型 fanout 或 direct
     * @return
     */
    public Integer getQueueCount(String queue, ExchangeType exchangeType) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setPort(port);
        factory.setUsername(username);
        factory.setPassword(password);
        factory.setVirtualHost(virtual_host);
        //创建连接
        Connection conn;
        // 创建通道
        Channel chan;
        Integer messageCount;
        // 设置消息交换机
        conn = factory.newConnection();
        chan = conn.createChannel();
        chan.exchangeDeclare(null, exchangeType.value(), true, false, null);
        AMQP.Queue.DeclareOk declareOk = chan.queueDeclarePassive(queue);
        //获取队列中的消息个数
        messageCount = declareOk.getMessageCount();
        // 关闭通道和连接
        chan.close();
        conn.close();

        return messageCount;
    }

    public void convertAndSendByAuto(String queueName, String message) throws IOException{
        // 创建连接
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(username);
        factory.setPassword(password);
        factory.setVirtualHost(virtual_host);
        factory.setHost(host);
        factory.setPort(port);
        Connection conn = null;
        try {
            conn = factory.newConnection();
        } catch (TimeoutException e) {
            e.printStackTrace();
            log.info("rabbitmq服务不可用");
        }
        // 创建信道
        Channel channel = conn.createChannel();
        // 声明队列
        channel.queueDeclare(queueName, true, false, false, null);
        // 开启发送方确认模式
        channel.confirmSelect();

        channel.basicPublish("", queueName, null, message.getBytes("UTF-8"));
    }

    /**
     * 发送指定消息到交换机,并手动ACK
     * @param queueName
     * @param message
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    public boolean convertAndSend(String queueName, String message) throws IOException, InterruptedException {

        // 创建连接
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(username);
        factory.setPassword(password);
        factory.setVirtualHost(virtual_host);
        factory.setHost(host);
        factory.setPort(port);
        Connection conn;
        try {
            conn = factory.newConnection();
        } catch (TimeoutException e) {
            e.printStackTrace();
            log.info("rabbitmq服务不可用");
            return false;
        }
        // 创建信道
        Channel channel = conn.createChannel();
        // 声明队列
        channel.queueDeclare(queueName, true, false, false, null);
        // 开启发送方确认模式
        channel.confirmSelect();

        channel.basicPublish("", queueName, null, message.getBytes(StandardCharsets.UTF_8));
        if (channel.waitForConfirms()) {
            log.info("消息数量:1 成功接收,标识: true");
            return true;
        }
        return false;
    }


    /**
     * 发送消息到指定队列，并异步ACK，当消息发送失败时，将消息重写写入指定文件URL
     * @param queueName 队列名称
     * @param message 消息体
     * @param fileName 备份文件名
     */
    public void convertAndSend(String queueName, String message, String fileName){
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
            writeFile(message, fileName);
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
                writeFile((String)map.get("xmlStream"), fileName);
                log.info("消息数量{}未能成功接收,将写入文件夹",deliveryTag);
            }
            @Override
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                log.info("消息数量{}成功接收,标识{}", deliveryTag, multiple);
            }
        });
    }

    /**
     * 将同步内容写入文件
     * @return
     */
    @Async
    protected boolean writeFile(String fileStream, String fileName){
        File file = new File(fileName);
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
            log.info("消息写入文件失败:");
            return false;
        }
        return true;
    }
}

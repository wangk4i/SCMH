package com.hyd.receivecdc.util;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeoutException;

/**
 * Created by xieshuai on 2020/7/1 上午 11:56
 */
@Component
public class RabbitUtils {

    private Logger log = LoggerFactory.getLogger(RabbitUtils.class);


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
     * 判断RabbitMQ 是否在线
     * @return
     */
    public static boolean isRabbitOnline(){
        return false;
    }


    /**
     * 读取RabbitMQ现存消息数量
     * @return
     */
    public Integer queryRabbitMQCount(String exchangeName, String queueName, String exchangeType) throws IOException, TimeoutException {

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
        chan.exchangeDeclare(exchangeName, exchangeType, true, false, null);
        AMQP.Queue.DeclareOk declareOk = chan.queueDeclarePassive(queueName);
        //获取队列中的消息个数
        messageCount = declareOk.getMessageCount();

        // 关闭通道和连接
        chan.close();
        conn.close();


        return messageCount;
    }

    /**
     * 发送消息到队列并自动ACK
     * @param queueName
     * @param message
     * @throws IOException
     */
    public void convertAndSendByAutoACK(String queueName, String message) throws IOException{
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

        channel.basicPublish("", queueName, null, message.getBytes(StandardCharsets.UTF_8));
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
        Connection conn = null;
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
            log.info("消息发送成功");
            return true;
        }
        return false;
    }


    /**
     * 发送消息到指定队列，并异步ACK，当消息发送失败时，将消息重新写入指定文件URL
     * @param queueName 队列名称
     * @param message 消息体
     * @param filePath 备份文件路径
     */
    public void convertAndSend(String queueName, String message, String filePath) throws IOException, TimeoutException {
        // 创建连接
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(username);
        factory.setPassword(password);
        factory.setVirtualHost(virtual_host);
        factory.setHost(host);
        factory.setPort(port);

        Connection conn = factory.newConnection();

        // 创建信道
        Channel channel = null;
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
            channel.basicPublish("", queueName, null, message.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //异步监听确认和未确认的消息
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleNack(long deliveryTag, boolean multiple){
                //序列化为
                writeErrorFileID(filePath,message);
                //log.info("消息数量{}未能成功接收,将写入文件夹",deliveryTag);
            }
            @Override
            public void handleAck(long deliveryTag, boolean multiple){
                //log.info("消息数量{}成功接收,标识{}", deliveryTag, multiple);
            }
        });
    }

    /**
     * 将同步内容写入文件
     * @return
     */
    @Async
    protected boolean writeFile(String fileStream, String filePath){
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
            log.info("消息写入文件失败:");
            return false;
        }
        return true;
    }
    void writeErrorFileID(String fileDir,String fileID){
        //创建文件名（业务分类-进行数据交换的机构代码-系统当前时间）
        DateTimeFormatter fullFormat = DateTimeFormatter.ofPattern("yyMMddHHmmss");
        String fileName=String.format("faild{}.log", LocalDateTime.now().format(fullFormat));
        String fileFullName=fileDir+fileName;
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
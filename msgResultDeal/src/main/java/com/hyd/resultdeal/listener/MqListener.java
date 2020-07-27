package com.hyd.resultdeal.listener;

import org.springframework.stereotype.Component;

/**
 * Created by xieshuai on 2020/7/1 下午 1:40
 */
@Component
public class MqListener {
/*

    @Autowired
    private RespMsgImpl respMsg;

    @RabbitListener(queues = "${config.rabbit.queueName}", returnExceptions = "false", concurrency= "10")
    public void thretMqListener(String message){


        //1.处理返回值消息，序列化为Map
        MessageDO msgDO = JSONObject.parseObject(message, MessageDO.class);

        //2.根据消息内容处理具体业务

        //3.保存至MongoDB中
        respMsg.save(msgDO);
    }
*/

}

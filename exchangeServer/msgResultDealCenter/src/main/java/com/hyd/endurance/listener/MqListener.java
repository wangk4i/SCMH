package com.hyd.endurance.listener;

import com.hyd.endurance.service.FileDealService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xieshuai on 2020/7/1 下午 1:40
 */
@Component
public class MqListener {

    @Autowired
    FileDealService server;

    @RabbitListener(queues = "${config.rabbit.queueName}", returnExceptions = "false", concurrency= "10")
    public void thretMqListener(String xmlId){
        if(Strings.isEmpty(xmlId)) return;
        server.receiveXmlIdDeal(xmlId);
    }
}
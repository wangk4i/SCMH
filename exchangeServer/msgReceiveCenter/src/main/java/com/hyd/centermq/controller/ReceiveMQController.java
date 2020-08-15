package com.hyd.centermq.controller;

import com.hyd.centermq.domain.info.MessageInfo;
import com.hyd.centermq.service.MqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * Created by xieshuai on 2020/5/29 11:48
 * 接收不同服务发来的同步消息
 *
 */
@RestController
public class ReceiveMQController {


    @Autowired
    private MqService mqService;


    /**
     * 接收消息，发送到RabbitMQ或持久化到硬盘中
     * @param info
     * @return
     */
    @RequestMapping("/sendMessage")
    public Integer sendMessage(@RequestBody MessageInfo info){
        mqService.syncMessage(info);
        return 1;
    }

}

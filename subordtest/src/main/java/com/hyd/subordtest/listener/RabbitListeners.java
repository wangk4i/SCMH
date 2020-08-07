package com.hyd.subordtest.listener;

import com.alibaba.fastjson.JSON;
import com.hyd.subordtest.domain.info.MessageInfo;
import com.hyd.subordtest.service.DealCenterService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Created by xieshuai on 2020/6/4 11:16
 *  根据业务监听指定消息队列
 */
@Component
public class RabbitListeners {

    @Autowired
    private DealCenterService dealCenter;

    /**
     * 监听指定消息队列--患者档案管理
     * @param jsonStr
     * @throws InterruptedException
     */
    @RabbitListener(queues = "${config.queue.patinfo}", returnExceptions = "false", concurrency="2-10")
    public void getDocMessage(String jsonStr){
        MessageInfo info =  JSON.parseObject(jsonStr, MessageInfo.class);
        //前往转码并输出 Attribute value must be
        dealCenter.BuildPatInfoMsg(info);
    }

    /**
     * 监听指定消息队列--患者随访管理
     * @param jsonStr
     * @throws InterruptedException
     */
    @RabbitListener(queues = "${config.queue.followup}", returnExceptions = "false", concurrency="2-10")
    public void getFollMessage(String jsonStr){
        MessageInfo info =  JSON.parseObject(jsonStr, MessageInfo.class);
        //前往转码并输出 Attribute value must be
        dealCenter.BuildFollowupMsg(info);
    }

    /**
     * 监听指定消息队列--出院单
     * @param jsonStr
     * @throws InterruptedException
     */
    @RabbitListener(queues = "${config.queue.report}", returnExceptions = "false",  concurrency="2")
    public void getDischMessage(String jsonStr){
        MessageInfo info =  JSON.parseObject(jsonStr, MessageInfo.class);
         //前往转码并输出 Attribute value must be
        dealCenter.BuildLeaveCardMsg(info);
    }

    /**
     * 监听指定消息队列--应急处置
     * @param jsonStr
     * @throws InterruptedException
     */
    @RabbitListener(queues = "${config.queue.leftcard}", returnExceptions = "false", concurrency="2")
    public void getEmerMessage(String jsonStr){
        MessageInfo info =  JSON.parseObject(jsonStr, MessageInfo.class);
        //前往转码并输出 Attribute value must be
        dealCenter.BuildEmergacyMsg(info);
    }

    /**
     * 监听指定消息队列--患者报告卡处理
     * @param jsonStr
     * @throws InterruptedException
     */
    @RabbitListener(queues = "${config.queue.emergacy}", returnExceptions = "false", concurrency="2")
    public void getRepMessage(String jsonStr){
        MessageInfo info =  JSON.parseObject(jsonStr, MessageInfo.class);
        //前往转码并输出 Attribute value must be
        dealCenter.BuildReportCardMsg(info);
    }


}

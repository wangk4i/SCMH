package com.hyd.subordtest.domain.detail;

import lombok.Data;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.util.ObjectUtils;

/**
 * Created by xieshuai on 2020/6/4 11:07
 */

@Data
public class MessageQueueDetail {

    /**
     * 队列名称
     */
    private String queueName;

    /**
     * 监听容器标识
     */
    private String containerIdentity;

    /**
     * 监听是否有效
     */
    private boolean activeContainer;

    /**
     * 是否正在监听
     */
    private boolean running;

    /**
     * 活动消费者数量
     */
    private int activeConsumerCount;

    public MessageQueueDetail(String queueName, SimpleMessageListenerContainer container) {
        this.queueName = queueName;
        this.running = container.isRunning();
        this.activeContainer = container.isActive();
        this.activeConsumerCount = container.getActiveConsumerCount();
        this.containerIdentity = "Container@" + ObjectUtils.getIdentityHexString(container);
    }

}

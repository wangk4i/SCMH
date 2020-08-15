package com.hyd.responsexml.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created by xieshuai on 2020/8/10 下午 2:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDO {
    /**
     * 消息唯一标识
     */
    private String id;
    /**
     * 消息渠道 如 订单 支付 代表不同业务类型
     * 为消费时不同类去处理
     */
    private String channel;
    /**
     * 具体消息 json
     */
    private String body;

    /**
     * 延时时间 被消费时间  取当前时间戳+延迟时间
     */
    private Long delayTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}

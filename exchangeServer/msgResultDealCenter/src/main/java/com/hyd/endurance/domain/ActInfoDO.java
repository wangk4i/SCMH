package com.hyd.endurance.domain;

import lombok.Data;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/13 10:00
 */
@Data
public class ActInfoDO {

    private MessageInfoDO msgInfo;

    private ReturnMsgDO msgResult;

    private InterchangeDO bizInfo;

    public ActInfoDO(MessageInfoDO msgInfo, ReturnMsgDO msgResult, InterchangeDO bizInfo) {
        this.msgInfo = msgInfo;
        this.msgResult = msgResult;
        this.bizInfo = bizInfo;
    }
}

package com.hyd.endurance.domain;

import lombok.Data;

/**
 * Created by xieshuai on 2020/7/2 上午 11:38
 */
@Data
public class ReturnMsgDO {

    private String xmlNam;

    private String msgBody;

    private String receivedTime;

    private String status;

}
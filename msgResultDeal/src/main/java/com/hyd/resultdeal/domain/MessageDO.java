package com.hyd.resultdeal.domain;

import lombok.Data;

/**
 * Created by xieshuai on 2020/7/2 上午 11:38
 */
@Data
public class MessageDO {

    private String xmlNam;

    private String msgBody;

    private String receivedTime;

    private String status;

    @Override
    public String toString() {
        return "{" +
                "xmlNam='" + xmlNam + '\'' +
                ", msgBody='" + msgBody + '\'' +
                ", receivedTime='" + receivedTime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

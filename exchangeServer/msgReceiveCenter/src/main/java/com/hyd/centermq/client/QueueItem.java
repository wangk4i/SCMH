package com.hyd.centermq.client;

import javax.xml.bind.annotation.XmlAttribute;

public class QueueItem {
    private String bizName;
    private String queueName;
    private Integer msgCate;
    private Integer msgType;

    public String getBizName() {
        return bizName;
    }
    @XmlAttribute( name = "name")
    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    public String getQueueName() {
        return queueName;
    }
    @XmlAttribute( name = "queue")
    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public Integer getMsgCate() {
        return msgCate;
    }
    @XmlAttribute( name = "cate")
    public void setMsgCate(Integer msgCate) {
        this.msgCate = msgCate;
    }

    public Integer getMsgType() {
        return msgType;
    }
    @XmlAttribute( name = "type")
    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }
}
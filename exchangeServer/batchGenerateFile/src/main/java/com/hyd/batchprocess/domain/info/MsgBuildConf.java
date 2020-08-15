package com.hyd.batchprocess.domain.info;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class MsgBuildConf {
    private String name;
    private Integer MsgCate;
    private Integer MsgType;
    private Integer MsgAction;
    private Boolean ifUse;
    private String msgSql;

    public String getName() {
        return name;
    }
    @XmlAttribute(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    public Integer getMsgCate() {
        return MsgCate;
    }
    @XmlAttribute(name = "MsgCate")
    public void setMsgCate(Integer msgCate) {
        MsgCate = msgCate;
    }

    public Integer getMsgType() {
        return MsgType;
    }
    @XmlAttribute(name = "MsgType")
    public void setMsgType(Integer msgType) {
        MsgType = msgType;
    }

    public Integer getMsgAction() {
        return MsgAction;
    }
    @XmlAttribute(name = "MsgAction")
    public void setMsgAction(Integer msgAction) {
        MsgAction = msgAction;
    }

    public Boolean isIfUse() {
        return ifUse;
    }
    @XmlAttribute(name = "ifUse")
    public void setIfUse(Boolean ifUse) {
        this.ifUse = ifUse;
    }

    public String getMsgSql() {
        return msgSql;
    }

    @XmlValue
    public void setMsgSql(String msgSql) {
        this.msgSql = msgSql;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", MsgCate='" + MsgCate + '\'' +
                ", MsgType='" + MsgType + '\'' +
                ", MsgAction='" + MsgAction + '\'' +
                ", ifUse=" + ifUse +
                ", msgSql='" + msgSql + '\'' +
                '}';
    }
}

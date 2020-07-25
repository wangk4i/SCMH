package com.hyd.subordtest.domain.info;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)  // XML文件中的根标识

@XmlRootElement(name = "root")  // 控制JAXB 绑定类中属性和字段的排序
public class MsgBuildXml {
    private List<MsgBuildConf> MsgInfo;

    public List<MsgBuildConf> getMsgInfo() {
        return MsgInfo;
    }

    public void setMsgInfo(List<MsgBuildConf> msgInfo) {
        MsgInfo = msgInfo;
    }

    @Override
    public String toString() {
        return "" + MsgInfo ;
    }
}

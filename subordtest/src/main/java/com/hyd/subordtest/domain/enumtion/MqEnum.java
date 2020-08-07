package com.hyd.subordtest.domain.enumtion;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by xieshuai on 2020/6/28 上午 10:19
 */
@Component
public class MqEnum {

    public static String MQ_PATINFO;
    public static String MQ_REPORT;
    public static String MQ_DISCHARGE;
    public static String MQ_FOLLOWUP;
    public static String MQ_EMERGENCY;
    public static String MQ_XMLID;
    public static String MQ_XMLRETURN;

    public static String getMqPatinfo() {
        return MQ_PATINFO;
    }
    @Value("${config.queue.patinfo}")
    public void setMqPatinfo(String mqPatinfo) {
        MQ_PATINFO = mqPatinfo;
    }

    public static String getMqReport() {
        return MQ_REPORT;
    }
    @Value("${config.queue.report}")
    public void setMqReport(String mqReport) {
        MQ_REPORT = mqReport;
    }

    public static String getMqDischarge() {
        return MQ_DISCHARGE;
    }
    @Value("${config.queue.leftcard}")
    public void setMqDischarge(String mqDischarge) {
        MQ_DISCHARGE = mqDischarge;
    }

    public static String getMqFollowup() {
        return MQ_FOLLOWUP;
    }
    @Value("${config.queue.followup}")
    public void setMqFollowup(String mqFollowup) {
        MQ_FOLLOWUP = mqFollowup;
    }

    public static String getMqEmergency() {
        return MQ_EMERGENCY;
    }
    @Value("${config.queue.emergacy}")
    public void setMqEmergency(String mqEmergency) {
        MQ_EMERGENCY = mqEmergency;
    }

    public static String getMqXmlid() {
        return MQ_XMLID;
    }
    @Value("${config.queue.xmlfile}")
    public void setMqXmlid(String mqXmlid) {
        MQ_XMLID = mqXmlid;
    }

    public static String getMqXmlreturn() {
        return MQ_XMLRETURN;
    }
    @Value("${config.queue.xmlReturn}")
    public void setMqXmlreturn(String mqXmlreturn) {
        MQ_XMLRETURN = mqXmlreturn;
    }
}

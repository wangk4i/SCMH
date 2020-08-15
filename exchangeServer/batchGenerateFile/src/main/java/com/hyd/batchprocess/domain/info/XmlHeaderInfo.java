package com.hyd.batchprocess.domain.info;

import com.hyd.batchprocess.config.XmlHeadConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


/**
 * Created by xieshuai on 2020/6/11 10:05
 */
@Data
@Component
@ConfigurationProperties(prefix = "config.docmentenum")
public class XmlHeaderInfo {

    private String docmentId;

    private String operateEnum;

    private String activityion;

    private String reportZoneNam;

    private String reportZone;

    private String reportOrg;

    private String reportOrgNam;

    private String licenseCode;

    public XmlHeaderInfo() {

        this.reportZone = XmlHeadConfig.reportZoneCode;
//        this.reportZone =
    }

    public void setXmlHeaderInfo(String activityion, String zoneCode, String zoneCodeValue, String orgCode, String orgCodeValue, String licenseCode , String operateEnum) {
        this.operateEnum = operateEnum;
        this.activityion = activityion;
        this.reportZoneNam = zoneCodeValue;
        this.reportZone = zoneCode;
        this.reportOrg = orgCode;
        this.reportOrgNam = orgCodeValue;
        this.licenseCode = licenseCode;
    }

    public String getDocmentId() {
        return docmentId;
    }

    public void setDocmentId(String docmentId) {
        this.docmentId = docmentId;
    }

    public String getOperateEnum() {
        return operateEnum;
    }

    public void setOperateEnum(String operateEnum) {
        this.operateEnum = operateEnum;
    }

    public String getActivityion() {
        return activityion;
    }

    public void setActivityion(String activityion) {
        this.activityion = activityion;
    }

    public String getReportZoneNam() {
        return reportZoneNam;
    }

    public void setReportZoneNam(String reportZoneNam) {
        this.reportZoneNam = reportZoneNam;
    }

    public String getReportZone() {
        return reportZone;
    }

    public void setReportZone(String reportZone) {
        this.reportZone = reportZone;
    }

    public String getReportOrg() {
        return reportOrg;
    }

    public void setReportOrg(String reportOrg) {
        this.reportOrg = reportOrg;
    }

    public String getReportOrgNam() {
        return reportOrgNam;
    }

    public void setReportOrgNam(String reportOrgNam) {
        this.reportOrgNam = reportOrgNam;
    }

    public String getLicenseCode() {
        return licenseCode;
    }

    public void setLicenseCode(String licenseCode) {
        this.licenseCode = licenseCode;
    }
}

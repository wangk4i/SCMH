package com.hyd.subordtest.domain.info;

import lombok.Data;

/**
 * Created by xieshuai on 2020/6/11 10:05
 */

@Data
public class XmlHeaderInfo {

    private String docmentId;

    private String operateEnum;

    private String activityion;

    private String zoneCodeValue;

    private String zoneCode;

    private String orgCode;

    private String orgCodeValue;

    private String licenseCode;

    public void setXmlHeaderInfo(String activityion, String zoneCode, String zoneCodeValue, String orgCode, String orgCodeValue, String licenseCode ,String operateEnum) {
        this.operateEnum = operateEnum;
        this.activityion = activityion;
        this.zoneCodeValue = zoneCodeValue;
        this.zoneCode = zoneCode;
        this.orgCode = orgCode;
        this.orgCodeValue = orgCodeValue;
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

    public String getZoneCodeValue() {
        return zoneCodeValue;
    }

    public void setZoneCodeValue(String zoneCodeValue) {
        this.zoneCodeValue = zoneCodeValue;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgCodeValue() {
        return orgCodeValue;
    }

    public void setOrgCodeValue(String orgCodeValue) {
        this.orgCodeValue = orgCodeValue;
    }

    public String getLicenseCode() {
        return licenseCode;
    }

    public void setLicenseCode(String licenseCode) {
        this.licenseCode = licenseCode;
    }
}

package com.hyd.batchprocess.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/12 11:54
 */
@Component
public class XmlHeadConfig {

    public static String licenseCode;
    // 上传机构
    public static String reportOrgCode;

    public static String reportOrgNam;
    // 上传地区
    public static String reportZoneCode;

    public static String reportZoneNam;

    public static String getLicenseCode() {
        return licenseCode;
    }
    @Value("${config.docmentenum.license-code}")
    public void setLicenseCode(String licenseCode) {
        XmlHeadConfig.licenseCode = licenseCode;
    }

    public static String getReportOrgCode() {
        return reportOrgCode;
    }
    @Value("${config.docmentenum.reportOrg}")
    public void setReportOrgCode(String reportOrgCode) {
        XmlHeadConfig.reportOrgCode = reportOrgCode;
    }

    public static String getReportOrgNam() {
        return reportOrgNam;
    }
    @Value("${config.docmentenum.reportOrgNam}")
    public void setReportOrgNam(String reportOrgNam) {
        XmlHeadConfig.reportOrgNam = reportOrgNam;
    }

    public static String getReportZoneCode() {
        return reportZoneCode;
    }
    @Value("${config.docmentenum.reportZone}")
    public void setReportZoneCode(String reportZoneCode) {
        XmlHeadConfig.reportZoneCode = reportZoneCode;
    }

    public static String getReportZoneNam() {
        return reportZoneNam;
    }
    @Value("${config.docmentenum.reportZoneNam}")
    public void setReportZoneNam(String reportZoneNam) {
        XmlHeadConfig.reportZoneNam = reportZoneNam;
    }
}

package com.hyd.subordtest.service;

import com.hyd.subordtest.domain.enumtion.OperateEnum;
import com.hyd.subordtest.domain.enumtion.TypeEnum;
import com.hyd.subordtest.domain.info.MessageInfo;
import com.hyd.subordtest.domain.info.XmlHeaderInfo;
import com.hyd.subordtest.mapper.*;
import com.hyd.subordtest.utils.RedisUtils;
import com.hyd.subordtest.utils.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OperateTools {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired(required = false)
    private BasicInfoMapper basicInfoMapper;
    @Autowired(required = false)
    private CaseReportInfoMapper reportInfoMapper;
    @Autowired(required = false)
    private DischargeInfoMapper dischargeInfoMapper;
    @Autowired(required = false)
    private FollowupInfoMapper followupInfoMapper;
    @Autowired(required = false)
    private EmergencyInfoMapper emergencyInfoMapper;

    @Autowired
    private RedisUtils redisUtils;
    @Value("${config.docmentenum.license-code}")
    private String licenseCode;
    // 上传机构代码
    @Value("${config.docmentenum.reportOrg}")
    private String reportOrgCode;
    // 上传地区代码
    @Value("${config.docmentenum.reportZone}")
    private String reportZoneCode;


    @Value("${config.to-xmlpath}")
    private String toXmlpath;
    //业务分类编码
    @Value("${config.business-code}")
    private String businessCode;
    //操作机构编码
    @Value("${config.organcode}")
    private String organCode;

    /**
     * 接收新增档案消息并输出到Xml
     * @param info
     */
    public void addDocumentToXml(MessageInfo info){

        //去数据库查询数据
        Map<String, Object> result = basicInfoMapper.queryBasicInfoViewOfInsert(info.getId());
        //判断查询结果是否为Null
        if(result==null){
            log.info("当前ID无法转码,视图无此数据{}", info.getId());
            return;
        }

        //获取头部数据
        XmlHeaderInfo xmlHeaderInfo = new XmlHeaderInfo();
        String reportZoneNam = basicInfoMapper.queryZoneNamByZoneCd(reportZoneCode);
        String reportOrgNam = basicInfoMapper.queryOrgNamByOrgCd(reportOrgCode);
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.ADD);

        /*xmlHeaderInfo.setActivityion("MentalHealth");
        xmlHeaderInfo.setZoneCode((String)result.get("ZoneCd"));
        xmlHeaderInfo.setZoneCodeValue((String)result.get("ZoneNam"));
        xmlHeaderInfo.setOrgCode((String)result.get("OrgCode"));
        xmlHeaderInfo.setOrgCodeValue((String)result.get("OrgCodeValue"));
        xmlHeaderInfo.setLicenseCode(licenseCode);
        xmlHeaderInfo.setOperateEnum(OperateEnum.ADD);*/
        //xml 文件id 业务分类-进行数据交换的机构代码-系统当前时间（yyyyMMddHHmmssSSS）
        // 由于当前队列存在高并发可能，会导致唯一ID失效，采用Redis取号器实现ID
        xmlHeaderInfo.setDocmentId(this.getDateCd());


        //获取模板文件URL
        String templatePath = "templates\\BasicInfo\\Add_BasicInfo.vm";
        //设置Xmlname
        //String xmlName = TypeEnum.DOC + OperateEnum.ADD + info.getId();
        //String xmlName=setXmlNam(TypeEnum.DOC,info);
        String xmlName = xmlHeaderInfo.getDocmentId();
        //输出到xml
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);

    }

    /**
     *  接收修改档案消息并输出到Xml
     * @param info
     */
    public void updateDocumentToXml(MessageInfo info) {
        //去数据库查询数据
        Map<String, Object> result = basicInfoMapper.queryBasicInfoViewOfUpdate(info.getId());

        //判断查询结果是否为Null
        if(result==null){
            log.info("当前ID无法转码,视图无此数据{}", info.getId());
            return;
        }

        //获取头部数据
        XmlHeaderInfo xmlHeaderInfo = new XmlHeaderInfo();
        String reportZoneNam = basicInfoMapper.queryZoneNamByZoneCd(reportZoneCode);
        String reportOrgNam = basicInfoMapper.queryOrgNamByOrgCd(reportOrgCode);
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.UPDATE);
        //xml 文件id 业务分类-进行数据交换的机构代码-系统当前时间（yyyyMMddHHmmssSSS）
        // 由于当前队列存在高并发可能，会导致唯一ID失效，采用Redis取号器实现ID
        xmlHeaderInfo.setDocmentId(this.getDateCd());
        //获取模板文件URL
        String templatePath = "templates\\BasicInfo\\Update_BasicInfo.vm";
        //设置Xmlname
        String xmlName=setXmlNam(TypeEnum.DOC,info);
        //输出到xml
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);

    }

    /**
     * 接收删除档案消息并输出到Xml
     * @param info
     */
    public void delDocumentToXml(MessageInfo info){
        //去数据库查询数据
        Map<String, Object> result = basicInfoMapper.queryBasicInfoViewOfDelete(info.getId());

        //判断查询结果是否为Null
        if(result==null){
            log.info("当前ID无法转码,视图无此数据{}", info.getId());
            return;
        }

        //获取头部数据
        XmlHeaderInfo xmlHeaderInfo = new XmlHeaderInfo();
        String reportZoneNam = basicInfoMapper.queryZoneNamByZoneCd(reportZoneCode);
        String reportOrgNam = basicInfoMapper.queryOrgNamByOrgCd(reportOrgCode);
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.DELETE);
        //xml 文件id 业务分类-进行数据交换的机构代码-系统当前时间（yyyyMMddHHmmssSSS）
        // 由于当前队列存在高并发可能，会导致唯一ID失效，采用Redis取号器实现ID
        xmlHeaderInfo.setDocmentId(this.getDateCd());

        //获取模板文件URL
        String templatePath = "templates\\BasicInfo\\Delete_BasicInfo.vm";
        //设置Xmlname
        String xmlName=setXmlNam(TypeEnum.DOC,info);
        //输出到xml
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);

    }

    /**
     *  接收数据恢复消息并输出到XMl
     * @param info
     */
    public void restoreDocumentToXml(MessageInfo info){

        //去数据库查询数据
        Map<String, Object> result = basicInfoMapper.queryBasicInfoViewOfUndelete(info.getId());
        //判断查询结果是否为Null
        if(result==null){
            log.info("当前ID无法转码,视图无此数据{}", info.getId());
            return;
        }

        //获取头部数据
        XmlHeaderInfo xmlHeaderInfo = new XmlHeaderInfo();
        String reportZoneNam = basicInfoMapper.queryZoneNamByZoneCd(reportZoneCode);
        String reportOrgNam = basicInfoMapper.queryOrgNamByOrgCd(reportOrgCode);
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.UNDELETE);
        //xml 文件id 业务分类-进行数据交换的机构代码-系统当前时间（yyyyMMddHHmmssSSS）
        // 由于当前队列存在高并发可能，会导致唯一ID失效，采用Redis取号器实现ID
        xmlHeaderInfo.setDocmentId(this.getDateCd());

        //获取模板文件URL
        String templatePath = "templates\\BasicInfo\\Undelete_BasicInfo.vm";
        //设置Xmlname
        String xmlName=setXmlNam(TypeEnum.DOC,info);

        //输出到xml
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
    }

    /**
     *  接收转死亡消息并输出到XMl
     * @param info
     */
    public void turnDeathDocumentToXml(MessageInfo info){
        //去数据库查询数据
        Map<String, Object> result = basicInfoMapper.queryBasicInfoViewOfTrundeath(info.getId());
        //判断查询结果是否为Null
        if(result==null){
            log.info("当前ID无法转码,视图无此数据{}", info.getId());
            return;
        }
        //获取头部数据
        XmlHeaderInfo xmlHeaderInfo = new XmlHeaderInfo();
        String reportZoneNam = basicInfoMapper.queryZoneNamByZoneCd(reportZoneCode);
        String reportOrgNam = basicInfoMapper.queryOrgNamByOrgCd(reportOrgCode);
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.DECLAREDEATH);
        //xml 文件id 业务分类-进行数据交换的机构代码-系统当前时间（yyyyMMddHHmmssSSS）
        // 由于当前队列存在高并发可能，会导致唯一ID失效，采用Redis取号器实现ID
        xmlHeaderInfo.setDocmentId(this.getDateCd());

        //获取模板文件URL
        //String templatePath = "templates\\Declaredeath_BasicInfo.vm";
        String templatePath = "";
        String DeathCauseCode = (String) result.get("DeathCauseCode");
        // 死亡原因为躯体疾病，需上传躯体疾病明细,此处选择模板
        if (DeathCauseCode.equals("1")){
            templatePath = "templates\\BasicInfo\\Declaredeath_BasicInfo.vm";
        } else {
            templatePath = "templates\\BasicInfo\\Declaredeath_BasicInfo_part.vm";
        }
        //设置Xmlname
        String xmlName=setXmlNam(TypeEnum.DOC,info);

        //输出到xml
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
    }

    /**
     * 报告卡操作
     * @param info
     */
    public void addReportToXml(MessageInfo info) {
        //去数据库查询数据
        Map<String, Object> result = reportInfoMapper.queryReportInfoViewOfInsert(info.getId());
        //判断查询结果是否为Null
        if(result==null){
            log.info("当前ID无法转码,视图无此数据{}", info.getId());
            return;
        }

        //获取头部数据
        XmlHeaderInfo xmlHeaderInfo = new XmlHeaderInfo();
        String reportZoneNam = basicInfoMapper.queryZoneNamByZoneCd(reportZoneCode);
        String reportOrgNam = basicInfoMapper.queryOrgNamByOrgCd(reportOrgCode);
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.ADD);
        //xml 文件id 业务分类-进行数据交换的机构代码-系统当前时间（yyyyMMddHHmmssSSS）
        // 由于当前队列存在高并发可能，会导致唯一ID失效，采用Redis取号器实现ID
        xmlHeaderInfo.setDocmentId(this.getDateCd());

        List<Map<String,Object>> drugList = reportInfoMapper.queryMedicationbyRptCd(info.getId());

        result.put("DrugList",drugList);

        //获取模板文件URL
        String templatePath = "templates\\ReportInfo\\Add_ReportInfo.vm";
        //设置Xmlname
        String xmlName=setXmlNam(TypeEnum.REP,info);
        //输出到xml
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName, drugList);
    }

    public void updateReportToXml(MessageInfo info) {
        //去数据库查询数据
        Map<String, Object> result = reportInfoMapper.queryReportInfoViewOfUpdate(info.getId());
        //判断查询结果是否为Null
        if(result==null){
            log.info("当前ID无法转码,视图无此数据{}", info.getId());
            return;
        }

        //获取头部数据
        XmlHeaderInfo xmlHeaderInfo = new XmlHeaderInfo();
        String reportZoneNam = basicInfoMapper.queryZoneNamByZoneCd(reportZoneCode);
        String reportOrgNam = basicInfoMapper.queryOrgNamByOrgCd(reportOrgCode);
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.UPDATE);
        //xml 文件id 业务分类-进行数据交换的机构代码-系统当前时间（yyyyMMddHHmmssSSS）
        // 由于当前队列存在高并发可能，会导致唯一ID失效，采用Redis取号器实现ID
        xmlHeaderInfo.setDocmentId(this.getDateCd());

        //获取模板文件URL
        String templatePath = "templates\\ReportInfo\\Update_ReportInfo.vm";
        //设置Xmlname
        String xmlName=setXmlNam(TypeEnum.REP,info);
        //输出到xml
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
    }

    public void delReportToXml(MessageInfo info) {
        //去数据库查询数据
        Map<String, Object> result = reportInfoMapper.queryReportInfoViewOfDelete(info.getId());
        //判断查询结果是否为Null
        if(result==null){
            log.info("当前ID无法转码,视图无此数据{}", info.getId());
            return;
        }

        //获取头部数据
        XmlHeaderInfo xmlHeaderInfo = new XmlHeaderInfo();
        String reportZoneNam = basicInfoMapper.queryZoneNamByZoneCd(reportZoneCode);
        String reportOrgNam = basicInfoMapper.queryOrgNamByOrgCd(reportOrgCode);
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.DELETE);
        //xml 文件id 业务分类-进行数据交换的机构代码-系统当前时间（yyyyMMddHHmmssSSS）
        // 由于当前队列存在高并发可能，会导致唯一ID失效，采用Redis取号器实现ID
        xmlHeaderInfo.setDocmentId(this.getDateCd());

        //获取模板文件URL
        String templatePath = "templates\\ReportInfo\\Delete_ReportInfo.vm";
        //设置Xmlname
        String xmlName=setXmlNam(TypeEnum.REP,info);
        //输出到xml
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
    }

    /**
     * 出院报告卡操作
     */
    public void addDischargeToXml(MessageInfo info) {
        //去数据库查询数据
        Map<String, Object> result = dischargeInfoMapper.queryDischargeInfoViewOfInsert(info.getId());
        //判断查询结果是否为Null
        if(result==null){
            log.info("当前ID无法转码,视图无此数据{}", info.getId());
            return;
        }

        //获取头部数据
        XmlHeaderInfo xmlHeaderInfo = new XmlHeaderInfo();
        String reportZoneNam = basicInfoMapper.queryZoneNamByZoneCd(reportZoneCode);
        String reportOrgNam = basicInfoMapper.queryOrgNamByOrgCd(reportOrgCode);
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.ADD);
        //xml 文件id 业务分类-进行数据交换的机构代码-系统当前时间（yyyyMMddHHmmssSSS）
        // 由于当前队列存在高并发可能，会导致唯一ID失效，采用Redis取号器实现ID
        xmlHeaderInfo.setDocmentId(this.getDateCd());

        //获取模板文件URL
        String templatePath = "templates\\DischargeInfo\\Add_DischargeInfo.vm";
        //设置Xmlname
        String xmlName=setXmlNam(TypeEnum.DISC,info);
        //输出到xml
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
    }

    public void updateDischargeToXml(MessageInfo info) {
        //去数据库查询数据
        Map<String, Object> result = dischargeInfoMapper.queryDischargeInfoViewOfUpdate(info.getId());
        //判断查询结果是否为Null
        if(result==null){
            log.info("当前ID无法转码,视图无此数据{}", info.getId());
            return;
        }

        //获取头部数据
        XmlHeaderInfo xmlHeaderInfo = new XmlHeaderInfo();
        String reportZoneNam = basicInfoMapper.queryZoneNamByZoneCd(reportZoneCode);
        String reportOrgNam = basicInfoMapper.queryOrgNamByOrgCd(reportOrgCode);
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.UPDATE);
        //xml 文件id 业务分类-进行数据交换的机构代码-系统当前时间（yyyyMMddHHmmssSSS）
        // 由于当前队列存在高并发可能，会导致唯一ID失效，采用Redis取号器实现ID
        xmlHeaderInfo.setDocmentId(this.getDateCd());

        //获取模板文件URL
        String templatePath = "templates\\DischargeInfo\\Update_DischargeInfo.vm";
        //设置Xmlname
        String xmlName=setXmlNam(TypeEnum.DISC,info);
        //输出到xml
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
    }

    public void delDischargeToXml(MessageInfo info) {
        //去数据库查询数据
        Map<String, Object> result = dischargeInfoMapper.queryDischargeInfoViewOfDelete(info.getId());
        //判断查询结果是否为Null
        if(result==null){
            log.info("当前ID无法转码,视图无此数据{}", info.getId());
            return;
        }

        //获取头部数据
        XmlHeaderInfo xmlHeaderInfo = new XmlHeaderInfo();
        String reportZoneNam = basicInfoMapper.queryZoneNamByZoneCd(reportZoneCode);
        String reportOrgNam = basicInfoMapper.queryOrgNamByOrgCd(reportOrgCode);
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.DELETE);
        //xml 文件id 业务分类-进行数据交换的机构代码-系统当前时间（yyyyMMddHHmmssSSS）
        // 由于当前队列存在高并发可能，会导致唯一ID失效，采用Redis取号器实现ID
        xmlHeaderInfo.setDocmentId(this.getDateCd());

        //获取模板文件URL
        String templatePath = "templates\\DischargeInfo\\Delete_DischargeInfo.vm";
        //设置Xmlname
        String xmlName=setXmlNam(TypeEnum.DISC,info);
        //输出到xml
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
    }

    /**
     * 流转信息操作
     */
    public void addFollowupToXml(MessageInfo info) {
        //去数据库查询数据
        Map<String, Object> result = followupInfoMapper.queryFollowupInfoViewOfInsert(info.getId());
        //判断查询结果是否为Null
        if(result==null){
            log.info("当前ID无法转码,视图无此数据{}", info.getId());
            return;
        }

        //获取头部数据
        XmlHeaderInfo xmlHeaderInfo = new XmlHeaderInfo();
        String reportZoneNam = basicInfoMapper.queryZoneNamByZoneCd(reportZoneCode);
        String reportOrgNam = basicInfoMapper.queryOrgNamByOrgCd(reportOrgCode);
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.ADD);
        //xml 文件id 业务分类-进行数据交换的机构代码-系统当前时间（yyyyMMddHHmmssSSS）
        // 由于当前队列存在高并发可能，会导致唯一ID失效，采用Redis取号器实现ID
        xmlHeaderInfo.setDocmentId(this.getDateCd());

        //获取模板文件URL
        String templatePath = "templates\\FollowupInfo\\Add_FollowupInfo.vm";
        //设置Xmlname
        String xmlName=setXmlNam(TypeEnum.FOLL,info);
        //输出到xml
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
    }

    public void updateFollowupToXml(MessageInfo info) {
        //去数据库查询数据
        Map<String, Object> result = followupInfoMapper.queryFollowupInfoViewOfUpdate(info.getId());
        //判断查询结果是否为Null
        if(result==null){
            log.info("当前ID无法转码,视图无此数据{}", info.getId());
            return;
        }

        //获取头部数据
        XmlHeaderInfo xmlHeaderInfo = new XmlHeaderInfo();
        String reportZoneNam = basicInfoMapper.queryZoneNamByZoneCd(reportZoneCode);
        String reportOrgNam = basicInfoMapper.queryOrgNamByOrgCd(reportOrgCode);
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.UPDATE);
        //xml 文件id 业务分类-进行数据交换的机构代码-系统当前时间（yyyyMMddHHmmssSSS）
        // 由于当前队列存在高并发可能，会导致唯一ID失效，采用Redis取号器实现ID
        xmlHeaderInfo.setDocmentId(this.getDateCd());

        //获取模板文件URL
        String templatePath = "templates\\FollowupInfo\\Update_FollowupInfo.vm";
        //设置Xmlname
        String xmlName=setXmlNam(TypeEnum.FOLL,info);
        //输出到xml
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
    }

    public void delFollowupToXml(MessageInfo info) {
        //去数据库查询数据
        Map<String, Object> result = followupInfoMapper.queryFollowupInfoViewOfDelete(info.getId());
        //判断查询结果是否为Null
        if(result==null){
            log.info("当前ID无法转码,视图无此数据{}", info.getId());
            return;
        }

        //获取头部数据
        XmlHeaderInfo xmlHeaderInfo = new XmlHeaderInfo();
        String reportZoneNam = basicInfoMapper.queryZoneNamByZoneCd(reportZoneCode);
        String reportOrgNam = basicInfoMapper.queryOrgNamByOrgCd(reportOrgCode);
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.DELETE);
        //xml 文件id 业务分类-进行数据交换的机构代码-系统当前时间（yyyyMMddHHmmssSSS）
        // 由于当前队列存在高并发可能，会导致唯一ID失效，采用Redis取号器实现ID
        xmlHeaderInfo.setDocmentId(this.getDateCd());

        //获取模板文件URL
        String templatePath = "templates\\FollowupInfo\\Delete_FollowupInfo.vm";
        //设置Xmlname
        String xmlName=setXmlNam(TypeEnum.FOLL,info);
        //输出到xml
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
    }

    /**
     * 应急处置操作
     */
    public void addEmergencyToXml(MessageInfo info) {
        //去数据库查询数据
        Map<String, Object> result = emergencyInfoMapper.queryEmergencyInfoViewOfInsert(info.getId());
        //判断查询结果是否为Null
        if(result==null){
            log.info("当前ID无法转码,视图无此数据{}", info.getId());
            return;
        }

        //获取头部数据
        XmlHeaderInfo xmlHeaderInfo = new XmlHeaderInfo();
        String reportZoneNam = basicInfoMapper.queryZoneNamByZoneCd(reportZoneCode);
        String reportOrgNam = basicInfoMapper.queryOrgNamByOrgCd(reportOrgCode);
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.ADD);
        //xml 文件id 业务分类-进行数据交换的机构代码-系统当前时间（yyyyMMddHHmmssSSS）
        // 由于当前队列存在高并发可能，会导致唯一ID失效，采用Redis取号器实现ID
        xmlHeaderInfo.setDocmentId(this.getDateCd());

        //获取模板文件URL
        String templatePath = "templates\\EmergencyInfo\\Add_EmergencyInfo.vm";
        //设置Xmlname
        String xmlName=setXmlNam(TypeEnum.EMER,info);
        //输出到xml
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
    }

    public void updateEmergencyToXml(MessageInfo info) {
        //去数据库查询数据
        Map<String, Object> result = emergencyInfoMapper.queryEmergencyInfoViewOfUpdate(info.getId());
        //判断查询结果是否为Null
        if(result==null){
            log.info("当前ID无法转码,视图无此数据{}", info.getId());
            return;
        }

        //获取头部数据
        XmlHeaderInfo xmlHeaderInfo = new XmlHeaderInfo();
        String reportZoneNam = basicInfoMapper.queryZoneNamByZoneCd(reportZoneCode);
        String reportOrgNam = basicInfoMapper.queryOrgNamByOrgCd(reportOrgCode);
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.UPDATE);
        //xml 文件id 业务分类-进行数据交换的机构代码-系统当前时间（yyyyMMddHHmmssSSS）
        // 由于当前队列存在高并发可能，会导致唯一ID失效，采用Redis取号器实现ID
        xmlHeaderInfo.setDocmentId(this.getDateCd());

        //获取模板文件URL
        String templatePath = "templates\\EmergencyInfo\\Update_EmergencyInfo.vm";
        //设置Xmlname
        String xmlName=setXmlNam(TypeEnum.EMER,info);
        //输出到xml
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
    }

    public void delEmergencyToXml(MessageInfo info) {
        //去数据库查询数据
        Map<String, Object> result = emergencyInfoMapper.queryEmergencyInfoViewOfDelete(info.getId());
        //判断查询结果是否为Null
        if(result==null){
            log.info("当前ID无法转码,视图无此数据{}", info.getId());
            return;
        }

        //获取头部数据
        XmlHeaderInfo xmlHeaderInfo = new XmlHeaderInfo();
        String reportZoneNam = basicInfoMapper.queryZoneNamByZoneCd(reportZoneCode);
        String reportOrgNam = basicInfoMapper.queryOrgNamByOrgCd(reportOrgCode);
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.DELETE);
        //xml 文件id 业务分类-进行数据交换的机构代码-系统当前时间（yyyyMMddHHmmssSSS）
        // 由于当前队列存在高并发可能，会导致唯一ID失效，采用Redis取号器实现ID
        xmlHeaderInfo.setDocmentId(this.getDateCd());

        //获取模板文件URL
        String templatePath = "templates\\EmergencyInfo\\Delete_EmergencyInfo.vm";
        //设置Xmlname
        String xmlName=setXmlNam(TypeEnum.EMER,info);
        //输出到xml
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
    }

    /**
     * 每间隔1小时，生成1000份当前时间的id，放入Redis的set中，且有效期为1h
     */
    @Scheduled(cron = "0 0 0/1 * * ?")
    public void createDateCd(){
        for(int i=0; i<1000; i++){
            Integer index = i;
            DecimalFormat df=new DecimalFormat("000");
            String str2 = df.format(index);
            String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+ str2;
            //放入Redis并设置有效时间1h
            redisUtils.sSetAndTime("dataCd", 60L*60 ,timeStamp);
        }

    }

    /**
     * 从redis的set中随机获取一个id,获取成功后，从队列中移除当前id
     * 当Redis连接异常或set队列为空时，实时生成id
     * @return
     */
    public String getDateCd(){
        boolean b = false;
        String dataCd = null;
        try {
            //获取Redis中所有ID数量
            long idCount = redisUtils.sGetSetSize("dataCd");
            if(idCount>0){
                //获取Id
                String redisId = redisUtils.randomMember("dataCd");
                //移除
                redisUtils.setRemove("dataCd", redisId);
                dataCd = businessCode +"-" + organCode +"-"+ redisId;
            }else {
                b = true;
            }
        }catch (Exception e){
            b = true;
        }finally {
            if(b){
                dataCd = businessCode +"-"+ organCode +"-"+ StringUtils.getDateStr("yyyyMMddHHmmssSSS");
            }
        }
        return dataCd;
    }

    private String setXmlNam(String type,MessageInfo info){
        String operate ="";
        switch (info.getMsgaction()){
            case 1:
                operate = OperateEnum.ADD;
                break;
            case 2:
                operate = OperateEnum.UPDATE;
                break;
            case 3:
                operate = OperateEnum.DELETE;
            default:
                break;
        }
        if (type.equals(TypeEnum.DOC)){
            switch (info.getMsgaction()){
                case 4:
                    operate = OperateEnum.UNDELETE;
                    break;
                case 5:
                    operate = OperateEnum.DECLAREDEATH;
                    break;
                default:
                    break;
            }}
        return type + operate + "_"+ info.getId()+"_"+ StringUtils.getDateStr("yyyyMMddHHmmssSSS");
    }

    private void  toXML(XmlHeaderInfo info, Map<String, Object> map, String templatePath, String xmlName){
        //初始化模板引擎
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();

        //获取模板文件
        Template template = null;
        try {
            template = ve.getTemplate(templatePath);
        }catch (Exception e){
            e.printStackTrace();
            log.info("获取模板文件失败,模板路径:"+templatePath);
        }
        //设置xml变量参数
        VelocityContext vc = new VelocityContext();

        //设置头部文件
        Field[] fields  = info.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                vc.put(field.getName(), field.get(info));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                log.info("根据反射获取字段名称和字段值失败");
            } finally {
                field.setAccessible(false);
            }
        }
        //设置body信息
        for(Map.Entry<String, Object> entry: map.entrySet()){
            vc.put(entry.getKey(), entry.getValue());
        }
        //生成xml
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(new File(toXmlpath+"\\"+xmlName+".xml"));
            template.merge(vc, fileWriter);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    private void  toXML(XmlHeaderInfo info, Map<String, Object> map, String templatePath, String xmlName, List<Map<String,Object>> todoList){
        //初始化模板引擎
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();

        //获取模板文件
        Template template = null;
        try {
            template = ve.getTemplate(templatePath);
        }catch (Exception e){
            e.printStackTrace();
            log.info("获取模板文件失败,模板路径:"+templatePath);
        }
        //设置xml变量参数
        VelocityContext vc = new VelocityContext();

        //设置头部文件
        Field[] fields  = info.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                vc.put(field.getName(), field.get(info));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                log.info("根据反射获取字段名称和字段值失败");
            } finally {
                field.setAccessible(false);
            }
        }
        //设置body信息
        for(Map.Entry<String, Object> entry: map.entrySet()){
            vc.put(entry.getKey(), entry.getValue());
        }

        for (Map<String,Object> drugInfo: todoList){
            for (Map.Entry<String, Object> entry: drugInfo.entrySet()){
                vc.put(entry.getKey(), entry.getValue());
            }
        }


        //生成xml
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(new File(toXmlpath+"\\"+xmlName+".xml"));
            template.merge(vc, fileWriter);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    /*private XmlHeaderInfo setXmlHeaderInfo(XmlHeaderInfo xml, String Activiitytion){

    }*/

}

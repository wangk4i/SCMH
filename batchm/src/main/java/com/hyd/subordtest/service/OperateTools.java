package com.hyd.subordtest.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class OperateTools {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    RedisTemplate<String,Object> redisTemplate;

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
    @Autowired(required = false)
    private DictInfoMapper dictInfoMapper;

    @Autowired
    private RedisUtils redisUtils;
    @Value("${config.docmentenum.license-code}")
    private String licenseCode;
    // 上传机构
    @Value("${config.docmentenum.reportOrg}")
    private String reportOrgCode;
    @Value("${config.docmentenum.reportOrgNam}")
    private String reportOrgNam;
    // 上传地区
    @Value("${config.docmentenum.reportZone}")
    private String reportZoneCode;
    @Value("${config.docmentenum.reportZoneNam}")
    private String reportZoneNam;

    // 输出xml路径
    @Value("${config.to-xmlpath}")
    private String toXmlpath;
    //业务分类编码
    @Value("${config.business-code}")
    private String businessCode;
    // 模板xml路径
    @Value("${config.templateFilePath}")
    private String templateFilePath;
    @Value("${config.msgPersistPath}")
    private String toMsgPath;
    @Value("${config.returnTxtPath}")
    private String toReturnTxtPath;




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
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.ADD);

        //xml 文件id 业务分类-进行数据交换的机构代码-系统当前时间（yyyyMMddHHmmssSSS）
        // 由于当前队列存在高并发可能，会导致唯一ID失效，采用Redis取号器实现ID
        String xmlName = this.getFileNum();
        xmlHeaderInfo.setDocmentId(xmlName);

        // 既往危险行为列表
        String pastRiskHaveStr = (String) result.get("PastRiskHave");
        result.put("PastRiskHave", this.getListValue(pastRiskHaveStr));

        //获取模板文件URL
        String templatePath = "templates\\BasicInfo\\Add_BasicInfo.vm"; // templateFilePath + "\BasicInfo\Add_BasicInfo.vm" 模板绝对路径
        //输出到xml
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
        this.toMsgJson(info, xmlName);
        this.toReturnTxt(xmlName);

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
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.UPDATE);
        //xml 文件id 业务分类-进行数据交换的机构代码-系统当前时间（yyyyMMddHHmmssSSS）
        // 由于当前队列存在高并发可能，会导致唯一ID失效，采用Redis取号器实现ID
        String xmlName = this.getFileNum();
        xmlHeaderInfo.setDocmentId(xmlName);

        // 既往危险行为列表
        String pastRiskHaveStr = (String) result.get("PastRiskHave");
        result.put("PastRiskHave", this.getListValue(pastRiskHaveStr));
        //获取模板文件URL
        String templatePath = "templates\\BasicInfo\\Update_BasicInfo.vm";

        //输出到xml
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
        this.toMsgJson(info, xmlName);
        this.toReturnTxt(xmlName);

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
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.DELETE);
        //xml 文件id 业务分类-进行数据交换的机构代码-系统当前时间（yyyyMMddHHmmssSSS）
        // 由于当前队列存在高并发可能，会导致唯一ID失效，采用Redis取号器实现ID
        String xmlName = this.getFileNum();
        xmlHeaderInfo.setDocmentId(xmlName);

        //获取模板文件URL
        String templatePath = "templates\\BasicInfo\\Delete_BasicInfo.vm";

        //输出到xml
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
        this.toMsgJson(info, xmlName);
        this.toReturnTxt(xmlName);

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
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.UNDELETE);
        //xml 文件id 业务分类-进行数据交换的机构代码-系统当前时间（yyyyMMddHHmmssSSS）
        // 由于当前队列存在高并发可能，会导致唯一ID失效，采用Redis取号器实现ID
        String xmlName = this.getFileNum();
        xmlHeaderInfo.setDocmentId(xmlName);

        //获取模板文件URL
        String templatePath = "templates\\BasicInfo\\Undelete_BasicInfo.vm";


        //输出到xml
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
        this.toMsgJson(info, xmlName);
        this.toReturnTxt(xmlName);
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
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.DECLAREDEATH);
        //xml 文件id 业务分类-进行数据交换的机构代码-系统当前时间（yyyyMMddHHmmssSSS）
        // 由于当前队列存在高并发可能，会导致唯一ID失效，采用Redis取号器实现ID
        String xmlName = this.getFileNum();
        xmlHeaderInfo.setDocmentId(xmlName);

        //获取模板文件URL
        String templatePath = "templates\\BasicInfo\\Declaredeath_BasicInfo.vm";



        //输出到xml
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
        this.toMsgJson(info, xmlName);
        this.toReturnTxt(xmlName);
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
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.ADD);
        //xml 文件id 业务分类-进行数据交换的机构代码-系统当前时间（yyyyMMddHHmmssSSS）
        // 由于当前队列存在高并发可能，会导致唯一ID失效，采用Redis取号器实现ID
        String xmlName = this.getFileNum();
        xmlHeaderInfo.setDocmentId(xmlName);

        // 服药列表
        List<Map<String, Object>> drugList = reportInfoMapper.queryMedicationByNewCaseReportId(info.getId());
        result.put("DrugList",drugList);

        // 既往危险行为列表
        String pastRiskHaveStr = (String) result.get("PastRiskHave");
        result.put("PastRiskHave", this.getListValue(pastRiskHaveStr));

        // 送诊主体列表
        String sendClinSubStr = (String) result.get("SendClinSub");
        result.put("SendClinSub",this.getListValue(sendClinSubStr));

        //获取模板文件URL
        String templatePath = "templates\\ReportInfo\\Add_ReportInfo.vm";

        //输出到xml
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
        this.toMsgJson(info, xmlName);
        this.toReturnTxt(xmlName);
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
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.UPDATE);
        //xml 文件id 业务分类-进行数据交换的机构代码-系统当前时间（yyyyMMddHHmmssSSS）
        // 由于当前队列存在高并发可能，会导致唯一ID失效，采用Redis取号器实现ID
        String xmlName = this.getFileNum();
        xmlHeaderInfo.setDocmentId(xmlName);

        // 服药列表
        List<Map<String, Object>> drugList = reportInfoMapper.queryMedicationByNewCaseReportId(info.getId());
        result.put("DrugList",drugList);

        // 既往危险行为列表
        String pastRiskHaveStr = (String) result.get("PastRiskHave");
        result.put("PastRiskHave", this.getListValue(pastRiskHaveStr));

        // 送诊主体列表
        String sendClinSubStr = (String) result.get("SendClinSub");
        result.put("SendClinSub",this.getListValue(sendClinSubStr));

        //获取模板文件URL
        String templatePath = "templates\\ReportInfo\\Add_ReportInfo.vm";

        //输出到xml
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
        this.toMsgJson(info, xmlName);
        this.toReturnTxt(xmlName);
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
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.DELETE);
        //xml 文件id 业务分类-进行数据交换的机构代码-系统当前时间（yyyyMMddHHmmssSSS）
        // 由于当前队列存在高并发可能，会导致唯一ID失效，采用Redis取号器实现ID
        String xmlName = this.getFileNum();
        xmlHeaderInfo.setDocmentId(xmlName);

        //获取模板文件URL
        String templatePath = "templates\\ReportInfo\\Delete_ReportInfo.vm";

        //输出到xml
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
        this.toMsgJson(info, xmlName);
        this.toReturnTxt(xmlName);
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
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.ADD);
        //xml 文件id 业务分类-进行数据交换的机构代码-系统当前时间（yyyyMMddHHmmssSSS）
        // 由于当前队列存在高并发可能，会导致唯一ID失效，采用Redis取号器实现ID
        String xmlName = this.getFileNum();
        xmlHeaderInfo.setDocmentId(xmlName);

        // 用药情况列表
        List<Map<String, Object>> drugList = dischargeInfoMapper.queryDrugListByDischargeId(info.getId());
        result.put("DrugList",drugList);

        // 指导用药列表
        List<Map<String, Object>> guideDrugList = dischargeInfoMapper.queryGuideDrugListByDischargeId(info.getId());
        result.put("GuideDrugList",guideDrugList);

        //获取模板文件URL
        String templatePath = "templates\\DischargeInfo\\Add_DischargeInfo.vm";

        //输出到xml
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
        this.toMsgJson(info, xmlName);
        this.toReturnTxt(xmlName);
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
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.ADD);
        //xml 文件id 业务分类-进行数据交换的机构代码-系统当前时间（yyyyMMddHHmmssSSS）
        // 由于当前队列存在高并发可能，会导致唯一ID失效，采用Redis取号器实现ID
        String xmlName = this.getFileNum();
        xmlHeaderInfo.setDocmentId(xmlName);

        // 用药情况列表
        List<Map<String, Object>> drugList = dischargeInfoMapper.queryDrugListByDischargeId(info.getId());
        result.put("DrugList",drugList);

        // 指导用药列表
        List<Map<String, Object>> guideDrugList = dischargeInfoMapper.queryGuideDrugListByDischargeId(info.getId());
        result.put("GuideDrugList",guideDrugList);

        // 既往危险行为列表
        String pastRiskHaveStr = (String) result.get("PastRiskHave");
        result.put("PastRiskHave", this.getListValue(pastRiskHaveStr));

        // 康复措施列表
        String recoveryMeasureStr = (String) result.get("RecoveryMeasures");
        result.put("RecoveryMeasures", this.getListValue(recoveryMeasureStr));

        //获取模板文件URL
        String templatePath = "templates\\DischargeInfo\\Add_DischargeInfo.vm";

        //输出到xml
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
        this.toMsgJson(info, xmlName);
        this.toReturnTxt(xmlName);
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
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.DELETE);
        //xml 文件id 业务分类-进行数据交换的机构代码-系统当前时间（yyyyMMddHHmmssSSS）
        // 由于当前队列存在高并发可能，会导致唯一ID失效，采用Redis取号器实现ID
        String xmlName = this.getFileNum();
        xmlHeaderInfo.setDocmentId(xmlName);

        //获取模板文件URL
        String templatePath = "templates\\DischargeInfo\\Delete_DischargeInfo.vm";

        //输出到xml
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
        this.toMsgJson(info, xmlName);
        this.toReturnTxt(xmlName);
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
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.ADD);
        //xml 文件id 业务分类-进行数据交换的机构代码-系统当前时间（yyyyMMddHHmmssSSS）
        // 由于当前队列存在高并发可能，会导致唯一ID失效，采用Redis取号器实现ID
        String xmlName = this.getFileNum();
        xmlHeaderInfo.setDocmentId(xmlName);

        // 用药情况列表
        List<Map<String, Object>> drugList = followupInfoMapper.queryDrugListByFollowupId(info.getId());
        result.put("DrugList", drugList);

        //用药指导列表
        List<Map<String, Object>>guideDrugList = followupInfoMapper.queryGuideDrugListByFollowupId(info.getId());
        result.put("GuideDrugList", guideDrugList);

        // 随访对象列表
        String followObjectStr = (String) result.get("FollowObject");
        result.put("FollowObject", this.getListValue(followObjectStr));

        // 目前症状列表
        String currSympStr = (String) result.get("Symptom");
        result.put("Symptom", this.getListValue(currSympStr));

        // 实验室检查列表
        String labInspectStr = (String) result.get("LabExamItem");
        result.put("LabExamItem", this.getListValue(labInspectStr));

        // 康复措施列表
        String recoveryMeasureStr = (String) result.get("RecoveryMeasures");
        result.put("RecoveryMeasures", this.getListValue(recoveryMeasureStr));

        //获取模板文件URL
        String templatePath = "templates\\FollowupInfo\\Add_FollowupInfo.vm";

        //输出到xml
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
        this.toMsgJson(info, xmlName);
        this.toReturnTxt(xmlName);
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
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.UPDATE);
        //xml 文件id 业务分类-进行数据交换的机构代码-系统当前时间（yyyyMMddHHmmssSSS）
        // 由于当前队列存在高并发可能，会导致唯一ID失效，采用Redis取号器实现ID
        String xmlName = this.getFileNum();
        xmlHeaderInfo.setDocmentId(xmlName);

        //获取模板文件URL
        String templatePath = "templates\\FollowupInfo\\Update_FollowupInfo.vm";

        //输出到xml
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
        this.toMsgJson(info, xmlName);
        this.toReturnTxt(xmlName);
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
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.DELETE);
        //xml 文件id 业务分类-进行数据交换的机构代码-系统当前时间（yyyyMMddHHmmssSSS）
        // 由于当前队列存在高并发可能，会导致唯一ID失效，采用Redis取号器实现ID
        String xmlName = this.getFileNum();
        xmlHeaderInfo.setDocmentId(xmlName);

        //获取模板文件URL
        String templatePath = "templates\\FollowupInfo\\Delete_FollowupInfo.vm";

        //输出到xml
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
        this.toMsgJson(info, xmlName);
        this.toReturnTxt(xmlName);
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
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.ADD);
        //xml 文件id 业务分类-进行数据交换的机构代码-系统当前时间（yyyyMMddHHmmssSSS）
        // 由于当前队列存在高并发可能，会导致唯一ID失效，采用Redis取号器实现ID
        String xmlName = this.getFileNum();
        xmlHeaderInfo.setDocmentId(xmlName);

        // 处置缘由列表
        String dealReasonStr = (String) result.get("DealReason");
        result.put("DealReason", this.getListValue(dealReasonStr));

        // 处置措施列表
        String dealMeasureStr = (String) result.get("DealMeasure");
        result.put("DealMeasure", this.getListValue(dealMeasureStr));

        //获取模板文件URL
        String templatePath = "templates\\EmergencyInfo\\Add_EmergencyInfo.vm";

        //输出到xml
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
        this.toMsgJson(info, xmlName);
        this.toReturnTxt(xmlName);
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
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.UPDATE);
        //xml 文件id 业务分类-进行数据交换的机构代码-系统当前时间（yyyyMMddHHmmssSSS）
        // 由于当前队列存在高并发可能，会导致唯一ID失效，采用Redis取号器实现ID
        String xmlName = this.getFileNum();
        xmlHeaderInfo.setDocmentId(xmlName);

        // 处置缘由列表
        String dealReasonStr = (String) result.get("DealReason");
        result.put("DealReason", this.getListValue(dealReasonStr));

        // 处置措施列表
        String dealMeasureStr = (String) result.get("DealMeasure");
        result.put("DealMeasure", this.getListValue(dealMeasureStr));

        //获取模板文件URL
        String templatePath = "templates\\EmergencyInfo\\Update_EmergencyInfo.vm";

        //输出到xml
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
        this.toMsgJson(info, xmlName);
        this.toReturnTxt(xmlName);
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
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.DELETE);
        //xml 文件id 业务分类-进行数据交换的机构代码-系统当前时间（yyyyMMddHHmmssSSS）
        // 由于当前队列存在高并发可能，会导致唯一ID失效，采用Redis取号器实现ID
        String xmlName = this.getFileNum();
        xmlHeaderInfo.setDocmentId(xmlName);

        //获取模板文件URL
        String templatePath = "templates\\EmergencyInfo\\Delete_EmergencyInfo.vm";

        //输出到xml
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
        this.toMsgJson(info, xmlName);
        this.toReturnTxt(xmlName);
    }

/*    @Value{"filePaht.xxx"}
    private String getToXmlpath''*/
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

    /*
     * TODO 文件取号算法
     *  按时分秒 HHmmss 作为key 且失效时间为 3秒
     * 取号进行原子操作 increment
     *     当取出的号<1000 正常返回
     *     当取出的号>=1000 丢弃该值 线程sleep 10毫秒 且key刷新为当前时刻的 HHmmss
     *     继续去取 直到取出为止
     * */

    //生成key HHmmss
    //increment
    //1000

    //while(取号值>=1000)
    //sleep 10ms
    //生成key HHmmss
    //取号值=increment
    //return 日期 +时间+ 取号值

    public String getFileNum(){
        String key = null;
        try {
            DecimalFormat df=new DecimalFormat("000");
            // 当前时间作为key
            key = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            // 当前数目
            int currNum =  redisTemplate.opsForValue().increment(key).intValue(); //redisTemplate.opsForValue()~ops

            while (currNum >= 1000) {
                Thread.sleep(10);
                key = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                currNum =  redisTemplate.opsForValue().increment(key).intValue();
            }
            String numStr = df.format(currNum);
            return businessCode +"-"+ reportOrgCode +"-"+ key+numStr;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
                dataCd = businessCode +"-" + reportOrgCode +"-"+ redisId;
            }else {
                b = true;
            }
        }catch (Exception e){
            b = true;
        }finally {
            if(b){
                dataCd = businessCode +"-"+ reportOrgCode +"-"+ StringUtils.getDateStr("yyyyMMddHHmmssSSS");
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
        Properties properties = new Properties();
        properties.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        properties.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

        ve.init(properties);

        /*ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        ve.init();*/

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


    // 查询数据字典 获取用逗号分隔的List对象
    private List<Map<String,Object>> getListValue(String infoStr){
        if (infoStr == null || infoStr.isEmpty()){
            return null;
        }
        String[] commaStrArr = infoStr.split(",");
        List<Map<String,Object>> outPutList = null;
        if (commaStrArr.length > 0){
            outPutList = new ArrayList<>();
            for(String x:commaStrArr){
                outPutList.add(dictInfoMapper.queryDictForNam(x));
            }
        }
        return outPutList;
    }

    // todo 消息交换 传递处理时的信息流转

    public void toMsgJson(MessageInfo info, String fileNam){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String content = gson.toJson(info);
        File file = new File(toMsgPath +"\\"+fileNam+".json");
        FileOutputStream o = null;
        try {
            o = new FileOutputStream(file,true);
            o.write(content.getBytes("utf-8"));
            o.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void toReturnTxt(String fileNam){

        StringBuffer sb = new StringBuffer();
        sb.append("文件名称:").append(fileNam).append(".xml\n");
        sb.append("交换信息: =").append("{\"result\":true,\"id\":\"XXXXXX\"}\n");
        sb.append("接受时间:=").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).append("\n");
        sb.append("消息状态:=").append("成功");
        String content = sb.toString();

        File file = new File(toReturnTxtPath +"\\"+fileNam+".txt");
        FileOutputStream o = null;
        try {
            o = new FileOutputStream(file,true);
            o.write(content.getBytes("utf-8"));
            o.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}


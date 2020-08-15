package com.hyd.subordtest.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hyd.subordtest.domain.enumtion.OperateEnum;
import com.hyd.subordtest.domain.info.MessageInfo;
import com.hyd.subordtest.domain.info.XmlHeaderInfo;
import com.hyd.subordtest.mapper.*;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Value("${config.docmentenum.license-code}")
    private String licenseCode;
    @Value("${config.docmentenum.reportOrg}")
    private String reportOrgCode;
    @Value("${config.docmentenum.reportOrgNam}")
    private String reportOrgNam;
    @Value("${config.docmentenum.reportZone}")
    private String reportZoneCode;
    @Value("${config.docmentenum.reportZoneNam}")
    private String reportZoneNam;

    @Value("${config.build}")
    private String buildPath;
    @Value("${config.business-code}")
    private String businessCode;
    @Value("${config.templateFilePath}")
    private String templateFilePath;


    // 出院需要报告卡主键
    // 随访需要患者主键  不满足扔到消息队尾
    // 12请求都走build ， 有主键生成upd 无主键生成add

    public void buildPatInfoToXml(MessageInfo info){
        Map<String, Object> result = basicInfoMapper.queryBasicInfoViewByCd(info.getId());
        if (result == null){
            log.info("当前ID无法转码,视图无此数据{}", info.getId());
            return;
        }
        boolean isadd = false;
        if(result.get("BasicInformationId") == null||result.get("BasicInformationId").equals("")){
            isadd = true;
        }
        //设置Xml头部分
        XmlHeaderInfo xmlHeaderInfo = new XmlHeaderInfo();
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.ADD);
        xmlHeaderInfo.setDocmentId(this.fileNumFromRedis());

        // 既往危险行为列表
        String pastRiskHaveStr = (String) result.get("PastRiskHave");
        result.put("PastRiskHave", this.getListValue(pastRiskHaveStr));
        String templatePath ="";
        if (isadd){
            templatePath = templateFilePath + "\\BasicInfo\\Add_BasicInfo.vm";
        }else {
            xmlHeaderInfo.setOperateEnum(OperateEnum.UPDATE);
            templatePath = templateFilePath + "\\BasicInfo\\Update_BasicInfo.vm";
        }
        info.setGwaction(isadd?1 : 2);
        String xmlName = xmlHeaderInfo.getDocmentId();
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
        this.toJson(info, xmlName);
    }

    /**
     * 接收删除档案消息并输出到Xml
     * @param info
     */
    public void delDocumentToXml(MessageInfo info){
        Map<String, Object> result = basicInfoMapper.queryBasicInfoViewOfDelete(info.getId());
        if(result==null){
            log.info("当前ID无法转码,视图无此数据{}", info.getId());
            return;
        }
        XmlHeaderInfo xmlHeaderInfo = new XmlHeaderInfo();
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.DELETE);
        xmlHeaderInfo.setDocmentId(this.fileNumFromRedis());
        String templatePath = templateFilePath + "\\BasicInfo\\Delete_BasicInfo.vm";
        String xmlName = xmlHeaderInfo.getDocmentId();
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
        this.toJson(info, xmlName);
    }

    /**
     *  接收数据恢复消息并输出到XMl
     * @param info
     */
    public void restoreDocumentToXml(MessageInfo info){
        Map<String, Object> result = basicInfoMapper.queryBasicInfoViewOfUndelete(info.getId());
        if(result==null){
            log.info("当前ID无法转码,视图无此数据{}", info.getId());
            return;
        }
        XmlHeaderInfo xmlHeaderInfo = new XmlHeaderInfo();
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.UNDELETE);
        xmlHeaderInfo.setDocmentId(this.fileNumFromRedis());
        String templatePath = templateFilePath + "\\BasicInfo\\Undelete_BasicInfo.vm";
        String xmlName = xmlHeaderInfo.getDocmentId();
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
        this.toJson(info, xmlName);
    }

    /**
     *  接收转死亡消息并输出到XMl
     * @param info
     */
    public void turnDeathDocumentToXml(MessageInfo info){
        Map<String, Object> result = basicInfoMapper.queryBasicInfoViewOfTrundeath(info.getId());
        if(result==null){
            log.info("当前ID无法转码,视图无此数据{}", info.getId());
            return;
        }
        XmlHeaderInfo xmlHeaderInfo = new XmlHeaderInfo();
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.DECLAREDEATH);
        xmlHeaderInfo.setDocmentId(this.fileNumFromRedis());
        String templatePath = templateFilePath + "\\BasicInfo\\Declaredeath_BasicInfo.vm";
        String xmlName = xmlHeaderInfo.getDocmentId();
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
        this.toJson(info, xmlName);
    }

    /**
     * 报告卡操作
     * @param info
     */
    public void buildReportToXml(MessageInfo info){
        Map<String, Object> result = reportInfoMapper.queryReportInfoViewByCd(info.getId());
        if (result == null){
            log.info("当前ID无法转码,视图无此数据{}", info.getId());
            return;
        }
        boolean isadd = false;
        if(result.get("NewCaseReportId") == null||result.get("NewCaseReportId").equals("")){
            isadd = true;
        }
        XmlHeaderInfo xmlHeaderInfo = new XmlHeaderInfo();
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.ADD);
        xmlHeaderInfo.setDocmentId(this.fileNumFromRedis());

        // 服药列表
        List<Map<String, Object>> drugList = reportInfoMapper.queryMedicationByNewCaseReportId(info.getId());
        result.put("DrugList",drugList);
        // 既往危险行为列表
        String pastRiskHaveStr = (String) result.get("PastRiskHave");
        result.put("PastRiskHave", this.getListValue(pastRiskHaveStr));
        // 送诊主体列表
        String sendClinSubStr = (String) result.get("SendClinSub");
        result.put("SendClinSub",this.getListValue(sendClinSubStr));

        String templatePath ="";
        if (isadd){
            templatePath = templateFilePath + "\\ReportInfo\\Add_ReportInfo.vm";
        }else {
            xmlHeaderInfo.setOperateEnum(OperateEnum.UPDATE);
            templatePath = templateFilePath + "\\ReportInfo\\Update_ReportInfo.vm";
        }
        info.setGwaction(isadd?1 : 2);
        String xmlName = xmlHeaderInfo.getDocmentId();
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
        this.toJson(info, xmlName);
    }


    public void delReportToXml(MessageInfo info) {
        Map<String, Object> result = reportInfoMapper.queryReportInfoViewOfDelete(info.getId());
        if(result==null){
            log.info("当前ID无法转码,视图无此数据{}", info.getId());
            return;
        }
        XmlHeaderInfo xmlHeaderInfo = new XmlHeaderInfo();
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.DELETE);
        xmlHeaderInfo.setDocmentId(this.fileNumFromRedis());
        String templatePath = templateFilePath + "\\ReportInfo\\Delete_ReportInfo.vm";
        String xmlName = xmlHeaderInfo.getDocmentId();
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
        this.toJson(info, xmlName);
    }

    /**
     * 出院报告卡操作
     */
    public void buildDischargeToXml(MessageInfo info){
        Map<String, Object> result = dischargeInfoMapper.queryDischargeInfoByCd(info.getId());
        if (result == null){
            log.info("当前ID无法转码,视图无此数据{}", info.getId());
            return;
        }
        boolean isadd = false;
        if(result.get("DischargeInformationId") == null||result.get("DischargeInformationId").equals("")){
            isadd = true;
        }
        XmlHeaderInfo xmlHeaderInfo = new XmlHeaderInfo();
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.ADD);
        xmlHeaderInfo.setDocmentId(this.fileNumFromRedis());

        // 用药情况列表
        List<Map<String, Object>> drugList = dischargeInfoMapper.queryDrugListByDischargeId(info.getId());
        result.put("DrugList",drugList);
        // 指导用药列表
        List<Map<String, Object>> guideDrugList = dischargeInfoMapper.queryGuideDrugListByDischargeId(info.getId());
        result.put("GuideDrugList",guideDrugList);

        String templatePath ="";
        if (isadd){
            templatePath = templateFilePath + "\\DischargeInfo\\Add_DischargeInfo.vm";
        }else {
            xmlHeaderInfo.setOperateEnum(OperateEnum.UPDATE);
            templatePath = templateFilePath + "\\DischargeInfo\\Update_DischargeInfo.vm";
        }
        info.setGwaction(isadd?1 : 2);
        String xmlName = xmlHeaderInfo.getDocmentId();
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
        this.toJson(info, xmlName);
    }

    public void delDischargeToXml(MessageInfo info) {
        Map<String, Object> result = dischargeInfoMapper.queryDischargeInfoOfDelete(info.getId());
        if(result==null){
            log.info("当前ID无法转码,视图无此数据{}", info.getId());
            return;
        }
        XmlHeaderInfo xmlHeaderInfo = new XmlHeaderInfo();
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.DELETE);
        xmlHeaderInfo.setDocmentId(this.fileNumFromRedis());
        String templatePath = templateFilePath + "\\DischargeInfo\\Delete_DischargeInfo.vm";
        String xmlName = xmlHeaderInfo.getDocmentId();
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
        this.toJson(info, xmlName);
    }

    /**
     * 流转信息操作
     */
    public void buildFollowupToXml(MessageInfo info){
        Map<String, Object> result = followupInfoMapper.queryFollowupInfoViewByCd(info.getId());
        if (result == null){
            log.info("当前ID无法转码,视图无此数据{}", info.getId());
            return;
        }
        boolean isadd = false;
        if(result.get("FollowUpInformationId") == null||result.get("FollowUpInformationId").equals("")){
            isadd = true;
        }
        XmlHeaderInfo xmlHeaderInfo = new XmlHeaderInfo();
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.ADD);
        xmlHeaderInfo.setDocmentId(this.fileNumFromRedis());

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

        String templatePath ="";
        if (isadd){
            templatePath = templateFilePath + "\\FollowupInfo\\Add_FollowupInfo.vm";
        }else {
            xmlHeaderInfo.setOperateEnum(OperateEnum.UPDATE);
            templatePath = templateFilePath + "\\FollowupInfo\\Update_FollowupInfo.vm";
        }
        info.setGwaction(isadd?1 : 2);
        String xmlName = xmlHeaderInfo.getDocmentId();
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
        this.toJson(info, xmlName);
    }

    public void delFollowupToXml(MessageInfo info) {
        Map<String, Object> result = followupInfoMapper.queryFollowupInfoViewOfDelete(info.getId());
        if(result==null){
            log.info("当前ID无法转码,视图无此数据{}", info.getId());
            return;
        }
        XmlHeaderInfo xmlHeaderInfo = new XmlHeaderInfo();
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.DELETE);
        xmlHeaderInfo.setDocmentId(this.fileNumFromRedis());
        String templatePath = templateFilePath + "\\FollowupInfo\\Delete_FollowupInfo.vm";
        String xmlName = xmlHeaderInfo.getDocmentId();
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
        this.toJson(info, xmlName);
    }

    /**
     * 应急处置操作
     */
    public void buildEmergencyToXml(MessageInfo info){
        Map<String, Object> result = emergencyInfoMapper.queryEmergencyInfoViewByCd(info.getId());
        if (result == null){
            log.info("当前ID无法转码,视图无此数据{}", info.getId());
            return;
        }
        boolean isadd = false;
        if(result.get("EmerDealInfoId") == null||result.get("EmerDealInfoId").equals("")){
            isadd = true;
        }
        XmlHeaderInfo xmlHeaderInfo = new XmlHeaderInfo();
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.ADD);
        xmlHeaderInfo.setDocmentId(this.fileNumFromRedis());

        // 处置缘由列表
        String dealReasonStr = (String) result.get("DealReason");
        result.put("DealReason", this.getListValue(dealReasonStr));
        // 处置措施列表
        String dealMeasureStr = (String) result.get("DealMeasure");
        result.put("DealMeasure", this.getListValue(dealMeasureStr));

        String templatePath ="";
        if (isadd){
            templatePath = templateFilePath + "\\EmergencyInfo\\Add_EmergencyInfo.vm";
        }else {
            xmlHeaderInfo.setOperateEnum(OperateEnum.UPDATE);
            templatePath = templateFilePath + "\\EmergencyInfo\\Update_EmergencyInfo.vm";
        }
        info.setGwaction(isadd?1 : 2);
        String xmlName = xmlHeaderInfo.getDocmentId();
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
    }

    public void delEmergencyToXml(MessageInfo info) {
        Map<String, Object> result = emergencyInfoMapper.queryEmergencyInfoViewOfDelete(info.getId());
        if(result==null){
            log.info("当前ID无法转码,视图无此数据{}", info.getId());
            return;
        }
        XmlHeaderInfo xmlHeaderInfo = new XmlHeaderInfo();
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.DELETE);
        xmlHeaderInfo.setDocmentId(this.fileNumFromRedis());
        String templatePath = templateFilePath + "\\EmergencyInfo\\Delete_EmergencyInfo.vm";
        String xmlName = xmlHeaderInfo.getDocmentId();
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
        this.toJson(info, xmlName);
    }

    /*
     *  文件取号算法
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

    public String fileNumFromRedis(){
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

    private void  toXML(XmlHeaderInfo info, Map<String, Object> map, String templatePath, String xmlName){
        Template template = this.getTemplate(templatePath);
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
        //TODO 生成流程XML
        //this.saveBuildXml(template, vc, xmlName);
        try {
            fileWriter = new FileWriter(new File(buildPath +"\\"+xmlName+".xml"));
            template.merge(vc, fileWriter);
        }catch (IOException e){
            log.error("构建xml文件失败,文件ID{},异常信息{}", xmlName, e.getMessage());
        }finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Template getTemplate(String templatePath){
        Template template = null;
        try {
            //由于加载模板文件需要配置文件夹路径和文件名,所以对路径进行切割
            String folderPath = templatePath.substring(0, templatePath.lastIndexOf("\\"));
            String tempalteName = templatePath.substring(templatePath.lastIndexOf("\\"));
            Properties p = new Properties();
            p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, folderPath);
            Velocity.init(p);
            template = Velocity.getTemplate(tempalteName);
        }catch (Exception e){
            log.info("获取模板文件失败,模板路径{}",templatePath);
        }
        return  template;
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

    private void toJson(MessageInfo info, String fileNam){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        info.setFileNam(fileNam);
        String content = gson.toJson(info);
        File file = new File(buildPath +"\\"+fileNam+".json");
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


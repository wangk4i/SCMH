package com.hyd.subordtest.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hyd.subordtest.domain.info.MessageInfo;
import com.hyd.subordtest.domain.enumtion.OperateEnum;
import com.hyd.subordtest.domain.info.XmlHeaderInfo;
import com.hyd.subordtest.mapper.*;
import com.hyd.subordtest.utils.LogUtil;
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
    @Value("${config.build}")
    private String buildPath;
    //业务分类编码
    @Value("${config.business-code}")
    private String businessCode;
    // 模板xml路径
    @Value("${config.templateFilePath}")
    private String templateFilePath;
    @Value("${config.returnTxtPath}")
    private String toReturnTxtPath;




    /**
     * 接收新增档案消息并输出到Xml
     * @param info
     */
    public boolean buildPatInfoToXml(MessageInfo info){
        Map<String, Object> result = basicInfoMapper.queryBasicInfoViewByCd(info.getId());
        if (result == null){
            log.info("当前ID无法转码,视图无此数据{}", info.getId());
            LogUtil.info("当前ID无法转码,视图无此数据 "+ info.getId()+"\n");
            return false;
        }
        boolean isadd = false;
        if(result.get("BasicInformationId") == null||result.get("BasicInformationId").equals("")){
            isadd = true;
        }
        //设置Xml头部分
        XmlHeaderInfo xmlHeaderInfo = new XmlHeaderInfo();
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.ADD);
        String xmlName = this.getFileNum();
        xmlHeaderInfo.setDocmentId(xmlName);

        // 既往危险行为列表
        String pastRiskHaveStr = (String) result.get("PastRiskHave");
        result.put("PastRiskHave", this.getListValue(pastRiskHaveStr));
        String templatePath ="";
        if (isadd){
            info.setMsgaction(1);
            templatePath = templateFilePath + "\\BasicInfo\\Add_BasicInfo.vm";
        }else {
            info.setMsgaction(2);
            xmlHeaderInfo.setOperateEnum(OperateEnum.UPDATE);
            templatePath = templateFilePath + "\\BasicInfo\\Update_BasicInfo.vm";
        }
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
        this.toMsgJson(info, xmlName);
        this.toReturnTxt(xmlName);
        basicInfoMapper.setSyncStatus(info.getId());
        return true;
    }

    /**
     * 接收删除档案消息并输出到Xml
     * @param info
     */
    public boolean delDocumentToXml(MessageInfo info){
        //去数据库查询数据
        Map<String, Object> result = basicInfoMapper.queryBasicInfoViewOfDelete(info.getId());

        //判断查询结果是否为Null
        if(result==null){
            log.info("当前ID无法转码,视图无此数据{}", info.getId());
            LogUtil.info("当前ID无法转码,视图无此数据 "+ info.getId()+"\n");
            return false;
        }

        //获取头部数据
        XmlHeaderInfo xmlHeaderInfo = new XmlHeaderInfo();
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.DELETE);
        //xml 文件id 业务分类-进行数据交换的机构代码-系统当前时间（yyyyMMddHHmmssSSS）
        // 由于当前队列存在高并发可能，会导致唯一ID失效，采用Redis取号器实现ID
        String xmlName = this.getFileNum();
        xmlHeaderInfo.setDocmentId(xmlName);

        //获取模板文件URL
        String templatePath = templateFilePath + "\\BasicInfo\\Delete_BasicInfo.vm";

        //输出到xml
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
        this.toMsgJson(info, xmlName);
        this.toReturnTxt(xmlName);
        return true;

    }

    /**
     *  接收数据恢复消息并输出到XMl
     * @param info
     */
    public boolean restoreDocumentToXml(MessageInfo info){

        //去数据库查询数据
        Map<String, Object> result = basicInfoMapper.queryBasicInfoViewOfUndelete(info.getId());
        //判断查询结果是否为Null
        if(result==null){
            log.info("当前ID无法转码,视图无此数据{}", info.getId());
            LogUtil.info("当前ID无法转码,视图无此数据 "+ info.getId()+"\n");
            return false;
        }

        //获取头部数据
        XmlHeaderInfo xmlHeaderInfo = new XmlHeaderInfo();
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.UNDELETE);
        //xml 文件id 业务分类-进行数据交换的机构代码-系统当前时间（yyyyMMddHHmmssSSS）
        // 由于当前队列存在高并发可能，会导致唯一ID失效，采用Redis取号器实现ID
        String xmlName = this.getFileNum();
        xmlHeaderInfo.setDocmentId(xmlName);

        //获取模板文件URL
        String templatePath = templateFilePath + "\\BasicInfo\\Undelete_BasicInfo.vm";


        //输出到xml
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
        this.toMsgJson(info, xmlName);
        this.toReturnTxt(xmlName);
        return true;
    }

    /**
     *  接收转死亡消息并输出到XMl
     * @param info
     */
    public boolean turnDeathDocumentToXml(MessageInfo info){
        //去数据库查询数据
        Map<String, Object> result = basicInfoMapper.queryBasicInfoViewOfTrundeath(info.getId());
        //判断查询结果是否为Null
        if(result==null){
            log.info("当前ID无法转码,视图无此数据{}", info.getId());
            LogUtil.info("当前ID无法转码,视图无此数据 "+ info.getId()+"\n");
            return false;
        }
        //获取头部数据
        XmlHeaderInfo xmlHeaderInfo = new XmlHeaderInfo();
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.DECLAREDEATH);
        //xml 文件id 业务分类-进行数据交换的机构代码-系统当前时间（yyyyMMddHHmmssSSS）
        // 由于当前队列存在高并发可能，会导致唯一ID失效，采用Redis取号器实现ID
        String xmlName = this.getFileNum();
        xmlHeaderInfo.setDocmentId(xmlName);

        //获取模板文件URL
        String templatePath = templateFilePath + "\\BasicInfo\\Declaredeath_BasicInfo.vm";



        //输出到xml
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
        this.toMsgJson(info, xmlName);
        this.toReturnTxt(xmlName);
        return true;
    }

    /**
     * 报告卡操作
     * @param info
     */
    public boolean buildReportToXml(MessageInfo info){
        Map<String, Object> result = reportInfoMapper.queryReportInfoViewByCd(info.getId());
        if (result == null){
            log.info("当前ID无法转码,视图无此数据{}", info.getId());
            LogUtil.info("当前ID无法转码,视图无此数据 "+ info.getId());
            return false;
        }
        boolean isadd = false;
        if(result.get("NewCaseReportId") == null||result.get("NewCaseReportId").equals("")){
            isadd = true;
        }
        XmlHeaderInfo xmlHeaderInfo = new XmlHeaderInfo();
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.ADD);
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

        String templatePath ="";
        if (isadd){
            info.setMsgaction(1);
            templatePath = templateFilePath + "\\ReportInfo\\Add_ReportInfo.vm";
        }else {
            info.setMsgaction(2);
            xmlHeaderInfo.setOperateEnum(OperateEnum.UPDATE);
            templatePath = templateFilePath + "\\ReportInfo\\Update_ReportInfo.vm";
        }

        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
        this.toMsgJson(info, xmlName);
        this.toReturnTxt(xmlName);
        reportInfoMapper.setSyncStatus(info.getId());
        return true;
    }

    public boolean delReportToXml(MessageInfo info) {
        //去数据库查询数据
        Map<String, Object> result = reportInfoMapper.queryReportInfoViewOfDelete(info.getId());
        //判断查询结果是否为Null
        if(result==null){
            log.info("当前ID无法转码,视图无此数据{}", info.getId());
            LogUtil.info("当前ID无法转码,视图无此数据 "+ info.getId());
            return false;
        }
        //获取头部数据
        XmlHeaderInfo xmlHeaderInfo = new XmlHeaderInfo();
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.DELETE);
        //xml 文件id 业务分类-进行数据交换的机构代码-系统当前时间（yyyyMMddHHmmssSSS）
        // 由于当前队列存在高并发可能，会导致唯一ID失效，采用Redis取号器实现ID
        String xmlName = this.getFileNum();
        xmlHeaderInfo.setDocmentId(xmlName);

        //获取模板文件URL
        String templatePath = templateFilePath + "\\ReportInfo\\Delete_ReportInfo.vm";

        //输出到xml
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
        this.toMsgJson(info, xmlName);
        this.toReturnTxt(xmlName);
        return true;
    }

    /**
     * 出院报告卡操作
     */
    public boolean buildDischargeToXml(MessageInfo info){
        Map<String, Object> result = dischargeInfoMapper.queryDischargeInfoByCd(info.getId());
        if (result == null){
            LogUtil.info("当前ID无法转码,视图无此数据 "+ info.getId()+"\n");
            return false;
        }
        if (result.get("CaseReportId")==null||result.get("CaseReportId").equals("")){
            log.info("当前ID缺少报告卡主键{}", info.getId());
            LogUtil.info("当前ID缺少报告卡主键{}" + info.getId());
            return false;
        }

        boolean isadd = false;
        if(result.get("DischargeInformationId") == null||result.get("DischargeInformationId").equals("")){
            isadd = true;
        }
        XmlHeaderInfo xmlHeaderInfo = new XmlHeaderInfo();
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.ADD);
        String xmlName = this.getFileNum();
        xmlHeaderInfo.setDocmentId(xmlName);

        // 用药情况列表
        List<Map<String, Object>> drugList = dischargeInfoMapper.queryDrugListByDischargeId(info.getId());
        result.put("DrugList",drugList);
        // 指导用药列表
        List<Map<String, Object>> guideDrugList = dischargeInfoMapper.queryGuideDrugListByDischargeId(info.getId());
        result.put("GuideDrugList",guideDrugList);

        String templatePath ="";
        if (isadd){
            info.setMsgaction(1);
            templatePath = templateFilePath + "\\DischargeInfo\\Add_DischargeInfo.vm";
        }else {
            info.setMsgaction(2);
            xmlHeaderInfo.setOperateEnum(OperateEnum.UPDATE);
            templatePath = templateFilePath + "\\DischargeInfo\\Update_DischargeInfo.vm";
        }

        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
        this.toMsgJson(info, xmlName);
        this.toReturnTxt(xmlName);
        dischargeInfoMapper.setSyncStatus(info.getId());
        return true;
    }

    public boolean delDischargeToXml(MessageInfo info) {
        //去数据库查询数据
        Map<String, Object> result = dischargeInfoMapper.queryDischargeInfoOfDelete(info.getId());
        //判断查询结果是否为Null
        if(result==null){
            log.info("当前ID无法转码,视图无此数据{}", info.getId());
            LogUtil.info("当前ID无法转码,视图无此数据 "+ info.getId()+"\n");
            return false;
        }

        //获取头部数据
        XmlHeaderInfo xmlHeaderInfo = new XmlHeaderInfo();
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.DELETE);
        //xml 文件id 业务分类-进行数据交换的机构代码-系统当前时间（yyyyMMddHHmmssSSS）
        // 由于当前队列存在高并发可能，会导致唯一ID失效，采用Redis取号器实现ID
        String xmlName = this.getFileNum();
        xmlHeaderInfo.setDocmentId(xmlName);

        //获取模板文件URL
        String templatePath = templateFilePath + "\\DischargeInfo\\Delete_DischargeInfo.vm";

        //输出到xml
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
        this.toMsgJson(info, xmlName);
        this.toReturnTxt(xmlName);
        return true;
    }

    /**
     * 流转信息操作
     */
    public boolean buildFollowupToXml(MessageInfo info){
        Map<String, Object> result = followupInfoMapper.queryFollowupInfoViewByCd(info.getId());
        if (result == null){
            log.info("当前ID无法转码,视图无此数据{}", info.getId());
            LogUtil.info("当前ID无法转码,视图无此数据 " + info.getId());
            return false;
        }
        if (result.get("BasicInformationId")==null||result.get("BasicInformationId").equals("")){
            log.info("当前ID缺少患者主键{}", info.getId());
            LogUtil.info("当前ID缺少患者主键 " + info.getId());
            return false;
        }

        boolean isadd = false;
        if(result.get("FollowUpInformationId") == null||result.get("FollowUpInformationId").equals("")){
            isadd = true;
        }
        XmlHeaderInfo xmlHeaderInfo = new XmlHeaderInfo();
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.ADD);
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

        String templatePath ="";
        if (isadd){
            info.setMsgaction(1);
            templatePath = templateFilePath + "\\FollowupInfo\\Add_FollowupInfo.vm";
        }else {
            info.setMsgaction(2);
            xmlHeaderInfo.setOperateEnum(OperateEnum.UPDATE);
            templatePath = templateFilePath + "\\FollowupInfo\\Update_FollowupInfo.vm";
        }
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
        this.toMsgJson(info, xmlName);
        this.toReturnTxt(xmlName);
        followupInfoMapper.setSyncStatus(info.getId());
        return true;
    }

    public boolean delFollowupToXml(MessageInfo info) {
        //去数据库查询数据
        Map<String, Object> result = followupInfoMapper.queryFollowupInfoViewOfDelete(info.getId());
        //判断查询结果是否为Null
        if(result==null){
            log.info("当前ID无法转码,视图无此数据{}", info.getId());
            LogUtil.info("当前ID无法转码,视图无此数据 "+ info.getId()+"\n");
            return false;
        }

        //获取头部数据
        XmlHeaderInfo xmlHeaderInfo = new XmlHeaderInfo();
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.DELETE);
        //xml 文件id 业务分类-进行数据交换的机构代码-系统当前时间（yyyyMMddHHmmssSSS）
        // 由于当前队列存在高并发可能，会导致唯一ID失效，采用Redis取号器实现ID
        String xmlName = this.getFileNum();
        xmlHeaderInfo.setDocmentId(xmlName);

        //获取模板文件URL
        String templatePath = templateFilePath + "\\FollowupInfo\\Delete_FollowupInfo.vm";

        //输出到xml
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
        this.toMsgJson(info, xmlName);
        this.toReturnTxt(xmlName);
        return true;
    }

    /**
     * 应急处置操作
     */
    public boolean buildEmergencyToXml(MessageInfo info){
        Map<String, Object> result = emergencyInfoMapper.queryEmergencyInfoViewByCd(info.getId());
        if (result == null){
            log.info("当前ID无法转码,视图无此数据{}", info.getId());
            return false;
        }
        boolean isadd = false;
        if(result.get("EmerDealInfoId") == null||result.get("EmerDealInfoId").equals("")){
            isadd = true;
        }
        XmlHeaderInfo xmlHeaderInfo = new XmlHeaderInfo();
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.ADD);
        String xmlName = this.getFileNum();
        xmlHeaderInfo.setDocmentId(xmlName);

        // 处置缘由列表
        String dealReasonStr = (String) result.get("DealReason");
        result.put("DealReason", this.getListValue(dealReasonStr));
        // 处置措施列表
        String dealMeasureStr = (String) result.get("DealMeasure");
        result.put("DealMeasure", this.getListValue(dealMeasureStr));

        String templatePath ="";
        if (isadd){
            info.setMsgaction(1);
            templatePath = templateFilePath + "\\EmergencyInfo\\Add_EmergencyInfo.vm";
        }else {
            info.setMsgaction(2);
            xmlHeaderInfo.setOperateEnum(OperateEnum.UPDATE);
            templatePath = templateFilePath + "\\EmergencyInfo\\Update_EmergencyInfo.vm";
        }
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
        this.toMsgJson(info, xmlName);
        this.toReturnTxt(xmlName);
        emergencyInfoMapper.setSyncStatus(info.getId());
        return true;
    }

    public boolean delEmergencyToXml(MessageInfo info) {
        //去数据库查询数据
        Map<String, Object> result = emergencyInfoMapper.queryEmergencyInfoViewOfDelete(info.getId());
        //判断查询结果是否为Null
        if(result==null){
            log.info("当前ID无法转码,视图无此数据{}", info.getId());
            LogUtil.info("当前ID无法转码,视图无此数据 "+ info.getId()+"\n");
            return false;
        }

        //获取头部数据
        XmlHeaderInfo xmlHeaderInfo = new XmlHeaderInfo();
        xmlHeaderInfo.setXmlHeaderInfo("MentalHealth", reportZoneCode, reportZoneNam, reportOrgCode, reportOrgNam, licenseCode, OperateEnum.DELETE);
        //xml 文件id 业务分类-进行数据交换的机构代码-系统当前时间（yyyyMMddHHmmssSSS）
        // 由于当前队列存在高并发可能，会导致唯一ID失效，采用Redis取号器实现ID
        String xmlName = this.getFileNum();
        xmlHeaderInfo.setDocmentId(xmlName);

        //获取模板文件URL
        String templatePath = templateFilePath + "\\EmergencyInfo\\Delete_EmergencyInfo.vm";

        //输出到xml
        this.toXML(xmlHeaderInfo, result, templatePath, xmlName);
        this.toMsgJson(info, xmlName);
        this.toReturnTxt(xmlName);
        return true;
    }

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
        try {
            fileWriter = new FileWriter(new File(buildPath +"\\"+xmlName+".xml"));
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

    public void toMsgJson(MessageInfo info, String fileNam){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
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

    public void toReturnTxt(String fileNam){

        StringBuffer sb = new StringBuffer();
        sb.append("文件名称:").append(fileNam).append(".xml\n")
                .append("交换信息:=").append("{\"result\":true,\"id\":\"XXXXXX\"}\n")
        //sb.append("接受时间:=").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).append("\n");
                .append("接受时间:=").append("2018-12-31 00:00:00").append("\n")
                .append("消息状态:=").append("成功");
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

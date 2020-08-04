package com.hyd.gwinterfaceserver.patrptcard.vo;

import lombok.Data;

import java.util.List;

@Data
public class CaseReport2 {

    /// <summary>
    /// 患者报告卡的主键
    /// </summary>
    public String newCaseReportId;
    /// <summary>
    /// 患者基本信息的主键
    /// </summary>
    public String basicInformationId;
    /// <summary>
    /// 报告地区
    /// </summary>
    public String orgCountyCode;
    /// <summary>
    /// 报告单位
    /// </summary>
    public String orgCode;
    /// <summary>
    ///  卡片编号(患者报告卡号)
    /// </summary>
    public String newCaseReportNumber;
    /// <summary>
    /// 患者来源(报告部门)
    /// </summary>
    public String division;
    /// <summary>
    /// 患者姓名
    /// </summary>
    public String patientName;
    /// <summary>
    /// 性别
    /// </summary>
    public String genderCode;
    /// <summary>
    /// 出生日期
    /// </summary>
    public String birthDate;
    /// <summary>
    /// 证件类型
    /// </summary>
    public String iDType;
    /// <summary>
    /// 国籍
    /// </summary>
    public String nationality;
    /// <summary>
    /// 证件号码
    /// </summary>
    public String iDCode;
    /// <summary>
    /// 民族
    /// </summary>
    public String nation;
    /// <summary>
    /// 户别
    /// </summary>
    public String registertype;
    /// <summary>
    /// 户别不详原因
    /// </summary>
    public String registertypeCause;
    /// <summary>
    /// 联系人姓名
    /// </summary>
    public String guardianName;
    /// <summary>
    /// 联系人电话
    /// </summary>
    public String guardianTel;
    /// <summary>
    /// 联系人电话（副）
    /// </summary>
    public String guardianTelVice;
    /// <summary>
    /// 与患者关系
    /// </summary>
    public String relationship;
    /// <summary>
    /// 户籍地
    /// </summary>
    public String domicileAdrressDetails;
    /// <summary>
    /// 户籍地址类型
    /// </summary>
    public String domicileAddressAttributionCode;
    /// <summary>
    /// 户籍国标码
    /// </summary>
    public String domicileAddressCode;
    /// <summary>
    /// 现住址
    /// </summary>
    public String livingAddressDetails;
    /// <summary>
    /// 现住址地区类型
    /// </summary>
    public String livingAddressAttributionCode;
    /// <summary>
    /// 现住地国标码
    /// </summary>
    public String livingAddressCode;
    /// <summary>
    /// 文化程度
    /// </summary>
    public String educationback;
    /// <summary>
    /// 婚姻状况
    /// </summary>
    public String marriage;
    /// <summary>
    /// 就业情况
    /// </summary>
    public String employment;
    /// <summary>
    /// 两系三代精神疾病家族史
    /// </summary>
    public String familyHistory;
    /// <summary>
    /// 初次发病时间
    /// </summary>
    public String firstOnsetDate;
    /// <summary>
    /// 初次发病时间精确度
    /// </summary>
    public String firstOnsetDateAccuracy;
    /// <summary>
    /// 是否已进行抗精神病药物治疗
    /// </summary>
    public String ifCure;
    /// <summary>
    /// 首次抗精神病药治疗时间
    /// </summary>
    public String firstCureTime;
    /// <summary>
    /// 首次抗精神病药治疗时间精确度
    /// </summary>
    public String firstMedicineTimeAccuracy;
    /// <summary>
    /// 既往住院情况
    /// </summary>
    public String hospitalStateHistory;
    /// <summary>
    /// 既往关锁情况
    /// </summary>
    public String shutStatusHistory;
    /// <summary>
    /// 既往危险行为
    /// </summary>
    public String pastRiskhave;
    /// <summary>
    /// 既往危险性评估
    /// </summary>
    public String riskPast;
    /// <summary>
    /// 送诊主体
    /// </summary>
    public String sendDiagnosis;
    /// <summary>
    /// 送诊主体—其他
    /// </summary>
    public String sendDiagnosisOther;
    /// <summary>
    /// 确诊医院
    /// </summary>
    public String diagnosisHospital;
    /// <summary>
    /// 确诊日期
    /// </summary>
    public String diagnosisDate;
    /// <summary>
    /// 疾病名称及ICD10编码
    /// </summary>
    public String icd10Code;
    /// <summary>
    /// 知情同意
    /// </summary>
    public String nformed;
    /// <summary>
    /// 知情同意时间
    /// </summary>
    public String informedDate;
    /// <summary>
    /// 填卡医生
    /// </summary>
    public String fillingDoctorName;
    /// <summary>
    /// 填卡日期
    /// </summary>
    public String cardFillingTime;
    /// <summary>
    /// 报告单位及科室
    /// </summary>
    public String reportDepartment;
    /// <summary>
    /// 科室联系电话
    /// </summary>
    public String reportDepartmentTel;
    /// <summary>
    /// 勿需用药
    /// </summary>
    public String ifNomedication;

    //        [XmlArray("MedicationList")]
//        [XmlArrayItem("Medication")]
    public List<Medication> medicationList;

    public String iD;

    public String syncStatus;

    public String syncError;
}
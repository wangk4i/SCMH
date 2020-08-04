package com.hyd.gwinterfaceserver.patinfo.vo;

import lombok.Data;

@Data
public class PatInfo2 {

    /// <summary>
    /// ContactInformation
    /// </summary>
    public String contactInformation;

    ///<summary>
    ///既往住院情况—曾住精神专科医院/综合医院精神科次数
    ///</summary>
    public String inMentalHospital;
    ///<summary>
    ///是否为家庭医师签约服务对象
    ///</summary>
    public String ifFamilyphysician;
    ///<summary>
    ///患者基本信息的主键
    ///</summary>
    public String dischargeInformationId;
    ///<summary>
    ///管理地区
    ///</summary>
    public String orgCountyCode;
    public String zoneNam;
    ///<summary>
    ///管理单位
    ///</summary>
    public String orgCode;
    public String orgNam;
    ///<summary>
    ///患者编号
    ///</summary>
    public String basicInformationNumber;
    ///<summary>
    ///患者姓名
    ///</summary>
    public String patientName;
    ///<summary>
    ///证件类型
    ///</summary>
    public String idType;
    ///<summary>
    ///证件号码
    ///</summary>
    public String idCode;
    ///<summary>
    ///国籍
    ///</summary>
    public String nationality;
    ///<summary>
    ///性别
    ///</summary>
    public String genderCode;
    ///<summary>
    ///出生日期
    ///</summary>
    public String birthDate;
    ///<summary>
    ///监护人姓名
    ///</summary>
    public String guardianName;
    ///<summary>
    ///监护人电话
    ///</summary>
    public String guardianTel;
    ///<summary>
    ///监护人与患者关系
    ///</summary>
    public String relationToPatient;
    ///<summary>
    ///两系三代精神疾病家族史
    ///</summary>
    public String familyHistory;
    ///<summary>
    ///婚姻状况
    ///</summary>
    public String maritalStatusCode;
    ///<summary>
    ///民族
    ///</summary>
    public String nationCode;
    ///<summary>
    ///就业情况(职业)
    ///</summary>
    public String occupationCode;
    ///<summary>
    ///文化程度
    ///</summary>
    public String educationLevelCode;
    ///<summary>
    ///现住地类别
    ///</summary>
    public String currentAddressType;
    ///<summary>
    ///现住址
    ///</summary>
    public String livingAddressDetails;
    ///<summary>
    ///病人属于
    ///</summary>
    public String livingAddressAttributionCode;
    ///<summary>
    ///现住地国标码
    ///</summary>
    public String livingAddressCode;
    ///<summary>
    ///户籍详细地址
    ///</summary>
    public String domicileAdrressDetails;
    ///<summary>
    ///户籍地址类型
    ///</summary>
    public String domicileAddressAttributionCode;
    ///<summary>
    ///户籍国标码
    ///</summary>
    public String domicileAddressCode;
    ///<summary>
    ///户别
    ///</summary>
    public String registertype;
    ///<summary>
    ///户别不详原因
    ///</summary>
    public String unknownCause;
    ///<summary>
    ///目前诊断ICD10编码
    ///</summary>
    public String diagnosis;
    ///<summary>
    ///确诊医院
    ///</summary>
    public String diagnosisHospital;
    ///<summary>
    ///确诊医院不详
    ///</summary>
    public String unknownHospita;
    ///<summary>
    ///确诊医院不详原因
    ///</summary>
    public String unknownReason;
    ///<summary>
    ///确诊日期
    ///</summary>
    public String diagnosisDate;
    ///<summary>
    ///确诊日期精确度
    ///</summary>
    public String diagnosisDateAccuracy;
    ///<summary>
    ///初次发病时间
    ///</summary>
    public String firstOnsetDate;
    ///<summary>
    ///初次发病时间精确度
    ///</summary>
    public String firstOnsetDateAccuracy;
    ///<summary>
    ///是否已进行抗精神病药物治疗
    ///</summary>
    public String mentalMedicineTreatment;
    ///<summary>
    ///首次抗精神病药治疗时间
    ///</summary>
    public String firstMedicineTime;
    ///<summary>
    ///首次抗精神病药治疗时间精确度
    ///</summary>
    public String firstMedicineTimeAccuracy;
    ///<summary>
    ///既往住院情况—曾住精神专科医院/综合医院精神科次数
    ///
    ///<summary>
    ///既往关锁情况
    ///</summary>
    public String inCaptivity;
    ///<summary>
    ///既往危险行为
    ///</summary>
    public String pastRiskhave;
    ///<summary>
    ///家庭经济状况
    ///</summary>
    public String familyEconomics;
    ///<summary>
    ///知情同意
    ///</summary>
    public String informedConsent;
    ///<summary>
    ///知情同意书签字时间
    ///</summary>
    public String signedInformedConsentDate;
    ///<summary>
    ///死亡日期
    ///</summary>
    public String deathDate;
    ///<summary>
    ///死亡原因
    ///</summary>
    public String deathCause;
    ///<summary>
    ///死亡原因明细
    ///</summary>
    public String deadDetail;
    ///<summary>
    ///是否为精准扶贫对象
    ///</summary>
    public String ifPoverty;
    ///<summary>
    ///是否为监护补助对象
    ///</summary>
    public String ifSubsidies;
    ///<summary>
    ///领取残疾人证情况
    ///</summary>
    public String disabilitycardInfo;
    ///<summary>
    ///精神残疾人证等级
    ///</summary>
    public String disabilityGrade;
    ///<summary>
    ///是否为关爱帮扶小组对象
    ///</summary>
    public String ifSupportgroup;
    ///<summary>
    ///是否参加社区康复服务
    ///</summary>
    public String ifRecovery;

    public String id;


    public String unknownHospital;

}
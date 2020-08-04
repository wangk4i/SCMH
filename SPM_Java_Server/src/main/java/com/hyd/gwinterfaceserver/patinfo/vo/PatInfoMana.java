package com.hyd.gwinterfaceserver.patinfo.vo;

public class PatInfoMana {
    ///<summary>
    ///既往住院情况—曾住精神专科医院/综合医院精神科次数
    ///</summary>
    public String inMentalHos;
    ///<summary>
    ///是否为家庭医师签约服务对象
    ///</summary>
    /*[Dict(DictType = "Whether")]*/
    public String ifFamilyphysician;
    ///<summary>
    ///患者基本信息的主键
    ///</summary>
    public String fiELDPK;
    ///<summary>
    ///管理地区
    ///</summary>
    public String zoneCd;
    ///<summary>
    ///管理地区
    ///</summary>
    public String zoneNam;
    ///<summary>
    ///管理单位
    ///</summary>
    public String organCd;
    ///<summary>
    ///管理单位
    ///</summary>
    public String orgNam;
    ///<summary>
    ///患者编号
    ///</summary>
    public String patCode;
    ///<summary>
    ///患者姓名
    ///</summary>
    public String patNam;
    ///<summary>
    ///证件类型
    ///</summary>
    /*[Dict(DictType = "CertType")]*/
    public String idTypeCd;
    ///<summary>
    ///证件号码
    ///</summary>
    public String idCode;
    ///<summary>
    ///国籍
    ///</summary>
    /*[Dict(DictType = "Nationality")]*/
    public String nationalityCd;
    ///<summary>
    ///性别
    ///</summary>
    /*[Dict(DictType = "Sex")]*/
    public String genderCd;
    ///<summary>
    ///出生日期
    ///</summary>
    public String birthDate;
    ///<summary>
    ///监护人姓名
    ///</summary>
    public String guardianNam;
    ///<summary>
    ///监护人电话
    ///</summary>
    public String guardianTel;
    ///<summary>
    ///监护人与患者关系
    ///</summary>
    /*[Dict(DictType = "RelToPat")]*/
    public String relToPatient;
    ///<summary>
    ///两系三代精神疾病家族史
    ///</summary>
    /*[Dict(DictType = "PresUnk")]*/
    public String famHistoryCd;
    ///<summary>
    ///婚姻状况
    ///</summary>
    /*[Dict(DictType = "Marriage")]*/
    public String marriageCd;
    ///<summary>
    ///民族
    ///</summary>
    /*[Dict(DictType = "Nation")]*/
    public String nationCd;
    ///<summary>
    ///就业情况(职业)
    ///</summary>
    /*[Dict(DictType = "Professional")]*/
    public String occupationCd;
    ///<summary>
    ///文化程度
    ///</summary>
    /*[Dict(DictType = "EduLevel")]*/
    public String educationCd;
    ///<summary>
    ///现住地类别
    ///</summary>
    /*[Dict(DictType = "CurrAddrType")]*/
    public String currentAddressType;
    ///<summary>
    ///现住址
    ///</summary>
    public String laddress;
    ///<summary>
    ///病人属于
    ///</summary>
    /*[Dict(DictType = "PatBelongs")]*/
    public String laddrTpCd;
    ///<summary>
    ///现住地国标码
    ///</summary>
    public String ltownCd;
    ///<summary>
    ///户籍详细地址
    ///</summary>
    public String daddress;
    ///<summary>
    ///户籍地址类型
    ///</summary>
    /*[Dict(DictType = "PatBelongs")]*/
    public String daddrTpCd;
    ///<summary>
    ///户籍国标码
    ///</summary>
    public String dtownCd;
    ///<summary>
    ///户别
    ///</summary>
    /*[Dict(DictType = "ResidenceTp")]*/
    public String registertypeCd;
    ///<summary>
    ///户别不详原因
    ///</summary>
    public String unknownCause;
    ///<summary>
    ///目前诊断ICD10编码
    ///</summary>
    public String diagICD;
    ///<summary>
    ///确诊医院
    ///</summary>
    public String diagnosisHos;
    /////<summary>
    /////确诊医院不详
    /////</summary>
    //public String UnknownHospita ;
    /////<summary>
    /////确诊医院不详原因
    /////</summary>
    //public String UnknownReason ;
    ///<summary>
    ///确诊日期
    ///</summary>
    public String diagnosisDate;
    ///<summary>
    ///确诊日期精确度
    ///</summary>
    /*[Dict(DictType = "Timpre")]*/
    public String diagAccuracyCd;
    ///<summary>
    ///初次发病时间
    ///</summary>
    public String firstOnsetDate;
    ///<summary>
    ///初次发病时间精确度
    ///</summary>
    /*[Dict(DictType = "Timpre")]*/
    public String accuracyCd;
    ///<summary>
    ///是否已进行抗精神病药物治疗
    ///</summary>
    /*[Dict(DictType = "Whether")]*/
    public String ifTreatCd;
    ///<summary>
    ///首次抗精神病药治疗时间
    ///</summary>
    public String firstMedTime;
    ///<summary>
    ///首次抗精神病药治疗时间精确度
    ///</summary>
    /*[Dict(DictType = "Timpre")]*/
    public String medAccuracyCd;
    ///<summary>
    ///既往住院情况—曾住精神专科医院/综合医院精神科次数
    ///
    ///<summary>
    ///既往关锁情况
    ///</summary>
    /*[Dict(DictType = "BasInLock")]*/
    public String inCaptivity;
    ///<summary>
    ///既往危险行为
    ///</summary>
    /*[Dict(DictType = "Dangerous",IsMult = true)]*/
    public String riskActCd;
    ///<summary>
    ///家庭经济状况
    ///</summary>
    /*[Dict(DictType = "EconSit")]*/
    public String famEcoCd;
    ///<summary>
    ///知情同意
    ///</summary>
    /*[Dict(DictType = "InformCon")]*/
    public String informedConsCd;
    ///<summary>
    ///知情同意书签字时间
    ///</summary>
    public String icSignDate;
    ///<summary>
    ///死亡日期
    ///</summary>
    public String deathDate;
    ///<summary>
    ///死亡原因编号
    ///</summary>
    /*[Dict(DictType = "Deathcau2")]*/
    public String deathCauseCd;


    public String deathCause;

    ///<summary>
    ///死亡原因明细编号
    ///</summary>
    public String deadDetailCd;


    ///<summary>
    ///死亡原因明细
    ///</summary>
    public String deadDetail;


    public String baseStatus;


    ///<summary>
    ///是否为精准扶贫对象
    ///</summary>
    /*[Dict(DictType = "Whether")]*/
    public String ifPoverty;
    ///<summary>
    ///是否为监护补助对象
    ///</summary>
    /*[Dict(DictType = "Whether")]*/
    public String ifSubsidies;
    ///<summary>
    ///领取残疾人证情况
    ///</summary>
    /*[Dict(DictType = "Disability")]*/
    public String disabilitycardInfo;
    ///<summary>
    ///精神残疾人证等级
    ///</summary>
    /*[Dict(DictType = "Disgrade")]*/
    public String disabilityGrade;
    ///<summary>
    ///是否为关爱帮扶小组对象
    ///</summary>
    /*[Dict(DictType = "Whether")]*/
    public String ifSupportgroup;
    ///<summary>
    ///是否参加社区康复服务
    ///</summary>
    /*[Dict(DictType = "Whether")]*/
    public String ifRecovery;

    public String baseStatusCd;
    public String delStatus;
    public String cd;
    public String state;
    public String creTime;
    public String creaCd;
    public String syncStatus;
    public String syncTime;
    public String syncError;
}
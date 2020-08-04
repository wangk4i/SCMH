package com.hyd.gwinterfaceserver.patrptcard.vo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
public class PatInfo {



    @SerializedName("sport_desc")
    public String contactInformation;

    ///<summary>
    ///既往住院情况—曾住精神专科医院/综合医院精神科次数
    ///</summary>
    @SerializedName("PsychogenyPastHospital")
    public String inMentalHospital;
    ///<summary>
    ///是否为家庭医师签约服务对象
    ///</summary>
    @SerializedName("IfFamilyPhysician")
    public String ifFamilyphysician;
    ///<summary>
    ///患者基本信息的主键
    ///</summary>
    @SerializedName("Id")
    public String dischargeInformationId;
    ///<summary>
    ///管理地区
    ///</summary>
    @SerializedName("ZoneCode")
    public String orgCountyCode;
//    public String zoneNam;


    ///<summary>
    ///管理单位
    ///</summary>
    @SerializedName("OrgCode")
    public String orgCode;
//    public String orgNam;


    ///<summary>
    ///患者编号
    ///</summary>
    @SerializedName("PatientNo")
    public String basicInformationNumber;
    ///<summary>
    ///患者姓名
    ///</summary>
    @SerializedName("PatientName")
    public String patientName;
    ///<summary>
    ///证件类型
    ///</summary>
    @SerializedName("IdCardTypeCode")
    public String idType;
    ///<summary>
    ///证件号码
    ///</summary>
    @SerializedName("IdCard")
    public String idCode;
    ///<summary>
    ///国籍
    ///</summary>
    @SerializedName("NationalityCode")
    public String nationality;
    ///<summary>
    ///性别
    ///</summary>
    @SerializedName("GenderCode")
    public String genderCode;
    ///<summary>
    ///出生日期
    ///</summary>
    @SerializedName("BirthDate")
    public String birthDate;
    ///<summary>
    ///监护人姓名
    ///</summary>
    @SerializedName("Contacts")
    public String guardianName;
    ///<summary>
    ///监护人电话
    ///</summary>
    @SerializedName("ContactsTel")
    public String guardianTel;
    ///<summary>
    ///监护人电话
    ///</summary>
    @SerializedName("ContactsTelTwo")
    public String guardianTelTwo;
    ///<summary>
    ///监护人与患者关系
    ///</summary>
    @SerializedName("ContactsRelCode")
    public String relationToPatient;
    ///<summary>
    ///两系三代精神疾病家族史
    ///</summary>
    @SerializedName("FamilyHistory")
    public String familyHistory;
    ///<summary>
    ///婚姻状况
    ///</summary>
    @SerializedName("MaritalStatusCode")
    public String maritalStatusCode;
    ///<summary>
    ///民族
    ///</summary>
    @SerializedName("NationCode")
    public String nationCode;
    ///<summary>
    ///就业情况(职业)
    ///</summary>
    @SerializedName("ProfessionCode")
    public String occupationCode;
    ///<summary>
    ///文化程度
    ///</summary>
    @SerializedName("EducationCode")
    public String educationLevelCode;
    ///<summary>
    ///现住地类别
    ///</summary>
    @SerializedName("PsychogenyAddrTypeCode")
    public String currentAddressType;
    ///<summary>
    ///现住址
    ///</summary>
    @SerializedName("CurrentAddrDetail")
    public String livingAddressDetails;
    ///<summary>
    ///病人属于
    ///</summary>
    @SerializedName("CurrentAddrTypeCode")
    public String livingAddressAttributionCode;
    ///<summary>
    ///现住地国标码
    ///</summary>
    @SerializedName("CurrentAddrCode")
    public String livingAddressCode;
    ///<summary>
    ///户籍详细地址
    ///</summary>
    @SerializedName("PermanentAddrDetail")
    public String domicileAdrressDetails;
    ///<summary>
    ///户籍地址类型
    ///</summary>
    @SerializedName("PermanentAddrTypeCode")
    public String domicileAddressAttributionCode;
    ///<summary>
    ///户籍国标码
    ///</summary>
    @SerializedName("PermanentAddrCode")
    public String domicileAddressCode;
    ///<summary>
    ///户别
    ///</summary>
    @SerializedName("PsychogenyPermanentAddrCode")
    public String registertype;
    ///<summary>
    ///户别不详原因
    ///</summary>
    @SerializedName("PsychogenyPermanentAddrTypeUnknownCause")
    public String unknownCause;
    ///<summary>
    ///目前诊断ICD10编码
    ///</summary>
    @SerializedName("DiseaseCode")
    public String diagnosis;
    ///<summary>
    ///确诊医院
    ///</summary>
    @SerializedName("DiagnoseOrg")
    public String diagnosisHospital;
    ///<summary>
    ///确诊医院不详
    ///</summary>
    @SerializedName("DiagnoseOrgUnknown")
    public String unknownHospita;
    ///<summary>
    ///确诊医院不详原因
    ///</summary>
    @SerializedName("DiagnoseOrgUnknownReason")
    public String unknownReason;
    ///<summary>
    ///确诊日期
    ///</summary>
    @SerializedName("DiagnoseTime")
    public String diagnosisDate;
    //格式化
    public void setDiagnosisDate(String diagnosisDate) {
        //1.定义格式
        DateTimeFormatter df= DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter df2 = DateTimeFormatter.ofPattern("yyyyMMdd");
        //2.把字符串转成localDate
        LocalDate localDate = LocalDate.parse(diagnosisDate, df);
        //3.格式化localDate
        String diagnosisDates = localDate.format(df2);
        this.diagnosisDate = diagnosisDates;
    }

    ///<summary>
    ///确诊日期精确度
    ///</summary>
    @SerializedName("DiagnoseTimeAccuracyCode")
    public String diagnosisDateAccuracy;
    ///<summary>
    ///初次发病时间
    ///</summary>
    @SerializedName("OnsetDate")
    public String firstOnsetDate;
    //格式化
    public void setFirstOnsetDate(String firstOnsetDate) {
        //1.定义格式
        DateTimeFormatter df= DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter df2 = DateTimeFormatter.ofPattern("yyyyMMdd");
        //2.把字符串转成localDate
        LocalDate localDate = LocalDate.parse(firstOnsetDate, df);
        //3.格式化localDate
        String firstOnsetDates = localDate.format(df2);
        this.firstOnsetDate = firstOnsetDates;
    }


    ///<summary>
    ///初次发病时间精确度
    ///</summary>
    @SerializedName("OnsetDateAccuracyCode")
    public String firstOnsetDateAccuracy;
    ///<summary>
    ///是否已进行抗精神病药物治疗
    ///</summary>
    @SerializedName("SignsMedicationCode")
    public String mentalMedicineTreatment;
    ///<summary>
    ///首次抗精神病药治疗时间
    ///</summary>
    @SerializedName("PsychogenyFirstCureDate")
    public String firstMedicineTime;
    //格式化
    public void setFirstMedicineTime(String firstMedicineTime) {
        //1.定义格式
        DateTimeFormatter df= DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter df2 = DateTimeFormatter.ofPattern("yyyyMMdd");
        //2.把字符串转成localDate
        LocalDate localDate = LocalDate.parse(firstMedicineTime, df);
        //3.格式化localDate
        String firstMedicineTimes = localDate.format(df2);
        this.firstMedicineTime = firstMedicineTimes;
    }
    ///<summary>
    ///首次抗精神病药治疗时间精确度
    ///</summary>
    @SerializedName("PsychogenyFirstCureDateAccuracyCode")
    public String firstMedicineTimeAccuracy;
    ///<summary>
    ///既往住院情况—曾住精神专科医院/综合医院精神科次数
    ///
    ///<summary>
    ///既往关锁情况
    ///</summary>
    @SerializedName("PsychogenyShutStatusHistoryCode")
    public String inCaptivity;
    ///<summary>
    ///既往危险行为
    ///</summary>
    @SerializedName("PsychogenyPastDangerousCode")
    public List<Psychogeny> pastRiskhave;

    //格式化
    public void setPastRiskhave(String pastRiskhave) {
        String[] split = pastRiskhave.split(",");
        List<Psychogeny> list=new ArrayList<>();
        for (String s : split) {
            Psychogeny pcks=new Psychogeny();
            pcks.psychogenyPastDangerousCode=s;
            list.add(pcks);
        }
        this.pastRiskhave = list;
    }

    ///<summary>
    ///家庭经济状况
    ///</summary>
    @SerializedName("PsychogenyEconomyLevelCode")
    public String familyEconomics;
    ///<summary>
    ///知情同意
    ///</summary>
    @SerializedName("PsychogenyInformedCode")
    public String informedConsent;
    ///<summary>
    ///知情同意书签字时间
    ///</summary>
    @SerializedName("PsychogenyInformedDate")
    public String signedInformedConsentDate;

    //格式化
    public void setSignedInformedConsentDate(String signedInformedConsentDate) {
        //1.定义格式
        DateTimeFormatter df= DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter df2 = DateTimeFormatter.ofPattern("yyyyMMdd");
        //2.把字符串转成localDate
        LocalDate localDate = LocalDate.parse(signedInformedConsentDate, df);
        //3.格式化localDate
        String signedInformedConsentDates = localDate.format(df2);
        this.signedInformedConsentDate = signedInformedConsentDates;
    }

//    ///<summary>
//    ///死亡日期
//    ///</summary>
//    public String deathDate;
//    ///<summary>
//    ///死亡原因
//    ///</summary>
//    public String deathCause;
//    ///<summary>
//    ///死亡原因明细
//    ///</summary>
//    public String deadDetail;
    ///<summary>
    ///是否为精准扶贫对象
    ///</summary>
    @SerializedName("IfSupportCode")
    public String ifPoverty;
    ///<summary>
    ///是否为监护补助对象
    ///</summary>
    @SerializedName("IfSubsidyCode")
    public String ifSubsidies;
    ///<summary>
    ///领取残疾人证情况
    ///</summary>
    @SerializedName("ReceiveDisabled")
    public String disabilitycardInfo;
    ///<summary>
    ///精神残疾人证等级
    ///</summary>
    @SerializedName("DisabledLevelCode")
    public String disabilityGrade;
    ///<summary>
    ///是否为关爱帮扶小组对象
    ///</summary>
    @SerializedName("IfCarefor")
    public String ifSupportgroup;
    ///<summary>
    ///是否参加社区康复服务
    ///</summary>
    @SerializedName("IfRecoveryMeasures")
    public String ifRecovery;

//    public String id;


//    public String unknownHospital;

}
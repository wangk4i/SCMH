package com.hyd.gwinterfaceserver.patinfo.vo;

import com.hyd.gwinterfaceserver.patrptcard.vo.Medication;

import java.util.List;

public class Followup2 {

    ///<summary>
    ///患者基本信息的主键
    ///</summary>
    public String dischargeInformationId;
    ///<summary>
    ///患者随访信息的主键
    ///</summary>
    public String followUpInformationId;
    ///<summary>
    ///本次随访时间
    ///</summary>
    public String followupDate;
    ///<summary>
    ///失访原因
    ///</summary>
    public String lostReason;
    ///<summary>
    ///失访原因其它说明
    ///</summary>
    public String lostReasonOther;
    ///<summary>
    ///两次随访期间住院情况
    ///</summary>
    public String hospitalCourse;
    ///<summary>
    ///末次出院时间
    ///</summary>
    public String lastDischargedDate;
    ///<summary>
    ///末次出院时间精确度
    ///</summary>
    public String lastDischargedDateAccuracy;
    ///<summary>
    ///本次随访形式
    ///</summary>
    public String followForm;
    ///<summary>
    ///本次随访对象
    ///</summary>
    public String followObject;
    ///<summary>
    ///两次随访期间关锁情况
    ///</summary>
    public String captivity;
    ///<summary>
    ///本次随访分类(基础管理随访病情分类)
    ///</summary>
    public String caseClassification;
    ///<summary>
    ///危险性评估
    ///</summary>
    public String riskAssessment;
    ///<summary>
    ///目前症状
    ///</summary>
    public String symptom;
    ///<summary>
    ///目前症状其他
    ///</summary>
    public String symptomDesc;
    ///<summary>
    ///自知力
    ///</summary>
    public String insight;
    ///<summary>
    ///睡眠情况
    ///</summary>
    public String sleep;
    ///<summary>
    ///饮食情况
    ///</summary>
    public String diet;
    ///<summary>
    ///个人生活料理
    ///</summary>
    public String life;
    ///<summary>
    ///家务劳动
    ///</summary>
    public String menage;
    ///<summary>
    ///生产劳动及工作
    ///</summary>
    public String work;
    ///<summary>
    ///学习能力
    ///</summary>
    public String study;
    ///<summary>
    ///社会人际交往
    ///</summary>
    public String contact;
    ///<summary>
    ///轻度滋事（次）
    ///</summary>
    public String mildDisturbancesNumber;
    ///<summary>
    ///肇事（次）
    ///</summary>
    public String troubleNumber;
    ///<summary>
    ///肇祸（次）
    ///</summary>
    public String accidentNumber;
    ///<summary>
    ///其他危害他人行为（次）
    ///</summary>
    public String harmNumber;
    ///<summary>
    ///自伤（次）
    ///</summary>
    public String autolesionNumber;
    ///<summary>
    ///自杀未遂（次）
    ///</summary>
    public String suicideFailNumber;
    ///<summary>
    ///用药依从性(服药依从性)
    ///</summary>
    public String drugHabituation;
    ///<summary>
    ///药物不良反应
    ///</summary>
    public String adverseDrugReaction;
    ///<summary>
    ///治疗效果
    ///</summary>
    public String inMentalEffect;
    ///<summary>
    ///是否进行实验室检查
    ///</summary>
    public String laboratoryExamination;
    ///<summary>
    ///实验室检查具体数据项
    ///</summary>
    public String laboratorySpecific;
    ///<summary>
    ///建议转诊
    ///</summary>
    public String suggestRefer;
    ///<summary>
    ///建议转诊原因
    ///</summary>
    public String referReason;
    ///<summary>
    ///是否已转诊
    ///</summary>
    public String refer;
    ///<summary>
    ///转至机构名称
    ///</summary>
    public String referDepartment;
    ///<summary>
    ///是否进行个案管理
    ///</summary>
    public String caseManagement;
    ///<summary>
    ///个案管理病情总体评估
    ///</summary>
    public String assess;
    ///<summary>
    ///个案管理社会功能总评
    ///</summary>
    public String socialEvaluation;
    ///<summary>
    ///康复措施
    ///</summary>
    public String recovery;
    ///<summary>
    ///康复措施其他请注明
    ///</summary>
    public String recoveryDesc;
    ///<summary>
    ///是否有3个月内医生开具的精神科药物处方（含患者或家属自行就诊时获得的处方）
    ///</summary>
    public String ifRecipe;

    public String currSymStr;

    public String currSymDesc;

    ///<summary>
    ///药品信息
    ///</summary>
    public List<Medication> medicationList;

    public String patInfoCd;
    public String iD;
    public String syncStatus;
    public String syncTime;
    public String delStatus;
    public String delReson;
    public String syncError;


    /***
     * 新增患者的死亡信息
     * */
    /// <summary>
    /// 转死亡操作的基本信息主键。必须指定。
    /// </summary>
    public String basicInformationId;
    /// <summary>
    /// 死亡原因（1 躯体疾病、2 自杀、3 他杀、4 意外、5 精神疾病相关并发症、6 其他）。必须指定。
    /// </summary>
    public String causeOfDeath;
    /// <summary>
    /// 死亡日期（格式为“YYYY-MM-DD”，要求填写到日）。必须指定。
    /// </summary>
    public String dateOfDeath;
    /// <summary>
    /// 当死亡原因为躯体疾病，需要上传躯体疾病明细。否则不传 1(传染病)	2(寄生虫病)	3(肿瘤)	4(心脏病	)5(脑血管病)6(呼吸系统疾病)7(消化系统疾病)	8(其他疾病)	9(不详)
    /// </summary>
    public String deadDetail;

}

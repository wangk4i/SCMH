package com.hyd.gwinterfaceserver.patrptcard.vo;

public class Medication {
    /// <summary>
    /// 药物主键
    /// </summary>
    public String medicationId;
    /// <summary>
    /// 序号
    /// </summary>
    public String no;
    /// <summary>
    /// 目前用药情况-是否长效
    /// </summary>
    public String drugType;
    /// <summary>
    /// 非长效药物名称
    /// </summary>
    public String sDrug;
    /// <summary>
    /// 非长效药物规格
    /// </summary>
    public String sDrugSpecifications;
    /// <summary>
    /// 非长效药物晨间服用剂量
    /// </summary>
    public String sDrugDosem;
    /// <summary>
    /// 非长效药物午间服用剂量
    /// </summary>
    public String sDrugDosen;
    /// <summary>
    /// 非长效药物晚间服用剂量
    /// </summary>
    public String sDrugDosee;
    /// <summary>
    /// 长效药药物名称
    /// </summary>
    public String lDrug;
    /// <summary>
    /// 长效药物规格
    /// </summary>
    public String lDrugFrequency;
    /// <summary>
    /// 长效药物用法
    /// </summary>
    public String lDrugUsage;
    /// <summary>
    /// 长效药物剂量
    /// </summary>
    public String lDrugDose;
    /// <summary>
    /// 长效药物频率单位（只能填“月”或“周”）
    /// </summary>
    public String lDrugTime;
    /// <summary>
    /// 类别
    /// </summary>
    public String type;
}
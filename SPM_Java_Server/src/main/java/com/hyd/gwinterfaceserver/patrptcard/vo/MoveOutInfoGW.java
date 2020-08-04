package com.hyd.gwinterfaceserver.patrptcard.vo;

public class MoveOutInfoGW {
    /// <summary>
    /// 患者流转信息的主键
    /// </summary>
    public String moveInAndOutId;
    /// <summary>
    /// 迁出类型
    /// </summary>
    public String moveInAndOutType;
    /// <summary>
    /// 患者报告卡信息的主键
    /// </summary>
    public String newCaseReportId;
    /// <summary>
    /// 患者基本信息的主键
    /// </summary>
    public String basicInformationId;
    /// <summary>
    /// 迁出或上挂地区
    /// </summary>
    public String ooutOrgCountyCode;
    /// <summary>
    /// 迁出或上挂单位
    /// </summary>
    public String outOrgCode;
    /// <summary>
    /// 迁出或上挂时间
    /// </summary>
    public String moveOutTime;
    /// <summary>
    /// 迁入或挂入地区
    /// </summary>
    public String inOrgCountyCode;
    /// <summary>
    /// 迁入或挂入单位
    /// </summary>
    public String inOrgCode;
    /// <summary>
    /// 迁入或挂入时间
    /// </summary>
    public String moveInTime;
    /// <summary>
    /// 迁出原因
    /// </summary>
    public String moveOutCause;
    /// <summary>
    /// 知情同意书签字时间
    /// </summary>
    public String signedInformedConsentDate;
    /// <summary>
    /// 拒绝原因
    /// </summary>
    public String refuseCause;

}
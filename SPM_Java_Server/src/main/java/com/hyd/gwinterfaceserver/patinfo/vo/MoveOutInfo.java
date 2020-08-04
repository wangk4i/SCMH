package com.hyd.gwinterfaceserver.patinfo.vo;

import com.hyd.gwinterfaceserver.util.Pageinfos;
import lombok.Data;

@Data
public class MoveOutInfo extends Pageinfos {
    /// <summary>
    /// 编号
    /// </summary>

    public String cd;

    /// <summary>
    /// 1@（字典）迁出类型
    /// </summary>

    public String moveOutCd;

    /// <summary>
    /// 1@（字典）迁出类型 Nam
    /// </summary>

    public String moveOutCdNam;

    /// <summary>
    /// 名称
    /// </summary>

    public String nam;

    /// <summary>
    /// 2@患者出院单信息编号
    /// </summary>

    public String patLeftRptCd;
    /// <summary>
    /// 患者报告单编号
    /// </summary>
    public String inciRptCd;

    /// <summary>
    /// 3@患者基本信息编号
    /// </summary>

    public String patInfoCd;

    /// <summary>
    /// 4@迁出或上挂地区编号
    /// </summary>

    public String outZoneCd;

    /// <summary>
    /// 5@迁出或上挂地区名称
    /// </summary>

    public String outZoneNam;

    /// <summary>
    /// 6@迁出或上挂机构编号
    /// </summary>

    public String outOrgCd;

    /// <summary>
    /// 7@迁出或上挂机构名称
    /// </summary>

    public String outOrgNam;

    /// <summary>
    /// 8@迁出或上挂日期
    /// </summary>

    public String outDate;

    /// <summary>
    /// 9@迁入或挂入地区编号
    /// </summary>

    public String inZoneCd;

    /// <summary>
    /// 10@迁入或挂入地区名称
    /// </summary>

    public String inZoneNam;

    /// <summary>
    /// 11@迁入或挂入机构编号
    /// </summary>

    public String inOrgCd;

    /// <summary>
    /// 12@迁入或挂入机构名称
    /// </summary>

    public String inOrgNam;

    /// <summary>
    /// 13@迁入或挂入日期
    /// </summary>

    public String inDate;

    /// <summary>
    /// 14@迁出原因
    /// </summary>

    public String moveOutCause;

    /// <summary>
    /// 15@知情同意书签署日期
    /// </summary>

    public String signedDate;

    /// <summary>
    /// 16@拒绝原因
    /// </summary>

    public String refuseCause;

    /// <summary>
    /// 17@流转信息状态
    /// </summary>

    public String moveStatusCd;

    /// <summary>
    /// 17@流转信息状态 Nam
    /// </summary>

    public String moveStatusCdNam;

    /// <summary>
    /// 18@（字典）删除标识
    /// </summary>

    public String delStatus;

    /// <summary>
    /// 18@（字典）删除标识 Nam
    /// </summary>

    public String delStatusNam;

    /// <summary>
    /// 19@上挂地区编号
    /// </summary>

    public String upZoneCd;

    /// <summary>
    /// 20@上挂地区名称
    /// </summary>

    public String upZoneNam;

    /// <summary>
    /// 21@上挂级别
    /// </summary>

    public String upState;

    /// <summary>
    /// 22@是否迁出到地区
    /// </summary>

    public String isToArea;

    /// <summary>
    /// 23@迁出到地区是否被拒
    /// </summary>

    public String refuseToArea;

    /// <summary>
    /// 24@是否响应
    /// </summary>

    public String ifResponse;

    /// <summary>
    /// 25@响应时间
    /// </summary>

    public String responseDate;

    /// <summary>
    /// 26@数据来源
    /// </summary>

    public String sourceType;

    /// <summary>
    /// 27@国网主键
    /// </summary>

    public String fIELDPK;

    /// <summary>
    /// 28@同步国网状态
    /// </summary>

    public String syncStatus;

    /// <summary>
    /// 29@国网来源
    /// </summary>

    public String fromSrc;

    /// <summary>
    /// 30@国网迁入流转主键
    /// </summary>

    public String outCd;

    /// <summary>
    /// 创建人编号
    /// </summary>

    public String creaCd;

    /// <summary>
    /// 创建时间
    /// </summary>

    public String creTime;

    /// <summary>
    /// 最后一次修改人编号
    /// </summary>

    public String lastModiCd;

    /// <summary>
    /// 最后一次修改时间
    /// </summary>

    public String lastModTime;

    /// <summary>
    /// 数据状态
    /// </summary>

    public String state;
}
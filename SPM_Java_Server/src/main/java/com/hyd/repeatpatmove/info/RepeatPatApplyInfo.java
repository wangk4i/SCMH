package com.hyd.repeatpatmove.info;

import lombok.Data;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/4 14:04
 */
@Data
public class RepeatPatApplyInfo {

    private String RepeatInfoId;

    private String DischargeInformationId;

    private String PatientName;

    private String IDCard;

    private String GenderCode;

    private String BirthDate;

    private String CurrentAddrTypeCode;

    private String CurrentAddrCode;

    private String CurrentAddrDetail;

    private String DiseaseCode;

    private String OrgCode;

    private String OrgZoneCode;

    private String Contacts;

    private String ContactsTel;

    private String ContactsRelCode;
}
package com.hyd.patInfomanagement.vo;

import com.hyd.system.info.Pageinfo;
import lombok.Data;

@Data
public class ReportcardsQueryVo{

    private String inciRptCd;
    private String name;
    private String genderCdNam;
    private String birthDate;
    private String lAddress;
    private String diseaseICDNam;
    private String diagnosisDate;
    private String creTime;
    private String iDCode;

}
package com.hyd.gwinterfaceserver.patinfo.info;

import com.hyd.gwinterfaceserver.patinfo.vo.ExtInfo;
import com.hyd.system.info.ExtInfoObj;
import lombok.Data;

@Data
public class repeatInfoApplyRequest {
    public ExtInfo extInfoObj;
    public String repeatInfoId;
    public String dischargeInformationId;
    public String applyZone;
    public String applyOrgan;
    public String auditZone;
    public String auditOrgan;
    public String patientName;
    public String genderCode;
    public String idCode;
    public String birthDate;
    public String guardianName;
    public String guardianTel;
    public String relationToPatient;
    public String livingAddressDetails;
    public String livingAddressAttributionCode;
    public String livingAddressCode;
    public String diagnosis;
    public String moveCode;
}
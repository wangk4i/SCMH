package com.hyd.gwinterfaceserver.patinfo.vo;

import lombok.Data;

@Data
public class BasicInformation {
    public String dischargeInformationId;
    public String basicInformationNumber;
    public String orgCountyCode;
    public String orgCode;
    public String idCode;
    public String birthDate;
    public String patientName;
    public String genderCode;
    public String nationCode;
    public String maritalStatusCode;
    public String educationLevelCode;
    public String occupationCode;
    public String familyHistory;
    public String familyEconomics;
    public String guardianName;
    public String guardianTel;
    public String relationToPatient;
    public String livingAddressDetails;
    public String livingAddressAttributionCode;
    public String livingAddressCode;
    public String domicileAdrressDetails;
    public String domicileAddressAttributionCode;
    public String domicileAddressCode;
    public String diagnosis;
    public String firstOnsetDate;
    public String firstOnsetDateAccuracy;
    public String mentalMedicineTreatment;
    public String firstMedicineTime;
    public String firstMedicineTimeAccuracy;
    public String inMentalHospital;
    public String inCaptivity;
    public String informedConsent;
    public String signedInformedConsentDate;
    public String ifForce;
    public String manage;
    public String manageDate;
    public String deathCause;
    public String deathDate;
    public String deadDetail;
    public String ifDel;
}
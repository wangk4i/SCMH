package com.hyd.consmanage.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


public class ViewInPat {
    private String accuracyCd;
    private String birthDate;
    private String cd;
    private String dAddrTpCd;
    private String dAddress;
    private String dCityCd;
    private String dCountyCd;
    private String dProvinceCd;
    private String dTownCd;
    private String delStatus;
    private String diagnosisDate;
    private String diagnosisHos;
    private String diseaseCd;
    private String diseaseCdNam;
    private String diseaseICD;
    private String divisionCd;
    private String educationCd;
    private String employCd;
    private String familyHisCd;
    private String fillCardDate;
    private String fillCardDoc;
    private String firstCureTime;
    private String firstOnsetDate;
    private String firstOnsetDay;
    private String firstOnsetMon;
    private String firstOnsetYear;
    private String fstCureAccCd;
    private String genderCd;
    private String guardianNam;
    private String guardianTel;
    private String guardianTelVice;
    private String hospStateHis;
    private String iCSignDate;
    private String iDCode;
    private String iDTypeCd;
    private String ifCureCd;
    private String ifNomedication;
    private String inciReportNum;
    private String informedConsCd;
    private String lAddrTpCd;
    private String lAddress;
    private String lCityCd;
    private String lCountyCd;
    private String lProvinceCd;
    private String lTownCd;
    private String lockStateCd;
    private String marriageCd;
    private String nationCd;
    private String nationalityCd;
    private String orgNam;
    private String organCd;
    private String patNam;
    private String registertypeCd;
    private String relationshipCd;
    private String riskActCd;
    private String riskCd;
    private String rptDept;
    private String rptDeptTel;
    private String senderBodyStr;
    private String zoneCd;
    private String zoneNam;
    private List<PatRptCardMed> medList=new ArrayList<>();

    public List<PatRptCardMed> getMedList() {
        return medList;
    }

    public void setMedList(List<PatRptCardMed> medList) {
        this.medList = medList;
    }

    public String getAccuracyCd() {
        return accuracyCd;
    }

    public void setAccuracyCd(String accuracyCd) {
        this.accuracyCd = accuracyCd;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getdAddrTpCd() {
        return dAddrTpCd;
    }

    public void setdAddrTpCd(String dAddrTpCd) {
        this.dAddrTpCd = dAddrTpCd;
    }

    public String getdAddress() {
        return dAddress;
    }

    public void setdAddress(String dAddress) {
        this.dAddress = dAddress;
    }

    public String getdCityCd() {
        return dCityCd;
    }

    public void setdCityCd(String dCityCd) {
        this.dCityCd = dCityCd;
    }

    public String getdCountyCd() {
        return dCountyCd;
    }

    public void setdCountyCd(String dCountyCd) {
        this.dCountyCd = dCountyCd;
    }

    public String getdProvinceCd() {
        return dProvinceCd;
    }

    public void setdProvinceCd(String dProvinceCd) {
        this.dProvinceCd = dProvinceCd;
    }

    public String getdTownCd() {
        return dTownCd;
    }

    public void setdTownCd(String dTownCd) {
        this.dTownCd = dTownCd;
    }

    public String getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(String delStatus) {
        this.delStatus = delStatus;
    }

    public String getDiagnosisDate() {
        return diagnosisDate;
    }

    public void setDiagnosisDate(String diagnosisDate) {
        this.diagnosisDate = diagnosisDate;
    }

    public String getDiagnosisHos() {
        return diagnosisHos;
    }

    public void setDiagnosisHos(String diagnosisHos) {
        this.diagnosisHos = diagnosisHos;
    }

    public String getDiseaseCd() {
        return diseaseCd;
    }

    public void setDiseaseCd(String diseaseCd) {
        this.diseaseCd = diseaseCd;
    }

    public String getDiseaseCdNam() {
        return diseaseCdNam;
    }

    public void setDiseaseCdNam(String diseaseCdNam) {
        this.diseaseCdNam = diseaseCdNam;
    }

    public String getDiseaseICD() {
        return diseaseICD;
    }

    public void setDiseaseICD(String diseaseICD) {
        this.diseaseICD = diseaseICD;
    }

    public String getDivisionCd() {
        return divisionCd;
    }

    public void setDivisionCd(String divisionCd) {
        this.divisionCd = divisionCd;
    }

    public String getEducationCd() {
        return educationCd;
    }

    public void setEducationCd(String educationCd) {
        this.educationCd = educationCd;
    }

    public String getEmployCd() {
        return employCd;
    }

    public void setEmployCd(String employCd) {
        this.employCd = employCd;
    }

    public String getFamilyHisCd() {
        return familyHisCd;
    }

    public void setFamilyHisCd(String familyHisCd) {
        this.familyHisCd = familyHisCd;
    }

    public String getFillCardDate() {
        return fillCardDate;
    }

    public void setFillCardDate(String fillCardDate) {
        this.fillCardDate = fillCardDate;
    }

    public String getFillCardDoc() {
        return fillCardDoc;
    }

    public void setFillCardDoc(String fillCardDoc) {
        this.fillCardDoc = fillCardDoc;
    }

    public String getFirstCureTime() {
        return firstCureTime;
    }

    public void setFirstCureTime(String firstCureTime) {
        this.firstCureTime = firstCureTime;
    }

    public String getFirstOnsetDate() {
        return firstOnsetDate;
    }

    public void setFirstOnsetDate(String firstOnsetDate) {
        this.firstOnsetDate = firstOnsetDate;
    }

    public String getFirstOnsetDay() {
        return firstOnsetDay;
    }

    public void setFirstOnsetDay(String firstOnsetDay) {
        this.firstOnsetDay = firstOnsetDay;
    }

    public String getFirstOnsetMon() {
        return firstOnsetMon;
    }

    public void setFirstOnsetMon(String firstOnsetMon) {
        this.firstOnsetMon = firstOnsetMon;
    }

    public String getFirstOnsetYear() {
        return firstOnsetYear;
    }

    public void setFirstOnsetYear(String firstOnsetYear) {
        this.firstOnsetYear = firstOnsetYear;
    }

    public String getFstCureAccCd() {
        return fstCureAccCd;
    }

    public void setFstCureAccCd(String fstCureAccCd) {
        this.fstCureAccCd = fstCureAccCd;
    }

    public String getGenderCd() {
        return genderCd;
    }

    public void setGenderCd(String genderCd) {
        this.genderCd = genderCd;
    }

    public String getGuardianNam() {
        return guardianNam;
    }

    public void setGuardianNam(String guardianNam) {
        this.guardianNam = guardianNam;
    }

    public String getGuardianTel() {
        return guardianTel;
    }

    public void setGuardianTel(String guardianTel) {
        this.guardianTel = guardianTel;
    }

    public String getGuardianTelVice() {
        return guardianTelVice;
    }

    public void setGuardianTelVice(String guardianTelVice) {
        this.guardianTelVice = guardianTelVice;
    }

    public String getHospStateHis() {
        return hospStateHis;
    }

    public void setHospStateHis(String hospStateHis) {
        this.hospStateHis = hospStateHis;
    }

    public String getiCSignDate() {
        return iCSignDate;
    }

    public void setiCSignDate(String iCSignDate) {
        this.iCSignDate = iCSignDate;
    }

    public String getiDCode() {
        return iDCode;
    }

    public void setiDCode(String iDCode) {
        this.iDCode = iDCode;
    }

    public String getiDTypeCd() {
        return iDTypeCd;
    }

    public void setiDTypeCd(String iDTypeCd) {
        this.iDTypeCd = iDTypeCd;
    }

    public String getIfCureCd() {
        return ifCureCd;
    }

    public void setIfCureCd(String ifCureCd) {
        this.ifCureCd = ifCureCd;
    }

    public String getIfNomedication() {
        return ifNomedication;
    }

    public void setIfNomedication(String ifNomedication) {
        this.ifNomedication = ifNomedication;
    }

    public String getInciReportNum() {
        return inciReportNum;
    }

    public void setInciReportNum(String inciReportNum) {
        this.inciReportNum = inciReportNum;
    }

    public String getInformedConsCd() {
        return informedConsCd;
    }

    public void setInformedConsCd(String informedConsCd) {
        this.informedConsCd = informedConsCd;
    }

    public String getlAddrTpCd() {
        return lAddrTpCd;
    }

    public void setlAddrTpCd(String lAddrTpCd) {
        this.lAddrTpCd = lAddrTpCd;
    }

    public String getlAddress() {
        return lAddress;
    }

    public void setlAddress(String lAddress) {
        this.lAddress = lAddress;
    }

    public String getlCityCd() {
        return lCityCd;
    }

    public void setlCityCd(String lCityCd) {
        this.lCityCd = lCityCd;
    }

    public String getlCountyCd() {
        return lCountyCd;
    }

    public void setlCountyCd(String lCountyCd) {
        this.lCountyCd = lCountyCd;
    }

    public String getlProvinceCd() {
        return lProvinceCd;
    }

    public void setlProvinceCd(String lProvinceCd) {
        this.lProvinceCd = lProvinceCd;
    }

    public String getlTownCd() {
        return lTownCd;
    }

    public void setlTownCd(String lTownCd) {
        this.lTownCd = lTownCd;
    }

    public String getLockStateCd() {
        return lockStateCd;
    }

    public void setLockStateCd(String lockStateCd) {
        this.lockStateCd = lockStateCd;
    }

    public String getMarriageCd() {
        return marriageCd;
    }

    public void setMarriageCd(String marriageCd) {
        this.marriageCd = marriageCd;
    }
//
//    public String getMedList() {
//        return medList;
//    }
//
//    public void setMedList(String medList) {
//        this.medList = medList;
//    }
//
//    public String getCountryDrugCd() {
//        return countryDrugCd;
//    }
//
//    public void setCountryDrugCd(String countryDrugCd) {
//        this.countryDrugCd = countryDrugCd;
//    }
//
//    public String getDrugType() {
//        return drugType;
//    }
//
//    public void setDrugType(String drugType) {
//        this.drugType = drugType;
//    }
//
//    public String getInciRptCd() {
//        return inciRptCd;
//    }
//
//    public void setInciRptCd(String inciRptCd) {
//        this.inciRptCd = inciRptCd;
//    }
//
//    public String getsDrug() {
//        return sDrug;
//    }
//
//    public void setsDrug(String sDrug) {
//        this.sDrug = sDrug;
//    }
//
//    public String getsDrugDosee() {
//        return sDrugDosee;
//    }
//
//    public void setsDrugDosee(String sDrugDosee) {
//        this.sDrugDosee = sDrugDosee;
//    }
//
//    public String getsDrugDosem() {
//        return sDrugDosem;
//    }
//
//    public void setsDrugDosem(String sDrugDosem) {
//        this.sDrugDosem = sDrugDosem;
//    }
//
//    public String getsDrugDosen() {
//        return sDrugDosen;
//    }
//
//    public void setsDrugDosen(String sDrugDosen) {
//        this.sDrugDosen = sDrugDosen;
//    }
//
//    public String getsDrugSpecifications() {
//        return sDrugSpecifications;
//    }
//
//    public void setsDrugSpecifications(String sDrugSpecifications) {
//        this.sDrugSpecifications = sDrugSpecifications;
//    }
//
//    public String getSerialNo() {
//        return serialNo;
//    }
//
//    public void setSerialNo(String serialNo) {
//        this.serialNo = serialNo;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }

    public String getNationCd() {
        return nationCd;
    }

    public void setNationCd(String nationCd) {
        this.nationCd = nationCd;
    }

    public String getNationalityCd() {
        return nationalityCd;
    }

    public void setNationalityCd(String nationalityCd) {
        this.nationalityCd = nationalityCd;
    }

    public String getOrgNam() {
        return orgNam;
    }

    public void setOrgNam(String orgNam) {
        this.orgNam = orgNam;
    }

    public String getOrganCd() {
        return organCd;
    }

    public void setOrganCd(String organCd) {
        this.organCd = organCd;
    }

    public String getPatNam() {
        return patNam;
    }

    public void setPatNam(String patNam) {
        this.patNam = patNam;
    }

    public String getRegistertypeCd() {
        return registertypeCd;
    }

    public void setRegistertypeCd(String registertypeCd) {
        this.registertypeCd = registertypeCd;
    }

    public String getRelationshipCd() {
        return relationshipCd;
    }

    public void setRelationshipCd(String relationshipCd) {
        this.relationshipCd = relationshipCd;
    }

    public String getRiskActCd() {
        return riskActCd;
    }

    public void setRiskActCd(String riskActCd) {
        this.riskActCd = riskActCd;
    }

    public String getRiskCd() {
        return riskCd;
    }

    public void setRiskCd(String riskCd) {
        this.riskCd = riskCd;
    }

    public String getRptDept() {
        return rptDept;
    }

    public void setRptDept(String rptDept) {
        this.rptDept = rptDept;
    }

    public String getRptDeptTel() {
        return rptDeptTel;
    }

    public void setRptDeptTel(String rptDeptTel) {
        this.rptDeptTel = rptDeptTel;
    }

    public String getSenderBodyStr() {
        return senderBodyStr;
    }

    public void setSenderBodyStr(String senderBodyStr) {
        this.senderBodyStr = senderBodyStr;
    }

    public String getZoneCd() {
        return zoneCd;
    }

    public void setZoneCd(String zoneCd) {
        this.zoneCd = zoneCd;
    }

    public String getZoneNam() {
        return zoneNam;
    }

    public void setZoneNam(String zoneNam) {
        this.zoneNam = zoneNam;
    }
}
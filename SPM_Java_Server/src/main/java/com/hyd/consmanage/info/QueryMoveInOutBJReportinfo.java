package com.hyd.consmanage.info;


import com.hyd.system.info.ExtInfoObj;
import com.hyd.system.info.Pageinfo;
import lombok.Data;

@Data
public class QueryMoveInOutBJReportinfo extends Pageinfo {
    private String other1;
    private String other2;
    private String birthDateEnd;
    private String birthDateStart;
    private String dischgDiagCd;
    private String genderCd;
    private String iDCode;
    private String inciReportNum;
    private String moveOutCd;
    private String moveStatusCd;
    private String organCd;
    private String outDateEnd;
    private String outDateStart;
    private String patNam;
    private String zoneCd;






    public String getOther1() {
        return other1;
    }

    public void setOther1(String other1) {
        this.other1 = other1;
    }

    public String getOther2() {
        return other2;
    }

    public void setOther2(String other2) {
        this.other2 = other2;
    }

    public String getBirthDateEnd() {
        return birthDateEnd;
    }

    public void setBirthDateEnd(String birthDateEnd) {
        this.birthDateEnd = birthDateEnd;
    }

    public String getBirthDateStart() {
        return birthDateStart;
    }

    public void setBirthDateStart(String birthDateStart) {
        this.birthDateStart = birthDateStart;
    }

    public String getDischgDiagCd() {
        return dischgDiagCd;
    }

    public void setDischgDiagCd(String dischgDiagCd) {
        this.dischgDiagCd = dischgDiagCd;
    }

    public String getGenderCd() {
        return genderCd;
    }

    public void setGenderCd(String genderCd) {
        this.genderCd = genderCd;
    }

    public String getiDCode() {
        return iDCode;
    }

    public void setiDCode(String iDCode) {
        this.iDCode = iDCode;
    }

    public String getInciReportNum() {
        return inciReportNum;
    }

    public void setInciReportNum(String inciReportNum) {
        this.inciReportNum = inciReportNum;
    }

    public String getMoveOutCd() {
        return moveOutCd;
    }

    public void setMoveOutCd(String moveOutCd) {
        this.moveOutCd = moveOutCd;
    }

    public String getMoveStatusCd() {
        return moveStatusCd;
    }

    public void setMoveStatusCd(String moveStatusCd) {
        this.moveStatusCd = moveStatusCd;
    }

    public String getOrganCd() {
        return organCd;
    }

    public void setOrganCd(String organCd) {
        this.organCd = organCd;
    }



    public String getOutDateEnd() {
        return outDateEnd;
    }

    public void setOutDateEnd(String outDateEnd) {
        this.outDateEnd = outDateEnd;
    }

    public String getOutDateStart() {
        return outDateStart;
    }

    public void setOutDateStart(String outDateStart) {
        this.outDateStart = outDateStart;
    }

    public String getPatNam() {
        return patNam;
    }

    public void setPatNam(String patNam) {
        this.patNam = patNam;
    }

    public String getZoneCd() {
        return zoneCd;
    }

    public void setZoneCd(String zoneCd) {
        this.zoneCd = zoneCd;
    }


}

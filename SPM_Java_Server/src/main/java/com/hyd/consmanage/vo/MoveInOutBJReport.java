package com.hyd.consmanage.vo;

import lombok.Data;

@Data
public class MoveInOutBJReport {
    private String birth;
    private String cd;
    private String currAddress;
    private String currDia;
    private String iDCode;
    private String inToOrg;
    private String inToOrgNam;
    private String inToTime;
    private String inToZone;
    private String inToZoneNam;
    private String inciRptCd;
    private String isRefuse;
    private String isResponse;
    private String moveOutCdNam;
    private String moveStatusCd;
    private String name;
    private String outOrgCd;
    private String outOrgNam;
    private String outZoneCd;
    private String outZoneNam;
    private String patInfoCd;
    private String patLeftRptCd;
    private String patOutReson;
    private String phone;
    private String sex;
    private String refuseCause;

    public String getRefuseCause() {
        return refuseCause;
    }

    public void setRefuseCause(String refuseCause) {
        this.refuseCause = refuseCause;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getCurrAddress() {
        return currAddress;
    }

    public void setCurrAddress(String currAddress) {
        this.currAddress = currAddress;
    }

    public String getCurrDia() {
        return currDia;
    }

    public void setCurrDia(String currDia) {
        this.currDia = currDia;
    }

    public String getiDCode() {
        return iDCode;
    }

    public void setiDCode(String iDCode) {
        this.iDCode = iDCode;
    }

    public String getInToOrg() {
        return inToOrg;
    }

    public void setInToOrg(String inToOrg) {
        this.inToOrg = inToOrg;
    }

    public String getInToOrgNam() {
        return inToOrgNam;
    }

    public void setInToOrgNam(String inToOrgNam) {
        this.inToOrgNam = inToOrgNam;
    }

    public String getInToTime() {
        return inToTime;
    }

    public void setInToTime(String inToTime) {
        this.inToTime = inToTime;
    }

    public String getInToZone() {
        return inToZone;
    }

    public void setInToZone(String inToZone) {
        this.inToZone = inToZone;
    }

    public String getInToZoneNam() {
        return inToZoneNam;
    }

    public void setInToZoneNam(String inToZoneNam) {
        this.inToZoneNam = inToZoneNam;
    }

    public String getInciRptCd() {
        return inciRptCd;
    }

    public void setInciRptCd(String inciRptCd) {
        this.inciRptCd = inciRptCd;
    }

    public String getIsRefuse() {
        return isRefuse;
    }

    public void setIsRefuse(String isRefuse) {
        this.isRefuse = isRefuse;
    }

    public String getIsResponse() {
        return isResponse;
    }

    public void setIsResponse(String isResponse) {
        this.isResponse = isResponse;
    }

    public String getMoveOutCdNam() {
        return moveOutCdNam;
    }

    public void setMoveOutCdNam(String moveOutCdNam) {
        this.moveOutCdNam = moveOutCdNam;
    }

    public String getMoveStatusCd() {
        return moveStatusCd;
    }

    public void setMoveStatusCd(String moveStatusCd) {
        this.moveStatusCd = moveStatusCd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOutOrgCd() {
        return outOrgCd;
    }

    public void setOutOrgCd(String outOrgCd) {
        this.outOrgCd = outOrgCd;
    }

    public String getOutOrgNam() {
        return outOrgNam;
    }

    public void setOutOrgNam(String outOrgNam) {
        this.outOrgNam = outOrgNam;
    }

    public String getOutZoneCd() {
        return outZoneCd;
    }

    public void setOutZoneCd(String outZoneCd) {
        this.outZoneCd = outZoneCd;
    }

    public String getOutZoneNam() {
        return outZoneNam;
    }

    public void setOutZoneNam(String outZoneNam) {
        this.outZoneNam = outZoneNam;
    }

    public String getPatInfoCd() {
        return patInfoCd;
    }

    public void setPatInfoCd(String patInfoCd) {
        this.patInfoCd = patInfoCd;
    }

    public String getPatLeftRptCd() {
        return patLeftRptCd;
    }

    public void setPatLeftRptCd(String patLeftRptCd) {
        this.patLeftRptCd = patLeftRptCd;
    }

    public String getPatOutReson() {
        return patOutReson;
    }

    public void setPatOutReson(String patOutReson) {
        this.patOutReson = patOutReson;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
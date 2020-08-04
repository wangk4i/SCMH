package com.hyd.patInfomanagement.vo;

import lombok.Data;

@Data
public class MoveReport {

    private  String cd;
    private  String nam;
    private  String outZoneNam;
    private  String outOrgNam;
    private  String inZoneNam;
    private  String inOrgNam;
    private  String moveOutCause;
    private  String ifResponse;
    private  String responseDate;
    private  String creTime;
    private  String moveStatusCd;
    private  String refuseCause;

    public String getRefuseCause() {
        return refuseCause;
    }

    public void setRefuseCause(String refuseCause) {
        this.refuseCause = refuseCause;
    }

    public String getMoveStatusCd() {
        return moveStatusCd;
    }

    public void setMoveStatusCd(String moveStatusCd) {
        this.moveStatusCd = moveStatusCd;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    public String getOutZoneNam() {
        return outZoneNam;
    }

    public void setOutZoneNam(String outZoneNam) {
        this.outZoneNam = outZoneNam;
    }

    public String getOutOrgNam() {
        return outOrgNam;
    }

    public void setOutOrgNam(String outOrgNam) {
        this.outOrgNam = outOrgNam;
    }

    public String getInZoneNam() {
        return inZoneNam;
    }

    public void setInZoneNam(String inZoneNam) {
        this.inZoneNam = inZoneNam;
    }

    public String getInOrgNam() {
        return inOrgNam;
    }

    public void setInOrgNam(String inOrgNam) {
        this.inOrgNam = inOrgNam;
    }

    public String getMoveOutCause() {
        return moveOutCause;
    }

    public void setMoveOutCause(String moveOutCause) {
        this.moveOutCause = moveOutCause;
    }

    public String getIfResponse() {
        return ifResponse;
    }

    public void setIfResponse(String ifResponse) {
        this.ifResponse = ifResponse;
    }

    public String getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(String responseDate) {
        this.responseDate = responseDate;
    }

    public String getCreTime() {
        return creTime;
    }

    public void setCreTime(String creTime) {
        this.creTime = creTime;
    }
}
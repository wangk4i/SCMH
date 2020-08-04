package com.hyd.patInfomanagement.info;

import com.hyd.system.info.ExtInfoObj;
import com.hyd.system.info.Pageinfo;

public class ReportcardsQueryinfo extends Pageinfo {

    private String cd;

    private String pcd;

    private String zoneCd;

    private String nam;

    private String phone;

    private String organCd;


    private String iDCode;

    private String patNam;

    private String diagICD;

    private String levCd;



    public String getiDCode() {
        return iDCode;
    }

    public void setiDCode(String iDCode) {
        this.iDCode = iDCode;
    }

    public String getPatNam() {
        return patNam;
    }

    public void setPatNam(String patNam) {
        this.patNam = patNam;
    }

    public String getDiagICD() {
        return diagICD;
    }

    public void setDiagICD(String diagICD) {
        this.diagICD = diagICD;
    }

    public String getLevCd() {
        return levCd;
    }

    public void setLevCd(String levCd) {
        this.levCd = levCd;
    }

    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrganCd() {
        return organCd;
    }

    public void setOrganCd(String organCd) {
        this.organCd = organCd;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getPcd() {
        return pcd;
    }

    public void setPcd(String pcd) {
        this.pcd = pcd;
    }

    public String getZoneCd() {
        return zoneCd;
    }

    public void setZoneCd(String zoneCd) {
        this.zoneCd = zoneCd;
    }
}

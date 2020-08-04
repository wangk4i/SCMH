package com.hyd.usercontrol.vo;

public class AddUser {

    private String  cd;
    private String  nam;
    private String  phone;
    private String  zoneCd;
    private String  zoneNam;
    private String  organCd;
    private String  orgNam;
    private String  iDcode;
    private String  activeStatus;



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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getOrganCd() {
        return organCd;
    }

    public void setOrganCd(String organCd) {
        this.organCd = organCd;
    }

    public String getOrgNam() {
        return orgNam;
    }

    public void setOrgNam(String orgNam) {
        this.orgNam = orgNam;
    }

    public String getiDcode() {
        return iDcode;
    }

    public void setiDcode(String iDcode) {
        this.iDcode = iDcode;
    }

    public String getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }

    @Override
    public String toString() {
        return "AddUser{" +
                "cd='" + cd + '\'' +
                ", nam='" + nam + '\'' +
                ", phone='" + phone + '\'' +
                ", zoneCd='" + zoneCd + '\'' +
                ", zoneNam='" + zoneNam + '\'' +
                ", organCd='" + organCd + '\'' +
                ", orgNam='" + orgNam + '\'' +
                ", iDcode='" + iDcode + '\'' +
                ", activeStatus='" + activeStatus + '\'' +
                '}';
    }
}

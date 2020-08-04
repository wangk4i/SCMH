package com.hyd.usercontrol.vo;

public class NoActiveUser {

    private String  cd ;
    private String  nam;
    private String  loginAccount;
    private String  zoneNam;
    private String  organCdNam;
    private String  email;
    private String  address;
    private String  duty;
    private String  phone;
    private String  startDate;
    private String  endDate;
    private String  userStatus;
    private String  activeStatus;

    private String orgNam;
    private String departNam;

    public String getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getOrgNam() {
        return orgNam;
    }

    public void setOrgNam(String orgNam) {
        this.orgNam = orgNam;
    }

    public String getDepartNam() {
        return departNam;
    }

    public void setDepartNam(String departNam) {
        this.departNam = departNam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
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

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public String getZoneNam() {
        return zoneNam;
    }

    public void setZoneNam(String zoneNam) {
        this.zoneNam = zoneNam;
    }

    public String getOrganCdNam() {
        return organCdNam;
    }

    public void setOrganCdNam(String organCdNam) {
        this.organCdNam = organCdNam;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}

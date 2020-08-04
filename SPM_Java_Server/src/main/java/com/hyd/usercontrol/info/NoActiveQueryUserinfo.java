package com.hyd.usercontrol.info;

import com.hyd.system.info.ExtInfoObj;

public class NoActiveQueryUserinfo {

    //操作员信息
    private ExtInfoObj extInfoObj;
    //激活状态
    private String activeStatus;
    //用户类型
    private String  duty;
    //所在地区
    private String zoneCd;
    //用户登录名
    private String loginAccount;
    //用户姓名
    private String nam;
    //机构Cd
    private String organCd;
    //开始日期
    private String startDate;

    //用户状态
    private String userStatus;
    //操作人员所属地区
    private String psnZoneCd;

    public ExtInfoObj getExtInfoObj() {
        return extInfoObj;
    }

    public void setExtInfoObj(ExtInfoObj extInfoObj) {
        this.extInfoObj = extInfoObj;
    }

    public String getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getZoneCd() {
        return zoneCd;
    }

    public void setZoneCd(String zoneCd) {
        this.zoneCd = zoneCd;
    }

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    public String getOrganCd() {
        return organCd;
    }

    public void setOrganCd(String organCd) {
        this.organCd = organCd;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getPsnZoneCd() {
        return psnZoneCd;
    }

    public void setPsnZoneCd(String psnZoneCd) {
        this.psnZoneCd = psnZoneCd;
    }
}

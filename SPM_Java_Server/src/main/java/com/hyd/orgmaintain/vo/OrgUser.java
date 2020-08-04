package com.hyd.orgmaintain.vo;

//用户列表
public class OrgUser {

    private String cd;
    //用户姓名
    private String nam;
    //账户名
    private String loginAccount;
    //类型
    private String duty;
    //开始时间
    private String startDate;
    //过期时间
    private String endDate;
    //是否可用
    private String activeStatus;
    //是否过期
    private String userStatus;
    //证件号码
    private String iDCode;
    //最近登录时间
    private String loginTime;
    //登录次数
    private String loginNum;

    @Override
    public String toString() {
        return "OrgUser{" +
                "cd='" + cd + '\'' +
                ", nam='" + nam + '\'' +
                ", loginAccount='" + loginAccount + '\'' +
                ", duty='" + duty + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", activeStatus='" + activeStatus + '\'' +
                ", userStatus='" + userStatus + '\'' +
                ", iDCode='" + iDCode + '\'' +
                ", loginTime='" + loginTime + '\'' +
                ", loginNum='" + loginNum + '\'' +
                '}';
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

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

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

    public String getiDCode() {
        return iDCode;
    }

    public void setiDCode(String iDCode) {
        this.iDCode = iDCode;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginNum() {
        return loginNum;
    }

    public void setLoginNum(String loginNum) {
        this.loginNum = loginNum;
    }
}

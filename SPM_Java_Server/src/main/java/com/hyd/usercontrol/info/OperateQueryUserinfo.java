package com.hyd.usercontrol.info;

import com.hyd.system.info.ExtInfoObj;

public class OperateQueryUserinfo {

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
    //结束日期
    private String endDate;
    //身份证
    private String iDCode;
    //快过期时间
    private String creTime;
    //用户状态
    private String userStatus;
    //操作人员所属地区
    private String psnZoneCd;
    //邮箱
    private String email;
    //用户属性
    private String userattr;
    //通讯地址
    private String address;
    //手机号码
    private String  phone;
    //用户部门
    private String departNam;
    private String cd;


    public ExtInfoObj getExtInfoObj() {
        return extInfoObj;
    }

    public void setExtInfoObj(ExtInfoObj extInfoObj) {
        this.extInfoObj = extInfoObj;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserattr() {
        return userattr;
    }

    public void setUserattr(String userattr) {
        this.userattr = userattr;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDepartNam() {
        return departNam;
    }

    public void setDepartNam(String departNam) {
        this.departNam = departNam;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getiDCode() {
        return iDCode;
    }

    public void setiDCode(String iDCode) {
        this.iDCode = iDCode;
    }

    public String getCreTime() {
        return creTime;
    }

    public void setCreTime(String creTime) {
        this.creTime = creTime;
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

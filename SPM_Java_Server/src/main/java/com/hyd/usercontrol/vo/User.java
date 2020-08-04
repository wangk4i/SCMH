package com.hyd.usercontrol.vo;


import org.apache.ibatis.annotations.Param;

import javax.swing.*;

//用户
public class User {

    private String cd;

    //是否可用
    private String activeStatus;

    //是否过期
    private String userStatus;

    //用户账号状态
    private String state;

    //用户姓名
    private String nam;

    //用户登录名称
    private String loginAccount;

    //密码
    private String pwd;

    //所在地区
    private String  zoneNam;

    //所在地区Cd
    private String  zoneCd;

    //通讯地址
    private String  address;

    //所属机构
    private String  orgNam;

    //用户部门
    private String departNam;

    //开始时间
    private String  startDate;

    //结束时间
    private String  endDate;

    //机构国网编码
    private String orgCd;

    //机构编码
    private String organCd;

    //机构级别Cd
    private String levCd;

    //机构级别
    private String levNam;

    //电话号码
    private String phone;

    //身份证号
    private String iDCode;

    //邮箱
    private String email;

    //职责
    private String duty;

    //创建用户平台编码
    private String creUser;

    //创建机构平台编码
    private String creOrg;

    //最后修改用户平台编码
    private String lastModiCd;

    //最后修改时间
    private String lastModTime;

    //描述
    private String description;


    //账号机构所在地区
    private String userOrganName;

    //用户属性
    private String userattr;

    //用户权限
    private String roleCd;

    //用户+权限
    private String userroleCd;


    public String getUserOrganName() {
        return userOrganName;
    }

    public void setUserOrganName(String userOrganName) {
        this.userOrganName = userOrganName;
    }

    public String getUserroleCd() {
        return userroleCd;
    }

    public void setUserroleCd(String userroleCd) {
        this.userroleCd = userroleCd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRoleCd() {
        return roleCd;
    }

    public void setRoleCd(String roleCd) {
        this.roleCd = roleCd;
    }

    @Override
    public String toString() {
        return "User{" +
                "cd='" + cd + '\'' +
                ", activeStatus='" + activeStatus + '\'' +
                ", userStatus='" + userStatus + '\'' +
                ", state='" + state + '\'' +
                ", nam='" + nam + '\'' +
                ", loginAccount='" + loginAccount + '\'' +
                ", pwd='" + pwd + '\'' +
                ", zoneNam='" + zoneNam + '\'' +
                ", zoneCd='" + zoneCd + '\'' +
                ", address='" + address + '\'' +
                ", orgNam='" + orgNam + '\'' +
                ", departNam='" + departNam + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", orgCd='" + orgCd + '\'' +
                ", organCd='" + organCd + '\'' +
                ", levCd='" + levCd + '\'' +
                ", levNam='" + levNam + '\'' +
                ", phone='" + phone + '\'' +
                ", iDCode='" + iDCode + '\'' +
                ", email='" + email + '\'' +
                ", duty='" + duty + '\'' +
                ", creUser='" + creUser + '\'' +
                ", creOrg='" + creOrg + '\'' +
                ", lastModiCd='" + lastModiCd + '\'' +
                ", lastModTime='" + lastModTime + '\'' +
                ", userattr='" + userattr + '\'' +
                '}';
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getZoneNam() {
        return zoneNam;
    }

    public void setZoneNam(String zoneNam) {
        this.zoneNam = zoneNam;
    }

    public String getZoneCd() {
        return zoneCd;
    }

    public void setZoneCd(String zoneCd) {
        this.zoneCd = zoneCd;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getOrgCd() {
        return orgCd;
    }

    public void setOrgCd(String orgCd) {
        this.orgCd = orgCd;
    }

    public String getOrganCd() {
        return organCd;
    }

    public void setOrganCd(String organCd) {
        this.organCd = organCd;
    }

    public String getLevCd() {
        return levCd;
    }

    public void setLevCd(String levCd) {
        this.levCd = levCd;
    }

    public String getLevNam() {
        return levNam;
    }

    public void setLevNam(String levNam) {
        this.levNam = levNam;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getiDCode() {
        return iDCode;
    }

    public void setiDCode(String iDCode) {
        this.iDCode = iDCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getCreUser() {
        return creUser;
    }

    public void setCreUser(String creUser) {
        this.creUser = creUser;
    }

    public String getCreOrg() {
        return creOrg;
    }

    public void setCreOrg(String creOrg) {
        this.creOrg = creOrg;
    }

    public String getLastModiCd() {
        return lastModiCd;
    }

    public void setLastModiCd(String lastModiCd) {
        this.lastModiCd = lastModiCd;
    }

    public String getLastModTime() {
        return lastModTime;
    }

    public void setLastModTime(String lastModTime) {
        this.lastModTime = lastModTime;
    }

    public String getUserattr() {
        return userattr;
    }

    public void setUserattr(String userattr) {
        this.userattr = userattr;
    }
}

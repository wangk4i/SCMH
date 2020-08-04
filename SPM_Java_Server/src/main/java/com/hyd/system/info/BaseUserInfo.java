package com.hyd.system.info;

import java.util.List;

/**
 * Created by xieshuai on 2020/4/7 18:07
 *  当前操作员基类
 */
public class BaseUserInfo {

    private String duty;

    private boolean fisrtLoginSign;

    private boolean idCodeSign;

    private String loginAccount;

    private String orgCd;

    private String orgNam;

    private String phone;

    private String pwd;

    private List<BaseRoleInfo> roleList;

    private String shareLev;

    private String userCd;

    private String userCdNam;

    private String userRole;

    private String zoneCd;

    private String zoneNam;

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public boolean isFisrtLoginSign() {
        return fisrtLoginSign;
    }

    public void setFisrtLoginSign(boolean fisrtLoginSign) {
        this.fisrtLoginSign = fisrtLoginSign;
    }

    public boolean isIdCodeSign() {
        return idCodeSign;
    }

    public void setIdCodeSign(boolean idCodeSign) {
        this.idCodeSign = idCodeSign;
    }

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public String getOrgCd() {
        return orgCd;
    }

    public void setOrgCd(String orgCd) {
        this.orgCd = orgCd;
    }

    public String getOrgNam() {
        return orgNam;
    }

    public void setOrgNam(String orgNam) {
        this.orgNam = orgNam;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public List<BaseRoleInfo> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<BaseRoleInfo> roleList) {
        this.roleList = roleList;
    }

    public String getShareLev() {
        return shareLev;
    }

    public void setShareLev(String shareLev) {
        this.shareLev = shareLev;
    }

    public String getUserCd() {
        return userCd;
    }

    public void setUserCd(String userCd) {
        this.userCd = userCd;
    }

    public String getUserCdNam() {
        return userCdNam;
    }

    public void setUserCdNam(String userCdNam) {
        this.userCdNam = userCdNam;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
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
}

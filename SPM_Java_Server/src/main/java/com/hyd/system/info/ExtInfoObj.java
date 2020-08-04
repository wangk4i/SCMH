package com.hyd.system.info;

import lombok.Data;

/**
 * 当前操作员返回信息
 */
@Data
public class ExtInfoObj {

    private String expcd;

     private String count;
     //账号所在地区Cd
     private String depCd;
    //账号所在地区
     private String depNam;
    //操作员Cd
     private String operatorCd;
    //操作员姓名
    private String operatorNam;
    //操作员所在机构Cd
     private String organCode;
     //操作员所在机构
     private String organName;
    //操作员Cd
    private String psnCd;
     //操作员权限
    private String userRole;
    //操作员级别
    private String shareLev;

    public String getExpcd() {
        return expcd;
    }

    public void setExpcd(String expcd) {
        this.expcd = expcd;
    }

    public String getShareLev() {
        return shareLev;
    }

    public void setShareLev(String shareLev) {
        this.shareLev = shareLev;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getDepCd() {
        return depCd;
    }

    public void setDepCd(String depCd) {
        this.depCd = depCd;
    }

    public String getDepNam() {
        return depNam;
    }

    public void setDepNam(String depNam) {
        this.depNam = depNam;
    }

    public String getOperatorCd() {
        return operatorCd;
    }

    public void setOperatorCd(String operatorCd) {
        this.operatorCd = operatorCd;
    }

    public String getOperatorNam() {
        return operatorNam;
    }

    public void setOperatorNam(String operatorNam) {
        this.operatorNam = operatorNam;
    }

    public String getOrganCode() {
        return organCode;
    }

    public void setOrganCode(String organCode) {
        this.organCode = organCode;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public String getPsnCd() {
        return psnCd;
    }

    public void setPsnCd(String psnCd) {
        this.psnCd = psnCd;
    }
}

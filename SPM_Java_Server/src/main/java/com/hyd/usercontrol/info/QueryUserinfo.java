package com.hyd.usercontrol.info;

import com.hyd.system.info.BaseUserInfo;
import com.hyd.system.info.ExtInfoObj;
import lombok.Data;

import java.util.List;

@Data
public class QueryUserinfo {



    private ExtInfoObj extInfoObj;


    private String cd;

    private String pcd;

    private String account;

    private String state;

    private String zoneCode;

    private String roleCd;

    private String identity;

    private String nam;

    private String appAuth;

    private String phone;

    private String endData;

    private String deviceID;
    //操作失败返回值
    private String errMsg;
    //操作成功返回值
    private String reminder;

    public String getEndData() {
        return endData;
    }

    public void setEndData(String endData) {
        this.endData = endData;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }


    public ExtInfoObj getExtInfoObj() {
        return extInfoObj;
    }

    public void setExtInfoObj(ExtInfoObj extInfoObj) {
        this.extInfoObj = extInfoObj;
    }

    private List<String> cds;

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public List<String> getCds() {
        return cds;
    }

    public void setCds(List<String> cds) {
        this.cds = cds;
    }

    public String getAppAuth() {
        return appAuth;
    }

    public void setAppAuth(String appAuth) {
        this.appAuth = appAuth;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
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

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public String getRoleCd() {
        return roleCd;
    }

    public void setRoleCd(String roleCd) {
        this.roleCd = roleCd;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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

}

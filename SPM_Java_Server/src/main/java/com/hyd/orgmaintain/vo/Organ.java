package com.hyd.orgmaintain.vo;




import lombok.Data;

import java.util.ArrayList;
import java.util.List;

//机构列表
@Data
public class Organ{

    private String cd;
    //省Cd
    private String provinceCd;

    //市Cd
    private String cityCd;

    //市名称
    private  String parNam;

    //国网编码
    private String zoneCode;

    //区Cd 国网编码
    private String zoneCd;

    //所在区
    private String zoneNam;

    //乡镇Cd
    private String townsCd;

    //所在乡镇
    private String towns;


    //机构名称
    private String nam;

    //机构级别Cd
    private String levCd;

    //机构级别
    private String levNam;

    //机构编码
    private String orgCode;

    //机构类型
    private String orgTpNam;

    //机构类型Cd
    private String orgTpCd;

    //主办单位
    private String hostNam;
    //主办单位Cd
    private String hostCd;


    //经济类型Cd
    private String ecoTpCd;

    //经济类型
    private String ecoTpNam;

    //管理类型Cd
    private String  manaTpCd;

    //管理类型
    private String manaTpNam;

    //机构地址
    private String address;

    //联系电话
    private String  phone;




    //履行直报职能
    private String ifPerform;

    //履行区县质控职能
    private String isBJ;


    //无生效用户
    private String noactive;

    //是否有基层直报用户
    private String straight;

    //是否有县本级数据质控用户
    private String countyControl;

    //生效用戶數
    private String userTotal;

    //是否停用
    private String state;


    public boolean getIsOther()
    {
        boolean result=false;
        if (ifPerform!=null && ifPerform.equals("1"))
            return  result;
        if (isBJ!=null && isBJ.equals("1"))
            return  result;
            result=true;
        return  result;
    }


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private List<OrgUser> orgUser=new ArrayList<>();

    public List<OrgUser> getOrgUser() {
        return orgUser;
    }


    @Override
    public String toString() {
        return "Organ{" +
                "cd='" + cd + '\'' +
                ", provinceCd='" + provinceCd + '\'' +
                ", cityCd='" + cityCd + '\'' +
                ", parNam='" + parNam + '\'' +
                ", zoneCode='" + zoneCode + '\'' +
                ", zoneCd='" + zoneCd + '\'' +
                ", zoneNam='" + zoneNam + '\'' +
                ", townsCd='" + townsCd + '\'' +
                ", towns='" + towns + '\'' +
                ", nam='" + nam + '\'' +
                ", levCd='" + levCd + '\'' +
                ", levNam='" + levNam + '\'' +
                ", orgCode='" + orgCode + '\'' +
                ", orgTpNam='" + orgTpNam + '\'' +
                ", orgTpCd='" + orgTpCd + '\'' +
                ", hostNam='" + hostNam + '\'' +
                ", hostCd='" + hostCd + '\'' +
                ", ecoTpCd='" + ecoTpCd + '\'' +
                ", ecoTpNam='" + ecoTpNam + '\'' +
                ", manaTpCd='" + manaTpCd + '\'' +
                ", manaTpNam='" + manaTpNam + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", ifPerform='" + ifPerform + '\'' +
                ", isBJ='" + isBJ + '\'' +
                ", noactive='" + noactive + '\'' +
                ", straight='" + straight + '\'' +
                ", countyControl='" + countyControl + '\'' +
                ", userTotal='" + userTotal + '\'' +
                ", orgUser=" + orgUser +
                '}';
    }

    public void setOrgUser(List<OrgUser> orgUser) {
        this.orgUser = orgUser;
    }



    public String getParNam() {
        return parNam;
    }

    public void setParNam(String parNam) {
        this.parNam = parNam;
    }

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getProvinceCd() {
        return provinceCd;
    }

    public void setProvinceCd(String provinceCd) {
        this.provinceCd = provinceCd;
    }

    public String getCityCd() {
        return cityCd;
    }

    public void setCityCd(String cityCd) {
        this.cityCd = cityCd;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
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

    public String getTownsCd() {
        return townsCd;
    }

    public void setTownsCd(String townsCd) {
        this.townsCd = townsCd;
    }

    public String getTowns() {
        return towns;
    }

    public void setTowns(String towns) {
        this.towns = towns;
    }

    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
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

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgTpNam() {
        return orgTpNam;
    }

    public void setOrgTpNam(String orgTpNam) {
        this.orgTpNam = orgTpNam;
    }

    public String getOrgTpCd() {
        return orgTpCd;
    }

    public void setOrgTpCd(String orgTpCd) {
        this.orgTpCd = orgTpCd;
    }

    public String getHostNam() {
        return hostNam;
    }

    public void setHostNam(String hostNam) {
        this.hostNam = hostNam;
    }

    public String getHostCd() {
        return hostCd;
    }

    public void setHostCd(String hostCd) {
        this.hostCd = hostCd;
    }

    public String getEcoTpCd() {
        return ecoTpCd;
    }

    public void setEcoTpCd(String ecoTpCd) {
        this.ecoTpCd = ecoTpCd;
    }

    public String getEcoTpNam() {
        return ecoTpNam;
    }

    public void setEcoTpNam(String ecoTpNam) {
        this.ecoTpNam = ecoTpNam;
    }

    public String getManaTpCd() {
        return manaTpCd;
    }

    public void setManaTpCd(String manaTpCd) {
        this.manaTpCd = manaTpCd;
    }

    public String getManaTpNam() {
        return manaTpNam;
    }

    public void setManaTpNam(String manaTpNam) {
        this.manaTpNam = manaTpNam;
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

    public String getIfPerform() {
        return ifPerform;
    }

    public void setIfPerform(String ifPerform) {
        this.ifPerform = ifPerform;
    }

    public String getIsBJ() {
        return isBJ;
    }

    public void setIsBJ(String isBJ) {
        this.isBJ = isBJ;
    }

    public String getNoactive() {
        return noactive;
    }

    public void setNoactive(String noactive) {
        this.noactive = noactive;
    }

    public String getStraight() {
        return straight;
    }

    public void setStraight(String straight) {
        this.straight = straight;
    }

    public String getCountyControl() {
        return countyControl;
    }

    public void setCountyControl(String countyControl) {
        this.countyControl = countyControl;
    }

    public String getUserTotal() {
        return userTotal;
    }

    public void setUserTotal(String userTotal) {
        this.userTotal = userTotal;
    }
}

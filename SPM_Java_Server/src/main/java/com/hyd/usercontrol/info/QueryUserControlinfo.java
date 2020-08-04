package com.hyd.usercontrol.info;

import com.hyd.system.info.BaseUserInfo;
import com.hyd.system.info.ExtInfoObj;
import lombok.Data;

@Data
public class QueryUserControlinfo extends BaseUserInfo {

    private ExtInfoObj extInfoObj;

    private String cd;
    //省Cd
    private String provinceCd;

    private String provinceNam;

    //市Cd
    private String  cityCd;

    //市名称
    private  String cityNam;

    //县Cd
    private String zoneCd;

    //县名称
    private String zoneNam;

    //乡镇Cd
    private String townsCd;

    //所在乡镇
    private String townsNam;

    //机构级别Cd
    private String levCd;

    //机构级别
    private String levNam;


    @Override
    public String toString() {
        return "QueryUserControlinfo{" +
                "provinceCd='" + provinceCd + '\'' +
                ", provinceNam='" + provinceNam + '\'' +
                ", cityCd='" + cityCd + '\'' +
                ", cityNam='" + cityNam + '\'' +
                ", zoneCd='" + zoneCd + '\'' +
                ", zoneNam='" + zoneNam + '\'' +
                ", townsCd='" + townsCd + '\'' +
                ", townsNam='" + townsNam + '\'' +
                ", levCd='" + levCd + '\'' +
                ", levNam='" + levNam + '\'' +
                '}';
    }

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

    public String getProvinceCd() {
        return provinceCd;
    }

    public void setProvinceCd(String provinceCd) {
        this.provinceCd = provinceCd;
    }

    public String getProvinceNam() {
        return provinceNam;
    }

    public void setProvinceNam(String provinceNam) {
        this.provinceNam = provinceNam;
    }

    public String getCityCd() {
        return cityCd;
    }

    public void setCityCd(String cityCd) {
        this.cityCd = cityCd;
    }

    public String getCityNam() {
        return cityNam;
    }

    public void setCityNam(String cityNam) {
        this.cityNam = cityNam;
    }

    @Override
    public String getZoneCd() {
        return zoneCd;
    }

    @Override
    public void setZoneCd(String zoneCd) {
        this.zoneCd = zoneCd;
    }

    @Override
    public String getZoneNam() {
        return zoneNam;
    }

    @Override
    public void setZoneNam(String zoneNam) {
        this.zoneNam = zoneNam;
    }

    public String getTownsCd() {
        return townsCd;
    }

    public void setTownsCd(String townsCd) {
        this.townsCd = townsCd;
    }

    public String getTownsNam() {
        return townsNam;
    }

    public void setTownsNam(String townsNam) {
        this.townsNam = townsNam;
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
}

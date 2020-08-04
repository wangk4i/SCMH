package com.hyd.qualitycontrol.info;


import com.hyd.system.info.ExtInfoObj;
import com.hyd.system.info.Pageinfo;
import lombok.Data;

@Data
public class NotInspectYearPatinfo extends Pageinfo {

    private String organCd;
    private String zoneCd;
    private String baseStatusCd;


    public String getOrganCd() {
        return organCd;
    }

    public void setOrganCd(String organCd) {
        this.organCd = organCd;
    }

    public String getZoneCd() {
        return zoneCd;
    }

    public void setZoneCd(String zoneCd) {
        this.zoneCd = zoneCd;
    }

    public String getBaseStatusCd() {
        return baseStatusCd;
    }

    public void setBaseStatusCd(String baseStatusCd) {
        this.baseStatusCd = baseStatusCd;
    }


}

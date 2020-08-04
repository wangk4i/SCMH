package com.hyd.exportexcel.info;

import com.hyd.system.info.Pageinfo;

public class Annualorganauditinfo extends Pageinfo {

    private String organCd;
    private String yearDateEnd;
    private String yearDateStart;
    private String zoneCd;

    public Annualorganauditinfo(String zoneCd,String organCd, String yearDateStart, String yearDateEnd ) {
        this.organCd = organCd;
        this.yearDateEnd = yearDateEnd;
        this.yearDateStart = yearDateStart;
        this.zoneCd = zoneCd;
    }

    public String getOrganCd() {
        return organCd;
    }

    public void setOrganCd(String organCd) {
        this.organCd = organCd;
    }

    public String getYearDateEnd() {
        return yearDateEnd;
    }

    public void setYearDateEnd(String yearDateEnd) {
        this.yearDateEnd = yearDateEnd;
    }

    public String getYearDateStart() {
        return yearDateStart;
    }

    public void setYearDateStart(String yearDateStart) {
        this.yearDateStart = yearDateStart;
    }

    public String getZoneCd() {
        return zoneCd;
    }

    public void setZoneCd(String zoneCd) {
        this.zoneCd = zoneCd;
    }
}

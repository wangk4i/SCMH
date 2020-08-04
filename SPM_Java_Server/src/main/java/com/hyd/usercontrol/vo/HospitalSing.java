package com.hyd.usercontrol.vo;

import java.util.ArrayList;
import java.util.List;

public class HospitalSing {


   private String signorgNam;

   private String signorgCd;

   private List<Hospital> hospital;

   private String signTime;

    public List<Hospital> getHospital() {
        return hospital;
    }

    public void setHospital(List<Hospital> hospital) {
        this.hospital = hospital;
    }

    public String getSignorgNam() {
        return signorgNam;
    }

    public void setSignorgNam(String signorgNam) {
        this.signorgNam = signorgNam;
    }



    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public String getSignorgCd() {
        return signorgCd;
    }

    public void setSignorgCd(String signorgCd) {
        this.signorgCd = signorgCd;
    }


}

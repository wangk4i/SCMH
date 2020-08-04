package com.hyd.usercontrol.vo;

public class Hospital {



    private String hospitalNam;

    private String hospitalCd;

    @Override
    public String toString() {
        return "Hospital{" +
                "hospitalNam='" + hospitalNam + '\'' +
                ", hospitalCd='" + hospitalCd + '\'' +
                '}';
    }

    public String getHospitalNam() {
        return hospitalNam;
    }

    public void setHospitalNam(String hospitalNam) {
        this.hospitalNam = hospitalNam;
    }

    public String getHospitalCd() {
        return hospitalCd;
    }

    public void setHospitalCd(String hospitalCd) {
        this.hospitalCd = hospitalCd;
    }


}

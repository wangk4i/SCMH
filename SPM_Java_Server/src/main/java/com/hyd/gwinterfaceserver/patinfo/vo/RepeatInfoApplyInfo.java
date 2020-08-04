package com.hyd.gwinterfaceserver.patinfo.vo;

import lombok.Data;

@Data
public class RepeatInfoApplyInfo
    {
        public String repeatInfoId ;
        public String dischargeInformationId ;
        public String orgCountyCode ;
        public String orgCode ;
        public String patientName ;
        public String genderCode ;
        public String idCode ;
        public String birthDate ;
        public String guardianName ;
        public String guardianTel ;
        public String relationToPatient ;
        public String livingAddressDetails ;
        public String livingAddressAttributionCode ;
        public String livingAddressCode ;
        public String diagnosis ;
    }
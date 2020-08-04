package com.hyd.exportexcel.vo;

import lombok.Data;

import java.lang.reflect.Method;

public class Annualorganauditstate {

    public static int OrganNam = 1;                  //单位

    public static int ZoneNam = 2;                 //市

    public static int ZoneParNam = 3;                    //县

    public static int FoundedTime = 6;                     //精神成立日

    public static int OrgType = 8;               // 机构类别

    public static int OrgLv = 10;               //机构级别

    public static int OrgDept = 9;                //部门

    public static int PsychiatryBedNum = 22;                                 //编制床位数

    public static int PsychiatryIsBedNum = 23;                                    //实有床位数

    public static int PsychiatryAdmissionsInNum = 115;                                                    //入院

    public static int PsychiatryAdmissionsOutNum = 116;                                                     //出院

    public static int PsychiatryEDNum = 113;                               //急诊

    public static int PrepareNum = 29;                     //编制人数

    public static int ElevenNum = 30;                   //在岗人

    public static int Psychiatrists = 38;                   //医师数

    public static int YearInTotal = 94;                      //总收入

    public static int YearOutTotal = 108;                          //总支出

    public static int SecondlvType1 = 151;

    public static int SecondlvType2 = 153;

    public static int SecondlvType3 = 155;

    public static int SecondlvType4 = 157;

    public static int SecondlvType5 = 159;

    public static int SecondlvType7 = 161;

    public static int SecondlvType8 = 163;

    public static int PatInfoState = 167;                         //是否收治

    public static int PatInfoSum = 169;                       //收治保障水平

    public static int PatInfoNum = 168;                        //收治人数

    public static int NotPatInfoState = 170;                               //三无

    public static int NotPatInfoSum = 172;                           //三无保障水平

    public static int NotPatInfoNum = 171;                                 //三无人


    public static Integer getAnnualorganauditstate(String field) {
        try {
            return (int) Annualorganauditstate.class.getField(field).get(null);
        } catch (Exception e) {
        }
        return null;
    }
}









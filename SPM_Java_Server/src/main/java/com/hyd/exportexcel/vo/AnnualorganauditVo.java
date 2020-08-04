package com.hyd.exportexcel.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;




@Data
public class AnnualorganauditVo{
    private String number;
    private String organNam;     //单位
    private String zoneNam;      //市
    private String zoneParNam;   //县
    private String foundedTime;  //精神成立日
    private String orgType;    // 机构类别
    private String orgLv;       //机构级别
    private String orgDept;     //部门
    private String psychiatryBedNum;   //编制床位数
    private String psychiatryIsBedNum;  //实有床位数
    private String psychiatryAdmissionsInNum;   //入院
    private String psychiatryAdmissionsOutNum;  //出院
    private String psychiatryEDNum;  //急诊
    private String prepareNum;   //编制人数
    private String elevenNum;   //在岗人
    private String psychiatrists; //医师数
    private String yearInTotal;  //总收入
    private String yearOutTotal;   //总支出
    private String secondlvType1;
    private String secondlvType2;
    private String secondlvType3;
    private String secondlvType4;
    private String secondlvType5;
    private String secondlvType7;
    private String secondlvType8;
    private String patInfoState;  //是否收治
    private String patInfoSum;    //收治保障水平
    private String patInfoNum;     //收治人数
    private String notPatInfoState;  //三无
    private String notPatInfoSum;  //三无保障水平
    private String notPatInfoNum;  //三无人
}









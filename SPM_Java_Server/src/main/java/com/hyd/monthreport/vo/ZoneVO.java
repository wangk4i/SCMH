package com.hyd.monthreport.vo;

import lombok.Data;


/**
 * Created by xieshuai on 2020/4/7 17:22
 *  地区vo
 */

@Data
public class ZoneVO {

    private String id;

    private String title;

    private String parentId;

    private String levIndex;

    //辖区常驻人口
    private Integer czrk;

    //累计建档患者人数
    private Integer ljjd;

    //本月建档患者人数
    private Integer byjd;

    //累计死亡患者人数
    private Integer ljsw;

    //本月报告死亡患者人数
    private Integer bybg;

    //在册患者人数
    private Integer zchz;

    //知情同意患者人数
    private Integer zqty;

    //报告患病率
    private Integer hblv;
}

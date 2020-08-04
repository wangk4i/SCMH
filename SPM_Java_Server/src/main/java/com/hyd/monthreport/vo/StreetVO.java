package com.hyd.monthreport.vo;

import lombok.Data;

/**
 * Created by xieshuai on 2020/4/9 10:00
 */
@Data
public class StreetVO {

    /**
     * 街道编码
     */
    private String cd;

    /**
     * 街道名称
     */
    private String nam;

    /**
     * 当前地区是否被选中
     */
    private String isQuota;

}

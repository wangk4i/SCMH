package com.hyd.monthreport.vo;

import lombok.Data;

import java.util.List;

/**
 * Created by xieshuai on 2020/4/10 10:45
 */

@Data
public class AuthCityVO {

    /**
     * 市级编码
     */
    private String cd;

    /**
     * 市名称
     */
    private String nam;

    /**
     * 已授权区县数量
     */
    private Integer districtCount;

    /**
     * 已授权街道数量
     */
    private Integer streetCount;


    private List<AuthCountyVO> countyList;
}

package com.hyd.monthreport.info;

import lombok.Data;

/**
 * Created by xieshuai on 2020/4/9 9:55
 */

@Data
public class QueryStreetInfo {

    /**
     * 省级编码
     */
    private String provinceId;

    /**
     * 市级编码
     */
    private String cityId;

    /**
     * 区县编码
     */
    private String countyId;

    /**
     * 当前操作地区编码
     */
    private String thisCountyId;
}

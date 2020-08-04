package com.hyd.monthreport.info;

import lombok.Data;

/**
 * Created by xieshuai on 2020/4/9 14:24
 */

@Data
public class QueryZoneInfo {

    /**
     * 当前操作人的地区编码
     */
    private String provinceId;

    /**
     * 当前的区县信息编码
     */
    private String countyId;

    private String treetId;

}

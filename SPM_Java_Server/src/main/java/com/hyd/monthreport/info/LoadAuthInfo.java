package com.hyd.monthreport.info;

import lombok.Data;

/**
 * Created by xieshuai on 2020/4/8 18:29
 *  加载地区授权情况
 */

@Data
public class LoadAuthInfo {

    /**
     * 当前操作员编号
     */
    private String cd;

    /**
     * 当前操作员姓名
     */
    private String name;

    /**
     * 当前操作的地区
     */
    private String thisZoneId;


}

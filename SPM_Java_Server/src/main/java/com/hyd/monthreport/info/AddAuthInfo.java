package com.hyd.monthreport.info;

import com.hyd.system.info.BaseUserInfo;
import lombok.Data;

import java.util.List;

/**
 *  Created by xieshuai on 2020/4/7 18:21
 *  新增地区授权info
 */

@Data
public class AddAuthInfo extends BaseUserInfo {

    /**
     * 省级编码
     */
    private String provinceId;

    /**
     * 市级编码
     */
    private String cityId;

    /**
     * 区县级编码
     */
    private String countyId;

    /**
     * 当前操作地区编码
     */
    private String thisCountId;

    /**
     * 街道信息
     */
    private List<StreetInfo> streetList;


}

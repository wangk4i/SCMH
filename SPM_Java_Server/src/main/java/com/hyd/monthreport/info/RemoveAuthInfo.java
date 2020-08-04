package com.hyd.monthreport.info;

import com.hyd.system.info.BaseUserInfo;
import lombok.Data;

/**
 * Created by xieshuai on 2020/4/8 16:21
 */

@Data
public class RemoveAuthInfo extends BaseUserInfo {

    /**
     * 当前操作地区
     */
    private String thisZoneId;

    /**
     * 需要移除的地区信息
     */
    private String streetId;


}

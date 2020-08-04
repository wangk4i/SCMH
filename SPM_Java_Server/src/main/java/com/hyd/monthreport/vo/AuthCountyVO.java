package com.hyd.monthreport.vo;

import lombok.Data;

import java.util.List;

/**
 * Created by xieshuai on 2020/4/10 10:46
 */

@Data
public class AuthCountyVO {

    /**
     * 区县编码
     */
    private String cd;

    /**
     * 区县名称
     */
    private String nam;

    /**
     * 街道信息
     */
    private List<StreetVO> streetVOS;
}

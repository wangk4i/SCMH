package com.hyd.monthreport.info;

import lombok.Data;

import java.util.List;

/**
 * Created by xieshuai on 2020/4/14 15:05
 */

@Data
public class AreaInfo {

    private String areaId;

    private String areaNam;

    private String authZoneCd;

    private String userCd;

    private List<AreaZoneInfo> areaCityInfos;
}

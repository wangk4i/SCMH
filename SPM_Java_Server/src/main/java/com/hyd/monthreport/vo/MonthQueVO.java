package com.hyd.monthreport.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;


/**
 * Created by xieshuai on 2020/4/15 14:34
 *  报表自定义查询VO
 */

@Data
public class MonthQueVO {

    /**
     * 乡镇视图treeTable
     */
    List<Map<String, Object>> streetViewTables;

    /**
     * 机构管理视图treeTable
     */
    List<Map<String, Object>> organViewTables;

    /**
     * 行政区域视图treeTable
     */
    List<Map<String, Object>> regionsViewTables;

}

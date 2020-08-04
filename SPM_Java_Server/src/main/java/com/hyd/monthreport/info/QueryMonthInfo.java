package com.hyd.monthreport.info;


import java.util.List;

/**
 * Created by xieshuai on 2020/4/15 10:20
 */


public class QueryMonthInfo {

    /**
     * 报表视图类型
     */
    private String treetableType;

    /**
     * 报表类型
     */
    private String typeNam;

    /**
     * 报表年份
     */
    private String year;

    /**
     * 报表月份
     */
    private String month;


    private List<MonthAreaInfo> areaList;


    public String getTreetableType() {
        return treetableType;
    }

    public void setTreetableType(String treetableType) {
        this.treetableType = treetableType;
    }

    public String getTypeNam() {
        return typeNam;
    }

    public void setTypeNam(String typeNam) {
        this.typeNam = typeNam;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public List<MonthAreaInfo> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<MonthAreaInfo> areaList) {
        this.areaList = areaList;
    }
}

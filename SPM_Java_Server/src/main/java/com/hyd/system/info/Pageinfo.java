package com.hyd.system.info;

import lombok.Data;


public class Pageinfo {


    //当前页
    private Integer page;

    //每页条数
    private Integer limit=10;

    //总条数
    private Integer count;

    //返回错误
    private String errMsg;

    //开始页码
    private Integer startNum;

    //结束页码
    private Integer endNum;


    private ExtInfoObj extInfoObj;
    private String code;
    private String expcd;
    private String name;
    private String level;


    public ExtInfoObj getExtInfoObj() {
        return extInfoObj;
    }

    public void setExtInfoObj(ExtInfoObj extInfoObj) {
        this.extInfoObj = extInfoObj;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getExpcd() {
        return expcd;
    }

    public void setExpcd(String expcd) {
        this.expcd = expcd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getStartNum() {
        return startNum;
    }

    public void setStartNum(Integer startNum) {
        this.startNum = startNum;
    }

    public Integer getEndNum() {
        return endNum;
    }

    public void setEndNum(Integer endNum) {
        this.endNum = endNum;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}

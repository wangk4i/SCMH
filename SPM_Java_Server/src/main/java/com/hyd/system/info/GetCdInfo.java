package com.hyd.system.info;


/**
 * Created by xieshuai on 2020/4/8 10:58
 *
 */

public class GetCdInfo {

    /**
     * 主键前缀
     */
    private String prefix;

    /**
     * 日期类型 例yymmdd
     */
    private String datetype;

    private Integer flowlen;

    /**
     * 返回值参数
     */
    private String retStr;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getDatetype() {
        return datetype;
    }

    public void setDatetype(String datetype) {
        this.datetype = datetype;
    }

    public Integer getFlowlen() {
        return flowlen;
    }

    public void setFlowlen(Integer flowlen) {
        this.flowlen = flowlen;
    }

    public String getRetStr() {
        return retStr;
    }

    public void setRetStr(String retStr) {
        this.retStr = retStr;
    }
}

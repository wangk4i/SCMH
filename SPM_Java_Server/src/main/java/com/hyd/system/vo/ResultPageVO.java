package com.hyd.system.vo;

/**
 * Created by xieshuai on 2020/4/7 17:59
 */


public class ResultPageVO {

    private Integer count;

    private Integer code;

    private String message;

    private Object data;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

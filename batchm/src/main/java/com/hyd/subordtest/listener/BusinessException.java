package com.hyd.subordtest.listener;

public class BusinessException extends RuntimeException{

    //默认500
    private Integer code = 500;

    //异常提示信息
    private String msg;

    //异常堆栈信息，默认使用StactException处理
    private Object data;

    public BusinessException(String msg, Object data) {

        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() { return code; }

    public void setCode(Integer code) { this.code = code; }

    public String getMsg() { return msg; }

    public void setMsg(String msg) { this.msg = msg; }

    public Object getData() { return data; }

    public void setData(Object data) { this.data = data; }
}

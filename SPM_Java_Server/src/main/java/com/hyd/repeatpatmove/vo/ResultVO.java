package com.hyd.repeatpatmove.vo;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/4 15:06
 */
public class ResultVO<T> {
    private Integer code;
    private String errMessage;
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

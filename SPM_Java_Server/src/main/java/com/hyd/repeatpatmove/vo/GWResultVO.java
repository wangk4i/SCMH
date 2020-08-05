package com.hyd.repeatpatmove.vo;

import com.google.gson.annotations.SerializedName;

/** 国网协同接口返回值
 * @author wangkai
 * @date 2020/8/4 11:05
 */
public class GWResultVO<T> {
    private Integer code;
    @SerializedName("message")
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

    @Override
    public String toString() {
        return "{" +
                "code=" + code +
                ", errMessage='" + errMessage + '\'' +
                ", data=" + data +
                '}';
    }
}

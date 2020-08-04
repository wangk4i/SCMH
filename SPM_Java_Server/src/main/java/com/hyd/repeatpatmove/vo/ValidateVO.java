package com.hyd.repeatpatmove.vo;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/4 10:47
 */
public class ValidateVO {
    private boolean isError;
    private String errMessage;

    public ValidateVO() {
        this.isError = true;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}

package com.hyd.system.vo;

import com.hyd.gwinterfaceserver.patrptcard.info.PatMoveOut;

public class ValidateVO {
    private boolean isError;
    private String errMessage;
    private String data;

    public boolean isError() { return isError; }

    public void setError(boolean error) { isError = error; }

    public String getErrMessage() { return errMessage; }

    public void setErrMessage(String errMessage) { this.errMessage = errMessage; }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}

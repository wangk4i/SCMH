package com.hyd.repeatpatmove.exceptclass;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/4 15:28
 */
public class BizException extends RuntimeException{
    private Integer code;

    private String errMsg;

    // 异常堆栈信息
    private Object data;

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public BizException(String errMsg, Object data) {
        this.code = -1;
        this.errMsg = errMsg;
        this.data = data;
    }

    public Integer getCode() { return code; }

    public void setCode(Integer code) { this.code = code; }

    public String getErrMsg() { return errMsg; }

    public void setErrMsg(String errMsg) { this.errMsg = errMsg; }

    public Object getData() { return data; }

    public void setData(Object data) { this.data = data; }
}

package com.hyd.system.exceptclass;



/**
 * Created by xieshuai on 2020/4/10 15:41
 *   业务异常
 *   该状态下异常不会进入Log中，只响应提示信息
 */

public class BusineExceptions extends RuntimeException{

   private String msg;

   private Integer code;

   private Object data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public BusineExceptions(String msg){
        this.msg = msg;
    }


    public BusineExceptions(String msg,Integer code) {
        this.msg = msg;
        this.code = code;
    }

    public BusineExceptions(String msg,Integer code,Object data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }
}

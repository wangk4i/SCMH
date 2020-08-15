package com.hyd.endurance.domain;

import lombok.Data;

@Data
public class ResponseDO {
    private Integer code;

    private String message;

    private String responseString;

    public ResponseDO(Integer code) {
        this.code = code;
    }
}

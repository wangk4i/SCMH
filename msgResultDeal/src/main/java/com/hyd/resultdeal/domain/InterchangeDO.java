package com.hyd.resultdeal.domain;

import lombok.Data;

@Data
public class InterchangeDO {
    private Boolean result;
    private Integer total;
    private String DocumentId;
    private String desc;
    private String id;

    @Override
    public String toString() {
        return "{" +
                "result=" + result +
                ", total=" + total +
                ", DocumentId='" + DocumentId + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}

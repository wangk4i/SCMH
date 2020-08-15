package com.hyd.subordmq.domain.info;

import lombok.Data;

@Data
public class MessageDataInfo {
    private Integer moveCate;
    private String zone;
    private String organ;
    private String patId;
    private String reportId;
    private String operatorName;
    private String startTime;
}

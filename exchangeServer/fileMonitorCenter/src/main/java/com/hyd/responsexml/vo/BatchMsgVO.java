package com.hyd.responsexml.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BatchMsgVO {
    private String batchID;
    private LocalDateTime createTime;
    private List<String> files;
}

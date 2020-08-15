package com.hyd.receivecdc.vo;

import lombok.Data;

import java.util.Set;

/**
 * Created by xieshuai on 2020/8/1 上午 10:29
 */
@Data
public class MessageVO {

    private Set<String> messageSet;

    private String uuid;
}

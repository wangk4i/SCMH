package com.hyd.responsexml.vo;

import lombok.Data;

import java.util.Set;

/**
 * Created by xieshuai on 2020/8/8 上午 10:44
 */
@Data
public class MessageVO {

    private String uuid;

    private Set<String> messageSet;
}

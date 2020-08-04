package com.hyd.gwinterfaceserver.patinfo.vo;

import lombok.Data;

import java.util.List;

@Data
public class getRepeatInfoByIdcodeResponse {
    public List<PatInfo2> patInfo;
}
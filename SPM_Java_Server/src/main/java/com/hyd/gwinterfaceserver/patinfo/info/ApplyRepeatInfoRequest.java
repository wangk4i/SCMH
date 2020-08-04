package com.hyd.gwinterfaceserver.patinfo.info;

import com.hyd.gwinterfaceserver.patinfo.vo.ExtInfo;
import com.hyd.system.info.ExtInfoObj;
import lombok.Data;

@Data
public class ApplyRepeatInfoRequest {
    public String repeatInfoId;
    public String state;
    public String moveCode;
    public ExtInfo extInfoObj;
}
package com.hyd.gwinterfaceserver.patinfo.info;

import com.hyd.gwinterfaceserver.patinfo.vo.ExtInfo;
import com.hyd.system.info.ExtInfoObj;
import lombok.Data;

@Data
public class getRepeatInfoByIdcodeRequest {
    public ExtInfo extInfoObj;
    public String idcode;
}
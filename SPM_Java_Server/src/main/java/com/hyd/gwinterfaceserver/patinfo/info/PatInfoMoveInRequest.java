package com.hyd.gwinterfaceserver.patinfo.info;

import com.hyd.gwinterfaceserver.patinfo.vo.ExtInfo;
import com.hyd.system.info.ExtInfoObj;
import lombok.Data;

@Data
public class PatInfoMoveInRequest {
    public String moveCode;
    public String inOrganCode;
    public String patCode;
    public ExtInfo extInfoObj;
}
package com.hyd.gwinterfaceserver.patinfo.info;

import com.hyd.gwinterfaceserver.patinfo.vo.ExtInfo;
import com.hyd.system.info.ExtInfoObj;
import lombok.Data;

@Data
public class PatInfoRefuseMoveInRequest {
    /// <summary>
    /// 流转编号
    /// </summary>
    public String moveCode;
    /// <summary>
    /// 拒绝原因
    /// </summary>
    public String refusedCause;

    public ExtInfo extInfoObj;
}
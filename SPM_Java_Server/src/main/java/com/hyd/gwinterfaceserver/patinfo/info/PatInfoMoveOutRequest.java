package com.hyd.gwinterfaceserver.patinfo.info;

import com.hyd.gwinterfaceserver.patinfo.vo.ExtInfo;
import com.hyd.gwinterfaceserver.patinfo.vo.MoveOutInfo;
import com.hyd.system.info.ExtInfoObj;
import lombok.Data;

@Data
public class PatInfoMoveOutRequest {
    public MoveOutInfo moveOutInfo;
    public ExtInfo extInfoObj;
}
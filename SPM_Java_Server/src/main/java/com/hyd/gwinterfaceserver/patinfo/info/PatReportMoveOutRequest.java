package com.hyd.gwinterfaceserver.patinfo.info;

import com.hyd.gwinterfaceserver.patinfo.vo.ExtInfo;
import com.hyd.gwinterfaceserver.patinfo.vo.MoveOutInfo;
import com.hyd.system.info.ExtInfoObj;
import lombok.Data;

@Data
public class PatReportMoveOutRequest {
    private ExtInfo extInfoObj;
    private MoveOutInfo moveOutInfo;

    public ExtInfo getExtInfoObj() {
        return extInfoObj;
    }

    public void setExtInfoObj(ExtInfo extInfoObj) {
        this.extInfoObj = extInfoObj;
    }

    public MoveOutInfo getMoveOutInfo() {
        return moveOutInfo;
    }

    public void setMoveOutInfo(MoveOutInfo moveOutInfo) {
        this.moveOutInfo = moveOutInfo;
    }
}

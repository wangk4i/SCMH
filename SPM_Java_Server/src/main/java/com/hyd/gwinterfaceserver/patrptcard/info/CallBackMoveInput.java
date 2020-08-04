package com.hyd.gwinterfaceserver.patrptcard.info;

import lombok.Data;

//迁出收回
@Data
public class CallBackMoveInput {
    /// <summary>
    /// 待收回的流转信息主键。必须指定
    /// </summary>
    public String moveInAndOutId;

    public String isPK;
}
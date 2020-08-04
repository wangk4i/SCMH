package com.hyd.gwinterfaceserver.patinfo.vo;

import lombok.Data;

@Data
public class PatInfoIsSyn {
    //国网主键
   public String fIELDPK;
    //同步状态
    public String syncStatus;
    //同步时间
    public String syncTime;
    //同步错误
    public String syncError;
    //知情同意时间
    public String iCSignDate;
}

package com.hyd.orgmaintain.info;

import com.hyd.system.info.BaseUserInfo;
import lombok.Data;

@Data
public class QueryOrganinfo extends BaseUserInfo {

    private String cd;

    private String pcd;

    private String State;

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getPcd() {
        return pcd;
    }

    public void setPcd(String pcd) {
        this.pcd = pcd;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }
}

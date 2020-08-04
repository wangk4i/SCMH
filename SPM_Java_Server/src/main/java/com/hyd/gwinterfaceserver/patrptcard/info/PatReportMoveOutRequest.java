package com.hyd.gwinterfaceserver.patrptcard.info;

import com.hyd.gwinterfaceserver.patrptcard.vo.ExtInfo;
import lombok.Data;

@Data
public class PatReportMoveOutRequest {
    private ExtInfo extInfoObj;
    private String cd;
    private String rptCardID;
    private String currentZone;
    private String currentOrgan;
    private String moveoutType;
    private String moveOutZone;
    private String moveOutOrgan;
    private String signedDate;

    private  boolean checkOrganZb1;

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    public String getSignedDate() {
        return signedDate;
    }

    public void setSignedDate(String signedDate) {
        this.signedDate = signedDate;
    }

    public ExtInfo getExtInfoObj() {
        return extInfoObj;
    }

    public void setExtInfoObj(ExtInfo extInfoObj) {
        this.extInfoObj = extInfoObj;
    }

    public String getRptCardID() {
        return rptCardID;
    }

    public void setRptCardID(String rptCardID) {
        this.rptCardID = rptCardID;
    }

    public String getCurrentZone() {
        return currentZone;
    }

    public void setCurrentZone(String currentZone) {
        this.currentZone = currentZone;
    }

    public String getCurrentOrgan() {
        return currentOrgan;
    }

    public void setCurrentOrgan(String currentOrgan) {
        this.currentOrgan = currentOrgan;
    }

    public String getMoveoutType() {
        return moveoutType;
    }

    public void setMoveoutType(String moveoutType) {
        this.moveoutType = moveoutType;
    }

    public String getMoveOutZone() {
        return moveOutZone;
    }

    public void setMoveOutZone(String moveOutZone) {
        this.moveOutZone = moveOutZone;
    }

    public String getMoveOutOrgan() {
        return moveOutOrgan;
    }

    public void setMoveOutOrgan(String moveOutOrgan) {
        this.moveOutOrgan = moveOutOrgan;
    }
}

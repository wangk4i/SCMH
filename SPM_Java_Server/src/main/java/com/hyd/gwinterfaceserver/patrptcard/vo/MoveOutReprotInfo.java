package com.hyd.gwinterfaceserver.patrptcard.vo;

import com.hyd.gwinterfaceserver.util.Pageinfos;
import lombok.Data;

@Data
public class MoveOutReprotInfo extends Pageinfos {

    public String cd;
    public String zoneCd;
    public String organCd;
    public String iDCode;
    public String signedDate;
    public String outZoneCd;
    public String outOrganCd;
    public String fIELDPK;
    public String moveOutCause;


}
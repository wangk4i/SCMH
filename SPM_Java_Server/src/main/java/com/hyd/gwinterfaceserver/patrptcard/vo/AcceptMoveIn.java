package com.hyd.gwinterfaceserver.patrptcard.vo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

@Data
public class AcceptMoveIn {

    @SerializedName("MoveInAndOutId")
    public String moveInAndOutId;

    @SerializedName("PersonInfo")
    public PatInfo patInfo;
}

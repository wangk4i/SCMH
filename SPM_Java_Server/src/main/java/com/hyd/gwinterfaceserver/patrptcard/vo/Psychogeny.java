package com.hyd.gwinterfaceserver.patrptcard.vo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Psychogeny {
    @SerializedName("PsychogenyPastDangerousCode")
    public String psychogenyPastDangerousCode;
}

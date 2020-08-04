package com.hyd.system;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spmapp")
public class SPMConfig {
    private static String localPrev;
    private static String localPrevLike;
    private static String gwinterface;
    private static String baseinformation;
    private static String followup;

    public static String getLocalPrev() {
        return localPrev;
    }
    @Value("${spmapp.localprev}")
    public void setLocalPrev(String localPrev) {
        SPMConfig.localPrev = localPrev;
    }

    public static String getLocalPrevLike() {
        return localPrevLike;
    }
    @Value("${spmapp.localprevlike}")
    public void setLocalPrevLike(String localPrevLike) {
        SPMConfig.localPrevLike = localPrevLike;
    }

    public static String getGwinterface() {
        return gwinterface;
    }
    @Value("${spmapp.gwinterface}")
    public void setGwinterface(String gwinterface) {
        SPMConfig.gwinterface = gwinterface;
    }

    public static String getBaseinformation() {
        return baseinformation;
    }
    @Value("${spmapp.baseinformation}")
    public void setBaseinformation(String baseinformation) {
        SPMConfig.baseinformation = baseinformation;
    }

    public static String getFollowup() {
        return followup;
    }
    @Value("${spmapp.followup}")
    public void setFollowup(String followup) {
        SPMConfig.followup = followup;
    }
}

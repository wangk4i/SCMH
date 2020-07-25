package com.hyd.subordtest.utils;

import java.lang.management.ManagementFactory;

/**
 * Created by xieshuai on 2020/6/11 14:49
 */
public class SystemUtils {

    /**
     * 记录当前服务启动时的window进程pid
     * @return
     */
    public static String getSystemPig(){
        String system =  ManagementFactory.getRuntimeMXBean().getName();
        return system.split("@")[0];
    }
}

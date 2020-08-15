package com.hyd.endurance.utils;

import java.lang.management.ManagementFactory;

/**
 * Created by xieshuai on 2020/7/1 下午 1:47
 */
public class SystemUtils {


    /**
     * 获取系统运行时的Pid
     * @return
     */
    public static String getSystemPid(){
        //9952@DESKTOP-P3AHQUD 虚拟机记录PID+@+正在运行虚拟机名称
        String pidStr = ManagementFactory.getRuntimeMXBean().getName();
        return pidStr.split("@")[0];
    }
}

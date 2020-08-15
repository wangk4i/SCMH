package com.hyd.endurance.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * 文件夹路径配置
 */
@Component
public class FolderPathConfig {
    // 已接收待处理文件夹
    public static String received;
    // 处理完成文件夹
    public static String done;
    // 操作失败文件夹
    public static String failed;
    // 已发送等待处理文件夹
    public static String waiting;

    public static String getReceived() {
        return received;
    }

    @Value("${mqPath.received}")
    public void setReceivedFolder(String receivedFolder) {
        FolderPathConfig.received = receivedFolder;
    }

    public static String getDone() {
        return done;
    }

    @Value("${mqPath.done}")
    public void setDoneFolder(String doneFolder) {
        FolderPathConfig.done = doneFolder;
    }

    public static String getFailed() {
        return failed;
    }

    @Value("${mqPath.failed}")
    public void setFailedFolder(String failedFolder) {
        FolderPathConfig.failed = failedFolder;
    }

    public static String getWaiting() {
        return waiting;
    }

    @Value("${mqPath.waiting}")
    public void setWaitingFolder(String waitingFolder) {
        FolderPathConfig.waiting = waitingFolder;
    }
}

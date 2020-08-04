package com.hyd.resultdeal.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
/**
 * 文件夹路径配置
 */
@Component
public class FolderPathConfig {
    // 已接收待处理文件夹
    public static String receivedFolder;
    // 处理完成文件夹
    public static String doneFolder;
    // 操作失败文件夹
    public static String failedFolder;
    // 已发送等待处理文件夹
    public static String waitingFolder;

    public static String getReceivedFolder() {
        return receivedFolder;
    }
    @Value("${mqPath.received}")
    public void setReceivedFolder(String receivedFolder) {
        FolderPathConfig.receivedFolder = receivedFolder;
    }

    public static String getDoneFolder() {
        return doneFolder;
    }
    @Value("${mqPath.done}")
    public void setDoneFolder(String doneFolder) {
        FolderPathConfig.doneFolder = doneFolder;
    }

    public static String getFailedFolder() {
        return failedFolder;
    }
    @Value("${mqPath.failed}")
    public void setFailedFolder(String failedFolder) {
        FolderPathConfig.failedFolder = failedFolder;
    }

    public static String getWaitingFolder() {
        return waitingFolder;
    }
    @Value("${mqPath.waiting}")
    public void setWaitingFolder(String waitingFolder) {
        FolderPathConfig.waitingFolder = waitingFolder;
    }
}

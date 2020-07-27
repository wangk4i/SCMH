package com.hyd.resultdeal.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
/**
 * 文件夹路径配置
 */
public class FolderPathConfig {
    // 已接收待处理文件夹
    public static String receivedFolder;
    // 处理完成文件夹
    public static String doneFolder;
    // 操作失败文件夹
    public static String failedFolder;
    // 相应消息txt接收文件夹
    public static String returnTxtFolder;

    // xml发送文件夹
    public static String sendingXmlFolder;
    // 消息持久化文件夹
    public static String msgPersistFolder;

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

    public static String getReturnTxtFolder() {
        return returnTxtFolder;
    }
    @Value("${mqPath.returnTxt}")
    public void setReturnTxtFolder(String returnTxtFolder) {
        FolderPathConfig.returnTxtFolder = returnTxtFolder;
    }

    public static String getSendingXmlFolder() {
        return sendingXmlFolder;
    }
    @Value("${mqPath.buildxml.sendingxml}")
    public void setSendingXmlFolder(String sendingXmlFolder) {
        FolderPathConfig.sendingXmlFolder = sendingXmlFolder;
    }

    public static String getMsgPersistFolder() {
        return msgPersistFolder;
    }
    @Value("${mqPath.msgPersist}")
    public void setMsgPersistFolder(String msgPersistFolder) {
        FolderPathConfig.msgPersistFolder = msgPersistFolder;
    }
}

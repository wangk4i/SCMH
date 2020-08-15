package com.hyd.centermq.file;

import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.File;

@Component
public class FileMonitorRegisterConfig {

    @Resource
    private FileListenerMonitorUtils fileListenerMonitorUtils ;

    @Value("${sysconfig.rabbit.backupfolder}")
    private String filePath;

    @Value("${sysconfig.rabbit.filesuffix}")
    private String fileSuffix;

    /**
     * SpringBoot启动时监听指定文件
     */
    @PostConstruct
    private void register() {
        File directory = new File(filePath);
        if (!directory.exists()) {
            throw new NullPointerException("目录不存在启动文件观察者失败:" + directory);
        }
        Long s = 10L;
        try {
            // 为指定文件夹下面的指定文件注册文件观察者
            FileAlterationMonitor monitor = fileListenerMonitorUtils.getMonitor(directory, s, "mq_", fileSuffix);
            // 启动观察者
            monitor.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
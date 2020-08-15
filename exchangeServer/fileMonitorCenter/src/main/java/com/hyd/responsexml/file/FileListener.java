package com.hyd.responsexml.file;

import com.hyd.responsexml.listener.FileMonitor;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by xieshuai on 2020/8/8 上午 11:07
 */
public class FileListener extends FileAlterationListenerAdaptor {

    Logger log = LoggerFactory.getLogger(this.getClass());

    // 声明业务服务
    private FileMonitor listenerService;

    // 采用构造函数注入服务
    public FileListener(FileMonitor listenerService) {
        this.listenerService = listenerService;
    }

    // 文件创建执行
    @Override
    public void onFileCreate(File file) {
        //log.info("文件创建:"+file.getPath());
        // 触发业务
        listenerService.captureFileResult(file);
    }

    // 文件创建修改
    @Override
    public void onFileChange(File file) {
    }

    // 文件创建删除
    @Override
    public void onFileDelete(File file) {

    }

    // 目录创建
    @Override
    public void onDirectoryCreate(File directory) {
    }

    // 目录修改
    @Override
    public void onDirectoryChange(File directory) {
    }

    // 目录删除
    @Override
    public void onDirectoryDelete(File directory) {
    }


    // 轮询开始
    @Override
    public void onStart(FileAlterationObserver observer) {
    }

    // 轮询结束
    @Override
    public void onStop(FileAlterationObserver observer) {
    }
}

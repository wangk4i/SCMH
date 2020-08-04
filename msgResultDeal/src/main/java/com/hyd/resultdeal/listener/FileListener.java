package com.hyd.resultdeal.listener;


import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;

public class FileListener extends FileAlterationListenerAdaptor {
    // 文件创建执行
    @Override
    public void onFileCreate(File file) {
        super.onFileCreate(file);
    }

    // 文件创建修改
    @Override
    public void onFileChange(File file) {
        super.onFileChange(file);
        // 触发业务
        //listenerService.doSomething();
    }

    // 文件创建删除
    @Override
    public void onFileDelete(File file) {
        super.onFileDelete(file);
    }

    // 目录创建
    @Override
    public void onDirectoryCreate(File directory) {
        super.onDirectoryCreate(directory);
    }

    // 目录修改
    @Override
    public void onDirectoryChange(File directory) {
        super.onDirectoryChange(directory);
    }

    // 目录删除
    @Override
    public void onDirectoryDelete(File directory) {
        super.onDirectoryDelete(directory);
    }


    // 轮询开始
    @Override
    public void onStart(FileAlterationObserver observer) {
        super.onStart(observer);
    }

    // 轮询结束
    @Override
    public void onStop(FileAlterationObserver observer) {
        super.onStop(observer);
    }

}

package com.hyd.resultdeal.listener;


import com.hyd.resultdeal.service.FileDealService;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;

public class FileListener extends FileAlterationListenerAdaptor {

    // 声明业务服务
    private FileDealService listenerService;

    // 采用构造函数注入服务
    public FileListener(FileDealService listenerService){
        this.listenerService = listenerService;
    }

    // 文件创建执行
    @Override
    public void onFileCreate(File file) {
        super.onFileCreate(file);
        System.out.println("文件创建事件");
        System.out.println("文件名称："+file.getName());
        listenerService.fileResultDeal(file.getName());
    }

    // 文件创建修改
    @Override
    public void onFileChange(File file) {
        super.onFileChange(file);
        System.out.println("文件改变事件");
        // 触发业务
        //listenerService.doSomething();
    }

    // 文件创建删除
    @Override
    public void onFileDelete(File file) {
        super.onFileDelete(file);
        System.out.println("文件删除事件");
        System.out.println("文件名称："+file.getName());
    }

    // 目录创建
    @Override
    public void onDirectoryCreate(File directory) {
        super.onDirectoryCreate(directory);
        System.out.println("目录创建事件");
        System.out.println("目录名称："+directory.getName());
    }

    // 目录修改
    @Override
    public void onDirectoryChange(File directory) {
        super.onDirectoryChange(directory);
        System.out.println("目录改变事件");
    }

    // 目录删除
    @Override
    public void onDirectoryDelete(File directory) {
        super.onDirectoryDelete(directory);
        System.out.println("目录删除事件");
        System.out.println("目录名称："+directory.getName());
    }


    // 轮询开始
    @Override
    public void onStart(FileAlterationObserver observer) {
        super.onStart(observer);
        System.out.println("文件系统观察者开始检查事件");
    }

    // 轮询结束
    @Override
    public void onStop(FileAlterationObserver observer) {
        super.onStop(observer);
        System.out.println("文件系统观察者完成检查事件观测器");
    }

}

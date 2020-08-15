package com.hyd.centermq.file;

import com.hyd.centermq.service.MqService;
import com.hyd.centermq.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.File;
import java.util.List;

/**
 * xieshuai by 2020-5-25
 */
@Slf4j
@Component
public class FileListenerExample extends FileAlterationListenerAdaptor {


    @Autowired
    private MqService mqService;

    @Value("${sysconfig.rabbit.backupfolder}")
    private String filePath;

    @Override
    public void onStart(FileAlterationObserver observer) {
        //开始文件监听
    }

    @Override
    public void onDirectoryCreate(File directory) {
        log.info("创建文件夹"+directory);
    }

    @Override
    public void onDirectoryChange(File directory) {
        log.info("修改文件夹"+directory);
    }

    @Override
    public void onDirectoryDelete(File directory) {
        log.info("删除文件夹"+directory);
    }


    /**
     * 每创建一个文件，获取当前文件的父文件夹，然后解析该文件夹下的第一个文件， 尝试发送到RabbitMQ中，发送失败时， 系统休眠30/s
     * 然后递归， 直到发送成功； 解析完成后继续遍历，直到当前文件夹下无文件， 销毁线程
     * @param file
     */
    @Override
    public void onFileCreate(File file){
        log.info("文件创建"+file);
        getAllFileByFolder();

    }

    public void getAllFileByFolder() {
        Thread thread = new Thread();
        thread.setDaemon(true);
        thread.start();
        //获取当前文件夹内所有文件
        List<File> fileList = FileUtils.traverseFolder(filePath);
        if(fileList==null){
            log.info("监听文件夹无文件, 退出当前线程任务");
            return;
        }
        for(File filed: fileList){
            //获取当前文件, 发送到RabbitMQ
            fileToRabbit(filed);
        }
        //再次查询文件数量
        List<File> filecount = FileUtils.traverseFolder(filePath);
        if(null!=filecount&&filecount.size()>0){
            try {
                Thread.sleep(30_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("监听文件夹有剩余文件，系统休眠30s后继续开启消费线程");
            getAllFileByFolder();
        }
    }


    public void fileToRabbit(File file1){

        //获得当前队列名称
        String queueName = file1.getName().substring(file1.getName().indexOf("_")+1, file1.getName().lastIndexOf("_"));
        //设置交换机名称和队列名称
        mqService. readFileByRabbitMQ(file1, queueName);
    }


    @Override
    public void onFileChange(File file) {
        log.info("修改文件"+file);
    }

    @Override
    public void onFileDelete(File file) {
        log.info("删除文件"+file);
    }

    @Override
    public void onStop(FileAlterationObserver observer) {
        //文件监听结束
    }





}
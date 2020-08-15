package com.hyd.responsexml.listener;


import com.hyd.responsexml.util.DateUtils;
import com.hyd.responsexml.util.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by xieshuai on 2020/6/17 上午 9:42
 */
@Component
public class ClearListener {

//
//    @Value("${config.backup-folder}")
//    private String listenerfile;
//
//    /**
//     * 每天晚上12点30分执行一次，删除大于120天的文件
//     */
//    @Scheduled(cron = "0 30 0 * * ?")
//    public void clearExpiredFile(){
//        //获取当前日期字符串
//        String currentTime = DateUtils.getDateStr("yyyyMMdd");
//        //获得当前文件夹所有文件
//        List<String> files = FileUtils.getFiles(listenerfile);
//        //遍历文件
//        for (String filePath : files) {
//            //获取当前文件创建时间
//            String createTime = FileUtils.getFileCreateTime(filePath);
//            Integer days = DateUtils.getBetweenDays(currentTime, createTime);
//            if (days >= 120) {
//                //删除当前文件
//                FileUtils.delectFile(filePath);
//            }
//        }
//    }





}

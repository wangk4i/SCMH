package com.hyd.batchprocess.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
@Component
public class LogUtil {
    private static String logPath ;

    @Value("${config.BuildLogPath}")
    public void setLogPath(String logPath) {
        LogUtil.logPath = logPath;
    }

    /**
     * 创建日志文件
     * @param logStr 日志内容
     * @return*/


    public static void info(String logStr){
         String content = infoLogContent(logStr);
         buildLogFile(content);
    }


    public static boolean buildLogFile(String content){

        String date = new SimpleDateFormat("yyyyMMddHH").format(new Date());
        // todo 日志文件夹修改

        // 文件夹路径
//        File rootfolder = new File(rootPath);
//        if(!rootfolder.exists()){ // 如果文件夹不存在
//            rootfolder.mkdir(); // 创建文件夹
//        }
//        File filefolder = new File(folderPath);
//        if(!filefolder.exists()){ // 如果文件夹不存在
//            filefolder.mkdir(); // 创建文件夹
//        }
        // 日志路径  :   根/log/ /接口方法名_yyyyMMddHH.log
        String filePath =  "batchm_"+date +".log";
        File fileName = new File(logPath+"/"+filePath);
        RandomAccessFile mm = null;
        boolean flag = false;

        FileOutputStream o = null;
        try {
            o = new FileOutputStream(fileName,true);
            o.write(content.getBytes("utf-8"));
            o.close();
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (mm != null) {
                try {
                    mm.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    private static String infoLogContent(String str){
        StringBuffer sb = new StringBuffer();
        String d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        sb.append(d).append("\t: ").append(str).append("\n");
        return sb.toString();

    }
}

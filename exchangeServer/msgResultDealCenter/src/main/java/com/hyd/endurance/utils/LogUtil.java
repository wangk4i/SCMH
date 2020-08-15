package com.hyd.endurance.utils;

import com.hyd.endurance.config.FolderPathConfig;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/11 14:42
 * 日志按月份记
 */
public class LogUtil {

    public static void info(String logStr){
        String content = infoLogContent(logStr);
        buildLogFile(content);
    }

    public static void warn(String logStr, Exception e){
        String content = warnLogContent(logStr, e);
        buildLogFile(content);
    }

    private static void buildLogFile(String content){
        String d = new SimpleDateFormat("yyyy-MM").format(new Date());
        String logPath = FolderPathConfig.received + "\\log";
        File logFolder = new File(logPath);
        if (!logFolder.exists()){
            logFolder.mkdir();
        }
        File file = new File(logPath + "\\"+d+ ".log");
        RandomAccessFile mm = null;
        FileOutputStream o = null;
        try {
            o = new FileOutputStream(file,true);
            o.write(content.getBytes("utf-8"));
            o.close();
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
    }

    private static String infoLogContent(String str){
        StringBuffer sb = new StringBuffer();
        String d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        sb.append(d).append("\t: ").append(str).append("\n");
        return sb.toString();
    }

    private static String warnLogContent(String str, Exception e){
        StringBuffer sb = new StringBuffer();
        String d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        sb.append(d).append("\t: ").append(str).append("\t").append(e.getMessage()).append("\n");
        return sb.toString();
    }
}

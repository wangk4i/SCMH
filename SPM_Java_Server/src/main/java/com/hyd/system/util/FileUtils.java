package com.hyd.system.util;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by xieshuai on 2020/5/14 18:37
 * 文件操作工具类
 */
public class FileUtils {

    /**
     * 文件Copy
     * @param fileUrl
     * @param fuleUrlTo
     * @throws IOException
     */
    public static void copyFile(String fileUrl, String fuleUrlTo) throws IOException {
        if(StringUtils.isNotEmpty(fileUrl)&&StringUtils.isNotEmpty(fuleUrlTo)){
            File fileA = new File(fileUrl);
            File fileB = new File(fuleUrlTo);
            org.apache.commons.io.FileUtils.copyFile(fileA, fileB);
        }
    }

    /**
     * 获取指定文件夹下的所有文件资源
     * @param fileUrl 文件夹路径
     * @param fileSuffix 获取文件后缀
     * @return
     */
    public static  Collection<File> getFileByUrl(String fileUrl, String fileSuffix){
        if(StringUtils.isEmpty(fileUrl)&&StringUtils.isEmpty(fileSuffix)){
            return null;
        }
        File directory = new File(fileUrl);
        return org.apache.commons.io.FileUtils.listFiles(directory, new String[]{fileSuffix}, false);
    }
}

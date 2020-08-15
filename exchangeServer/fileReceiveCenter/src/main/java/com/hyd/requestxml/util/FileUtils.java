package com.hyd.requestxml.util;


import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by xieshuai on 2020/5/29 14:08
 */
@Slf4j
public class FileUtils {

    /**
     * 定时删除指定文件夹的过期文件
     * @return
     */
    public static String timingRemoveFile(){
        return null;
    }


    /**
     * 保存文件到指定文件夹
     */
    public static void writeFileToPath(File file, String filePath){

    }


    /**
     * 读取本地Json资源文件
     * @param jsonPath
     * @return
     */
    public static String readJsonFile(String jsonPath) {
        File jsonFile = null;
        try {
            jsonFile = new File(jsonPath);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            return sb.toString();
        } catch (Exception e) {
            log.info(jsonFile.getPath() + "文件解析异常");
            e.printStackTrace();
            return null;
        }
    }
    public static String readJsonFile(File jsonFile){
        try {
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1){
                sb.append((char)ch);
            }
            fileReader.close();
            reader.close();
            return sb.toString();
        }catch (Exception e){
            log.info(jsonFile.getPath()+ "文件解析异常");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据文件路径删除文件
     * @param filePath
     * @return
     */
    public static boolean delectFile(String filePath){
        File file = new File(filePath);
        return file.delete();
    }
    public static boolean delectFile(File file){
        return file.delete();
    }


    /**
     * 读取指定文件夹下所有文件的全路径
     * @param path 文件夹路径
     * @return 文件资源路径集合
     */
    public static List<String> getFiles(String path) {
        ArrayList files = new ArrayList();
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                files.add(tempList[i].toString());
            }
        }
        return files;
    }

    /**
     * 传入文件夹路径，返回当前文件夹下所有文件
     * @param path
     * @return
     */
    public static List<File> traverseFolder(String path) {
        List<File> fileList = new ArrayList<>();
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null == files || files.length == 0) {
                log.info("当前文件夹为空");
                return null;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        traverseFolder(file2.getAbsolutePath());
                    } else {
                        fileList.add(file2);
                    }
                }
            }
        } else {
           log.info("文件不存在");
        }
        return fileList;
    }

    /**
     * 传入path,获取文件创建时间
     */
    public static String getFileCreateTime(String filePath){

        String strTime = null;
        try {
            Process p = Runtime.getRuntime().exec("cmd /C dir "
                    + filePath
                    + "/tc" );
            InputStream is = p.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;
            while((line = br.readLine()) != null){
                if(line.endsWith(".txt")){
                    strTime = line.substring(0,17);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strTime;
    }

    /**
     * 获取文件最后修改时间
     */
    public static String getFileModifiedTime(String filePath){
        File f = new File(filePath);
        Calendar cal = Calendar.getInstance();
        long time = f.lastModified();
        cal.setTimeInMillis(time);
        return cal.getTime().toLocaleString();
    }





}

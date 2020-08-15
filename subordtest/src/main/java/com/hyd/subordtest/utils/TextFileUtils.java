package com.hyd.subordtest.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextFileUtils {

    private final static Logger log = LogManager.getLogger(TextFileUtils.class);



    /**
     * 读txt文件，内容转为String
     * @param filePathString
     * @return
     */
    public static String readFileContent(String filePathString) {
        //File file = new File(fileName);
        BufferedReader reader = null;
        StringBuffer sbf = new StringBuffer();
        try {
            InputStreamReader fReader = new InputStreamReader(new FileInputStream(filePathString),"UTF-8");
            reader = new BufferedReader(fReader);
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                sbf.append(tempStr);
            }
            reader.close();
            return sbf.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return sbf.toString();
    }

    public static String readXmlFile(File file){

        SAXReader saxReader = new SAXReader();
        Document document = null;
        try {
            document = saxReader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document.asXML();
    }

    /**
     * 移动文件到新路径下
     * @param pathName 源文件夹
     * @param fileName 文件名
     * @param ansPath 新文件夹
     */
    public static void moveToOtherFolders(String fileName, String pathName, String ansPath){
        String startPath = pathName + File.separator + fileName;
        String endPath = ansPath + File.separator + File.separator;
        try {
            File startFile = new File(startPath);
            File tmpFile = new File(endPath);//获取文件夹路径
            if(!tmpFile.exists()){//判断文件夹是否创建，没有创建则创建新文件夹
                tmpFile.mkdirs();
            }
            System.out.println(endPath + startFile.getName());
            if (startFile.renameTo(new File(endPath + startFile.getName()))) {
//                System.out.println("File is moved successful!");
                log.info("File is moved successful! FileName:<{}> EndPath{}",fileName,endPath);
            } else {
//                System.out.println("File is failed to move!");
                log.info("File is failed to move! FileName:<{}> StartPath:{}",fileName,startPath);
            }
        } catch (Exception e) {
            log.info("File move unexpected！FileName:<{}> StartPath:{}",fileName,startPath);
        }
    }

    /**
     * 正则匹配方法
     */
    public static String matchValue(String stream, String regEx){
        String resultInfoStr = null;
        try {
            Pattern pattern = Pattern.compile(regEx);
            Matcher matcher = pattern.matcher(stream);
            if(!matcher.find()){
                log.info("未匹配到，返回值格式有误.原始值:{}，正则:{}", stream,regEx);
            }
            resultInfoStr = matcher.group(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultInfoStr;
    }

    /**
     * 更改目标文件对应内容
     * @param path 文件全路径名
     * @param oldString 需修改的内容
     * @param newString 修改后内容
     */
    public static void alterStringToCreateNewFile(String path, String oldString, String newString){
        File file = new File(path);
        try {
            long start = System.currentTimeMillis(); //开始时间
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(file))); //创建对目标文件读取流
            File newFile = new File("D:\\newFile"); //创建临时文件
            if (!newFile.exists()){
                newFile.createNewFile(); //不存在则创建
            }
            //创建对临时文件输出流，并追加
            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(newFile,true)));
            String string = null; //存储对目标文件读取的内容
            int sum = 0; //替换次数
            while ((string = br.readLine()) != null){
                //判断读取的内容是否包含原字符串
                if (string.contains(oldString)){
                    //替换读取内容中的原字符串为新字符串
                    string = new String(
                            string.replace(oldString,newString));
                    sum++;
                }
                bw.write(string);
                bw.newLine(); //添加换行
            }
            br.close(); //关闭流，对文件进行删除等操作需先关闭文件流操作
            bw.close();
            String filePath = file.getPath();
            file.delete(); //删除源文件
            newFile.renameTo(new File(filePath)); //将新文件更名为源文件
            long time = System.currentTimeMillis() - start; //整个操作所用时间;
            System.out.println(sum+"个"+oldString+"替换成"+newString+"耗费时间:"+time);
        } catch(Exception e){
            e.printStackTrace();
        }
    }



}

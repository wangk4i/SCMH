package com.hyd.responsexml.listener;

import com.hyd.responsexml.util.FileUtils;
import com.hyd.responsexml.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import java.io.File;

/**
 * Created by xieshuai on 2020/6/19 上午 10:31
 */

@Slf4j
@Component
public class FileMonitor {


    @Autowired
    private RedisUtils redisUtils;


    @Value("${config.listener-xml-folder}")
    private String xmlFolder;

    @Value("${config.unconfirm-folder}")
    private String unconfirmFolder;

    @Value("${config.redis_zset_key}")
    private String FILE_ZSET_KEY;

    /**
     * 每隔30/s扫描指定文件夹, 读取返回值消息并备份到指定文件夹
     */
//   @Scheduled(fixedDelay = 1000*30)
//    public void xmlFileListener(){
//        List<File> files =  FileUtils.traverseFolder(listenerFolder);
//        if(files==null||files.size()==0){
//            //系统休眠五分钟
//            log.info("文件不存在或文件夹为空,系统休眠五分钟");
//            try {
//                Thread.sleep(1000*60*5);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return;
//        }
//        //复制所有文件到备份文件夹
//        for(File file: Objects.requireNonNull(files)){
//            Map<String, String> map  = new HashMap<>();
//            //获取当前文件名
//            String name = file.getName();
//            String xmlStream = XmlUtils.readFileXml(file);
//            map.put("fileName", name);
//            //获取文件流
//            map.put("fileSteam", xmlStream);
//            //保存到Redis中
//            redisUtils.addzSet(FILE_ZSET_KEY, JSONArray.toJSONString(map));
//            //保存到备份文件夹
//            String backName = backupFolder + "\\"+name;
//            File backFile = new File(backName);
//            FileOutputStream out = null;
//
//            try {
//                out = new FileOutputStream(backFile);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//            try {
//                assert out != null;
//                out.write(xmlStream.getBytes());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }finally {
//                try {
//                    assert out != null;
//                    out.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        //删除所有已存储到Redis的文件
//        files.forEach(FileUtils::delectFile);
//    }


    /**
     * 文件监听器 监听到响应xml文件生成， 移入到待确认文件夹， 并将文件名保存到Redis中
     * @param file
     */
    @Async
    public void captureFileResult(File file){
        if(null==file){
            //log.error("文件不存在,解析xml到Redis失败");
            return;
        }
        //assert file != null;
        String fileName = file.getName();
        String filePath = file.getPath();
        String fileid = fileName.substring(0, fileName.lastIndexOf("."));
        //移入待确认文件夹
        FileUtils.move(filePath, unconfirmFolder+ fileName);
        FileUtils.move(xmlFolder+fileid+".xml", unconfirmFolder+ fileid+ ".xml");
        //保存到Redis中
        redisUtils.addzSet(FILE_ZSET_KEY, fileid);
    }


}

package com.hyd.requestxml.service;

import com.hyd.requestxml.domain.XmlInfo;
import com.hyd.requestxml.util.XmlUtils;

import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by xieshuai on 2020/6/18 下午 5:28
 */
@Slf4j
@Service
public class RestBaseService {

    /**
     * 国网指定文件夹
     */
    @Value("${sysconfig.State-Grid-monitor-folder}")
    private String monitorFolder;

    /**
     * 备份指定文件夹
     */
    @Value("${sysconfig.System-backup-folder}")
    private String backupFolder;


    /**
     * 从cdc服务收到需要生成的mapdata
     * @param info
     * @return
     */
    @Async
    public void createXml(XmlInfo info){
        if(null==info){
            return;
        }
        //1.获取文件名和xml字符串
//        Document document = null;
//        try {
//            document = DocumentHelper.parseText(info.getXmlStream());
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        }
        String monitor_path = monitorFolder + info.getXmlId()+".xml";
        String backup_path = backupFolder + info.getXmlId()+".xml";

        //2.写入备份文件夹和国网文件夹
//        XmlUtils.xmlStringToFile(document, monitor_path);
//        XmlUtils.xmlStringToFile(document, backup_path);

        XmlUtils.xmlStream2File(info.getXmlStream(), monitor_path);
        XmlUtils.xmlStream2File(info.getXmlStream(), backup_path);


        log.info("收到xml流,已保存至国网文件夹{}", monitor_path);
        log.info("收到xml流,医保存至备份文件夹{}", backup_path);
    }
}

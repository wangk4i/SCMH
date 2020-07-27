package com.hyd.resultdeal.distributeService;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hyd.resultdeal.Config.FolderPathConfig;
import com.hyd.resultdeal.domain.InterchangeDO;
import com.hyd.resultdeal.domain.MessageInfoDO;
import com.hyd.resultdeal.domain.ReturnMsgDO;
import com.hyd.resultdeal.mapper.BasicInfoMapper;
import com.hyd.resultdeal.utils.TextFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;

@Repository
public class ExchangeActionImpl implements ExchangeAction {
    private Gson gson = new GsonBuilder().serializeNulls().create();
    @Autowired
    BasicInfoMapper mapper;

    @Override
    public String DocumentAdd(MessageInfoDO info, ReturnMsgDO resultMsg) {
        System.out.println("msgPersistFolder:"+ FolderPathConfig.receivedFolder);
        System.out.println("msgPersistFolder:"+ FolderPathConfig.msgPersistFolder);

        String msgFilePath =  FolderPathConfig.msgPersistFolder + File.separator + resultMsg.getXmlNam()+".json";
        // todo 没找到json时的处理 记日志
        String msgInfoJson = TextFileUtils.readFileContent(msgFilePath);

        InterchangeDO exMsg = gson.fromJson(resultMsg.getMsgBody(), InterchangeDO.class);
        String docXml = resultMsg.getXmlNam()+".xml";
        // 返回值为失败时，xml,txt 转移到failed文件夹，返回交换消息体
        if (!exMsg.getResult()){
            TextFileUtils.moveTotherFolders( FolderPathConfig.sendingXmlFolder, docXml,  FolderPathConfig.failedFolder);
            TextFileUtils.moveTotherFolders( FolderPathConfig.returnTxtFolder, resultMsg.getXmlNam()+".txt",  FolderPathConfig.failedFolder);
            System.out.println(resultMsg.getMsgBody());
            return resultMsg.getMsgBody();
        }

        String regEx = "\"id\":\"([\\s\\S]*?)\"";
        String oldId = TextFileUtils.matchValue(msgInfoJson,regEx);
        // 主键更新为返回成功 交换消息中的返回值
        TextFileUtils.alterStringToCreateNewFile(msgFilePath, oldId, exMsg.getId());



        // 返回值为成功，转移到 done/日期/ 文件夹下
        TextFileUtils.moveTotherFolders( FolderPathConfig.sendingXmlFolder, docXml,  FolderPathConfig.doneFolder);
        TextFileUtils.moveTotherFolders( FolderPathConfig.returnTxtFolder, resultMsg.getXmlNam()+".txt",  FolderPathConfig.doneFolder);




        return "success";

    }

    @Override
    public void DocumentUpdate() {

    }

    @Override
    public void DocumentDelete() {

    }

    @Override
    public void DocumentUndelete() {

    }

    @Override
    public void DocumentDeclaredeath() {

    }


    @Override
    public void ReportAdd() {

    }

    @Override
    public void ReportUpdate() {

    }

    @Override
    public void ReportDelete() {

    }


    @Override
    public String DischargeAdd(MessageInfoDO info, ReturnMsgDO resultMsg) {
        System.out.println("msgPersistFolder:"+ FolderPathConfig.receivedFolder);
        System.out.println("msgPersistFolder:"+ FolderPathConfig.msgPersistFolder);

        String msgFilePath =  FolderPathConfig.msgPersistFolder + File.separator + resultMsg.getXmlNam()+".json";
        // todo 没找到json时的处理 记日志
        String msgInfoJson = TextFileUtils.readFileContent(msgFilePath);

        InterchangeDO exMsg = gson.fromJson(resultMsg.getMsgBody(), InterchangeDO.class);
        String docXml = resultMsg.getXmlNam()+".xml";
        // 返回值为失败时，xml,txt 转移到failed文件夹，返回交换消息体
        if (!exMsg.getResult()){
            // 数据库记录同步信息
            mapper.syncDischargeErrInfo(info.getId(), resultMsg.getReceivedTime(), exMsg.getDesc());
            TextFileUtils.moveTotherFolders( FolderPathConfig.sendingXmlFolder, docXml,  FolderPathConfig.failedFolder);
            TextFileUtils.moveTotherFolders( FolderPathConfig.returnTxtFolder, resultMsg.getXmlNam()+".txt",  FolderPathConfig.failedFolder);
            System.out.println("错误信息: "+resultMsg.getMsgBody());
            return resultMsg.getMsgBody();
        }

        mapper.syncDischargeSuccInfo(info.getId(), exMsg.getId(), resultMsg.getReceivedTime());
        // 更新json的主键
        String jsonRegEx = "\"id\":\"([\\s\\S]*?)\"";
        String oldId = TextFileUtils.matchValue(msgInfoJson,jsonRegEx);
        TextFileUtils.alterStringToCreateNewFile(msgFilePath, oldId, exMsg.getId());

        // 更新xml主键
        String xmlFilePath = FolderPathConfig.getSendingXmlFolder()+ File.separator + resultMsg.getXmlNam() + ".xml";
        String xmlStr = TextFileUtils.readFileContent(xmlFilePath);
        String xmlRegEx = "<ID>([\\s\\S]*?)</ID>";
        String oldPk = TextFileUtils.matchValue(xmlStr, xmlRegEx);
        TextFileUtils.alterStringToCreateNewFile(xmlFilePath, oldPk, exMsg.getId());

        // 返回值为成功，转移到 done/日期/ 文件夹下
        TextFileUtils.moveTotherFolders( FolderPathConfig.sendingXmlFolder, docXml,  FolderPathConfig.doneFolder);
        TextFileUtils.moveTotherFolders( FolderPathConfig.returnTxtFolder, resultMsg.getXmlNam()+".txt",  FolderPathConfig.doneFolder);

        return "success";
    }

    @Override
    public void DischargeUpdate(MessageInfoDO info, ReturnMsgDO resultMsg) {

    }

    @Override
    public void DischargeDelete(MessageInfoDO info, ReturnMsgDO resultMsg) {

    }


    @Override
    public void FollowupAdd() {

    }

    @Override
    public void FollowupUpdate() {

    }

    @Override
    public void FollowupDelete() {

    }


    @Override
    public void EmergencyAdd() {

    }

    @Override
    public void EmergencyUpdate() {

    }

    @Override
    public void EmergencyDelete() {

    }
}

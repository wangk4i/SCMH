package com.hyd.resultdeal.distributeService.impl;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hyd.resultdeal.Config.FolderPathConfig;
import com.hyd.resultdeal.distributeService.ExchangeAction;
import com.hyd.resultdeal.domain.InterchangeDO;
import com.hyd.resultdeal.domain.MessageInfoDO;
import com.hyd.resultdeal.domain.ReturnMsgDO;
import com.hyd.resultdeal.mapper.BasicInfoMapper;
import com.hyd.resultdeal.utils.TextFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class ExchangeActionImpl implements ExchangeAction {
    private Gson gson = new GsonBuilder().serializeNulls().create();

    @Autowired(required = false)
    private BasicInfoMapper mapper;
    @Override
    public void DocumentAdd(MessageInfoDO info, ReturnMsgDO resultMsg) {
        InterchangeDO exMsg = gson.fromJson(resultMsg.getMsgBody(), InterchangeDO.class);
        String docXml = resultMsg.getXmlNam();
        // 返回值为失败时，xml,txt 转移到failed文件夹，返回交换消息体
        if (!exMsg.getResult()){
            // 数据库记录同步信息
            mapper.syncDocErrInfo(info.getId(), resultMsg.getReceivedTime(), exMsg.getDesc());
            this.moveToOtherFolder(docXml, FolderPathConfig.failedFolder);
            System.out.println(resultMsg.getMsgBody());
            return ;
        }
        String fieldPk = exMsg.getId();
        mapper.addDocSuccInfo(info.getId(), fieldPk, resultMsg.getReceivedTime());
        this.addSucHandle(docXml, fieldPk);
    }

    @Override
    public void DocumentUpdate(MessageInfoDO info, ReturnMsgDO resultMsg) {
        InterchangeDO exMsg = gson.fromJson(resultMsg.getMsgBody(), InterchangeDO.class);
        String docXml = resultMsg.getXmlNam();
        // 返回值为失败时，xml,txt 转移到failed文件夹，返回交换消息体
        if (!exMsg.getResult()){
            // 数据库记录同步信息
            mapper.syncDocErrInfo(info.getId(), resultMsg.getReceivedTime(), exMsg.getDesc());
            this.moveToOtherFolder(docXml, FolderPathConfig.failedFolder);
            System.out.println(resultMsg.getMsgBody());
            return ;
        }
        String fieldPk = exMsg.getId();
        mapper.syncDocSuccInfo(info.getId(), resultMsg.getReceivedTime());
        this.syncSucHandle(docXml, fieldPk);
    }

    @Override
    public void DocumentDelete(MessageInfoDO info, ReturnMsgDO resultMsg) {
        InterchangeDO exMsg = gson.fromJson(resultMsg.getMsgBody(), InterchangeDO.class);
        String docXml = resultMsg.getXmlNam();
        // 返回值为失败时，xml,txt 转移到failed文件夹，返回交换消息体
        if (!exMsg.getResult()){
            // 数据库记录同步信息
            mapper.syncDocErrInfo(info.getId(), resultMsg.getReceivedTime(), exMsg.getDesc());
            this.moveToOtherFolder(docXml, FolderPathConfig.failedFolder);
            System.out.println(resultMsg.getMsgBody());
            return ;
        }
        String fieldPk = exMsg.getId();
        mapper.syncDocSuccInfo(info.getId(), resultMsg.getReceivedTime());
        this.syncSucHandle(docXml, fieldPk);
    }

    @Override
    public void DocumentUndelete(MessageInfoDO info, ReturnMsgDO resultMsg) {
        InterchangeDO exMsg = gson.fromJson(resultMsg.getMsgBody(), InterchangeDO.class);
        String docXml = resultMsg.getXmlNam();
        // 返回值为失败时，xml,txt 转移到failed文件夹，返回交换消息体
        if (!exMsg.getResult()){
            // 数据库记录同步信息
            mapper.syncDocErrInfo(info.getId(), resultMsg.getReceivedTime(), exMsg.getDesc());
            this.moveToOtherFolder(docXml, FolderPathConfig.failedFolder);
            System.out.println(resultMsg.getMsgBody());
            return ;
        }
        String fieldPk = exMsg.getId();
        mapper.undeleteDocSuccInfo(info.getId(), resultMsg.getReceivedTime());
        this.syncSucHandle(docXml, fieldPk);
    }

    @Override
    public void DocumentDeclaredeath(MessageInfoDO info, ReturnMsgDO resultMsg) {
        InterchangeDO exMsg = gson.fromJson(resultMsg.getMsgBody(), InterchangeDO.class);
        String docXml = resultMsg.getXmlNam();
        // 返回值为失败时，xml,txt 转移到failed文件夹，返回交换消息体
        // todo 记录失败日志
        if (!exMsg.getResult()){
            // 数据库记录同步信息
            mapper.syncDocErrInfo(info.getId(), resultMsg.getReceivedTime(), exMsg.getDesc());
            this.moveToOtherFolder(docXml, FolderPathConfig.failedFolder);
            System.out.println(resultMsg.getMsgBody());
            return ;
        }
        String fieldPk = exMsg.getId();
        mapper.turnDeathSuccInfo(info.getId(), resultMsg.getReceivedTime());
        this.syncSucHandle(docXml, fieldPk);
    }


    @Override
    public void ReportAdd(MessageInfoDO info, ReturnMsgDO resultMsg) {
        InterchangeDO exMsg = gson.fromJson(resultMsg.getMsgBody(), InterchangeDO.class);
        String docXml = resultMsg.getXmlNam();
        // 返回值为失败时，xml,txt 转移到failed文件夹，返回交换消息体
        // todo 记录失败日志
        if (!exMsg.getResult()){
            // 数据库记录同步信息
            mapper.syncReportErrInfo(info.getId(), resultMsg.getReceivedTime(), exMsg.getDesc());
            this.moveToOtherFolder(docXml, FolderPathConfig.failedFolder);
            System.out.println(resultMsg.getMsgBody());
            return ;
        }
        String fieldPk = exMsg.getId();
        mapper.addReportSuccInfo(info.getId(), fieldPk, resultMsg.getReceivedTime());
        this.addSucHandle(docXml, fieldPk);
    }

    @Override
    public void ReportUpdate(MessageInfoDO info, ReturnMsgDO resultMsg) {
        InterchangeDO exMsg = gson.fromJson(resultMsg.getMsgBody(), InterchangeDO.class);
        String docXml = resultMsg.getXmlNam();
        // 返回值为失败时，xml,txt 转移到failed文件夹，返回交换消息体
        // todo 记录失败日志
        if (!exMsg.getResult()){
            // 数据库记录同步信息
            mapper.syncReportErrInfo(info.getId(), resultMsg.getReceivedTime(), exMsg.getDesc());
            this.moveToOtherFolder(docXml, FolderPathConfig.failedFolder);
            System.out.println(resultMsg.getMsgBody());
            return ;
        }
        String fieldPk = exMsg.getId();
        mapper.syncReportSuccInfo(info.getId(), resultMsg.getReceivedTime());
        this.syncSucHandle(docXml, fieldPk);
    }

    @Override
    public void ReportDelete(MessageInfoDO info, ReturnMsgDO resultMsg) {
        InterchangeDO exMsg = gson.fromJson(resultMsg.getMsgBody(), InterchangeDO.class);
        String docXml = resultMsg.getXmlNam();
        // 返回值为失败时，xml,txt 转移到failed文件夹，返回交换消息体
        // todo 记录失败日志
        if (!exMsg.getResult()){
            // 数据库记录同步信息
            mapper.syncReportErrInfo(info.getId(), resultMsg.getReceivedTime(), exMsg.getDesc());
            this.moveToOtherFolder(docXml, FolderPathConfig.failedFolder);
            System.out.println(resultMsg.getMsgBody());
            return ;
        }
        String fieldPk = exMsg.getId();
        mapper.syncReportSuccInfo(info.getId(), resultMsg.getReceivedTime());
        this.syncSucHandle(docXml, fieldPk);
    }

    @Override
    public void DischargeAdd(MessageInfoDO info, ReturnMsgDO resultMsg){
        InterchangeDO exMsg = gson.fromJson(resultMsg.getMsgBody(), InterchangeDO.class);
        String docXml = resultMsg.getXmlNam();
        // 返回值为失败时，xml,txt 转移到failed文件夹，返回交换消息体
        if (!exMsg.getResult()){
            // 数据库记录同步信息
            mapper.syncDischargeErrInfo(info.getId(), resultMsg.getReceivedTime(), exMsg.getDesc());
            this.moveToOtherFolder(docXml, FolderPathConfig.failedFolder);
            System.out.println(resultMsg.getMsgBody());
            return ;
        }
        String fieldPk = exMsg.getId();
        mapper.addDischargeSuccInfo(info.getId(), fieldPk, resultMsg.getReceivedTime());
        this.addSucHandle(docXml, fieldPk);
    }


    @Override
    public void DischargeUpdate(MessageInfoDO info, ReturnMsgDO resultMsg) {

        InterchangeDO exMsg = gson.fromJson(resultMsg.getMsgBody(), InterchangeDO.class);
        String docXml = resultMsg.getXmlNam();
        // 返回值为失败时，xml,txt 转移到failed文件夹，返回交换消息体
        if (!exMsg.getResult()){
            // 数据库记录同步信息
            mapper.syncDischargeErrInfo(info.getId(), resultMsg.getReceivedTime(), exMsg.getDesc());
            this.moveToOtherFolder(docXml, FolderPathConfig.failedFolder);
            System.out.println(resultMsg.getMsgBody());
            return ;
        }
        String fieldPk = exMsg.getId();
        mapper.syncDischargeSuccInfo(info.getId(), resultMsg.getReceivedTime());
        this.syncSucHandle(docXml, fieldPk);
    }

    @Override
    public void DischargeDelete(MessageInfoDO info, ReturnMsgDO resultMsg) {
        InterchangeDO exMsg = gson.fromJson(resultMsg.getMsgBody(), InterchangeDO.class);
        String docXml = resultMsg.getXmlNam();
        // 返回值为失败时，xml,txt 转移到failed文件夹，返回交换消息体
        if (!exMsg.getResult()){
            // 数据库记录同步信息
            mapper.syncDischargeErrInfo(info.getId(), resultMsg.getReceivedTime(), exMsg.getDesc());
            this.moveToOtherFolder(docXml, FolderPathConfig.failedFolder);
            System.out.println(resultMsg.getMsgBody());
            return ;
        }
        String fieldPk = exMsg.getId();
        mapper.syncDischargeSuccInfo(info.getId(), resultMsg.getReceivedTime());
        this.syncSucHandle(docXml, fieldPk);
    }


    @Override
    public void FollowupAdd(MessageInfoDO info, ReturnMsgDO resultMsg) {
        InterchangeDO exMsg = gson.fromJson(resultMsg.getMsgBody(), InterchangeDO.class);
        String docXml = resultMsg.getXmlNam();
        // 返回值为失败时，xml,txt 转移到failed文件夹，返回交换消息体
        if (!exMsg.getResult()){
            // 数据库记录同步信息
            mapper.syncFollowupErrInfo(info.getId(), resultMsg.getReceivedTime(), exMsg.getDesc());
            this.moveToOtherFolder(docXml, FolderPathConfig.failedFolder);
            System.out.println(resultMsg.getMsgBody());
            return ;
        }
        String fieldPk = exMsg.getId();
        mapper.addFollowupSuccInfo(info.getId(), fieldPk, resultMsg.getReceivedTime());
        this.addSucHandle(docXml, fieldPk);
    }

    @Override
    public void FollowupUpdate(MessageInfoDO info, ReturnMsgDO resultMsg) {
        InterchangeDO exMsg = gson.fromJson(resultMsg.getMsgBody(), InterchangeDO.class);
        String docXml = resultMsg.getXmlNam();
        // 返回值为失败时，xml,txt 转移到failed文件夹，返回交换消息体
        if (!exMsg.getResult()){
            // 数据库记录同步信息
            mapper.syncFollowupErrInfo(info.getId(), resultMsg.getReceivedTime(), exMsg.getDesc());
            this.moveToOtherFolder(docXml, FolderPathConfig.failedFolder);
            System.out.println(resultMsg.getMsgBody());
            return ;
        }
        String fieldPk = exMsg.getId();
        mapper.syncFollowupSuccInfo(info.getId(), resultMsg.getReceivedTime());
        this.syncSucHandle(docXml, fieldPk);
    }

    @Override
    public void FollowupDelete(MessageInfoDO info, ReturnMsgDO resultMsg) {

        InterchangeDO exMsg = gson.fromJson(resultMsg.getMsgBody(), InterchangeDO.class);
        String docXml = resultMsg.getXmlNam();
        // 返回值为失败时，xml,txt 转移到failed文件夹，返回交换消息体
        if (!exMsg.getResult()){
            // 数据库记录同步信息
            mapper.syncFollowupErrInfo(info.getId(), resultMsg.getReceivedTime(), exMsg.getDesc());
            this.moveToOtherFolder(docXml, FolderPathConfig.failedFolder);
            System.out.println(resultMsg.getMsgBody());
            return ;
        }
        String fieldPk = exMsg.getId();
        mapper.syncFollowupSuccInfo(info.getId(), resultMsg.getReceivedTime());
        this.syncSucHandle(docXml, fieldPk);
    }


    @Override
    public void EmergencyAdd(MessageInfoDO info, ReturnMsgDO resultMsg) {
        InterchangeDO exMsg = gson.fromJson(resultMsg.getMsgBody(), InterchangeDO.class);
        String docXml = resultMsg.getXmlNam();
        // 返回值为失败时，xml,txt 转移到failed文件夹，返回交换消息体
        if (!exMsg.getResult()){
            // 数据库记录同步信息
            mapper.syncEmergencyErrInfo(info.getId(), resultMsg.getReceivedTime(), exMsg.getDesc());
            this.moveToOtherFolder(docXml, FolderPathConfig.failedFolder);
            System.out.println(resultMsg.getMsgBody());
            return ;
        }
        String fieldPk = exMsg.getId();
        mapper.addEmergencySuccInfo(info.getId(), fieldPk, resultMsg.getReceivedTime());
        this.addSucHandle(docXml, fieldPk);
    }

    @Override
    public void EmergencyUpdate(MessageInfoDO info, ReturnMsgDO resultMsg) {

        InterchangeDO exMsg = gson.fromJson(resultMsg.getMsgBody(), InterchangeDO.class);
        String docXml = resultMsg.getXmlNam();
        // 返回值为失败时，xml,txt 转移到failed文件夹，返回交换消息体
        if (!exMsg.getResult()){
            // 数据库记录同步信息
            mapper.syncEmergencyErrInfo(info.getId(), resultMsg.getReceivedTime(), exMsg.getDesc());
            this.moveToOtherFolder(docXml, FolderPathConfig.failedFolder);
            System.out.println(resultMsg.getMsgBody());
            return ;
        }
        String fieldPk = exMsg.getId();
        mapper.syncEmergencySuccInfo(info.getId(), resultMsg.getReceivedTime());
        this.syncSucHandle(docXml, fieldPk);
    }

    @Override
    public void EmergencyDelete(MessageInfoDO info, ReturnMsgDO resultMsg) {
        InterchangeDO exMsg = gson.fromJson(resultMsg.getMsgBody(), InterchangeDO.class);
        String docXml = resultMsg.getXmlNam();
        // 返回值为失败时，xml,txt 转移到failed文件夹，返回交换消息体
        if (!exMsg.getResult()){
            // 数据库记录同步信息
            mapper.syncEmergencyErrInfo(info.getId(), resultMsg.getReceivedTime(), exMsg.getDesc());
            this.moveToOtherFolder(docXml, FolderPathConfig.failedFolder);
            System.out.println(resultMsg.getMsgBody());
            return ;
        }
        String fieldPk = exMsg.getId();
        mapper.syncEmergencySuccInfo(info.getId(), resultMsg.getReceivedTime());
        this.syncSucHandle(docXml, fieldPk);
    }

    public void addSucHandle(String docXml, String fieldPk){
        String msgFilePath =  FolderPathConfig.receivedFolder + File.separator + docXml+".json";
        // todo 没找到json时的处理 记日志
        String msgInfoJson = TextFileUtils.readFileContent(msgFilePath);
        // 更新json的主键
        String jsonRegEx = "\"id\": \"([\\s\\S]*?)\"";
        String oldId = TextFileUtils.matchValue(msgInfoJson, jsonRegEx);
        TextFileUtils.alterStringToCreateNewFile(msgFilePath, oldId, fieldPk);

        // 更新xml主键
        String xmlFilePath = FolderPathConfig.receivedFolder+ File.separator + docXml+".xml";
        String xmlStr = TextFileUtils.readFileContent(xmlFilePath);
        String xmlRegEx = "(<ID>[\\s\\S]*?</ID>)";
        String oldPk = TextFileUtils.matchValue(xmlStr, xmlRegEx);
        TextFileUtils.alterStringToCreateNewFile(xmlFilePath, oldPk, "<ID>"+fieldPk+"</ID>");

        // todo 转移失败异常 日志
        // 返回值为成功，转移到 done/日期/ 文件夹下
        this.moveToOtherFolder(docXml, FolderPathConfig.doneFolder);
    }

    public void syncSucHandle(String docXml, String fieldPk){
        String msgFilePath =  FolderPathConfig.receivedFolder + File.separator + docXml+".json";
        // todo 没找到json时的处理 记日志
        String msgInfoJson = TextFileUtils.readFileContent(msgFilePath);
        // 更新json的主键
        String jsonRegEx = "\"id\": \"([\\s\\S]*?)\"";
        String oldId = TextFileUtils.matchValue(msgInfoJson, jsonRegEx);
        TextFileUtils.alterStringToCreateNewFile(msgFilePath, oldId, fieldPk);

        // todo 转移失败异常 日志
        // 返回值为成功，转移到 done/日期/ 文件夹下
        this.moveToOtherFolder(docXml, FolderPathConfig.doneFolder);
    }

    // todo 转移失败 找不到文件
    private void moveToOtherFolder(String fileNam, String targetFolder){
        TextFileUtils.moveTotherFolders( FolderPathConfig.receivedFolder, fileNam+".xml",  targetFolder);
        TextFileUtils.moveTotherFolders( FolderPathConfig.receivedFolder, fileNam+".json",  targetFolder);
        TextFileUtils.moveTotherFolders( FolderPathConfig.receivedFolder, fileNam+".txt",  targetFolder);
    }

}
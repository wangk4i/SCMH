package com.hyd.subordtest.service;

import com.hyd.subordtest.domain.info.MessageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Created by xieshuai on 2020/6/9 14:14
 *
 */

@Service
public class DealCenterService {


    @Autowired
    OperateTools tools;



    /**
     * 查询档案信息并输出到xml
     * @param info
     * @return
     */
    public void BuildPatInfoMsg(MessageInfo info){
        //调用查询档案xml并输出到文件
        DistribPatMsg(info);
    }

    public void BuildReportCardMsg(MessageInfo info){
        //调用患者报告卡xml并输出到文件
        DistribReportcardMsg(info);
    }

    public void BuildLeaveCardMsg(MessageInfo info){
        //调用查询出院单xml并输出到文件
        DistribDischargeMsg(info);
    }

    public void BuildFollowupMsg(MessageInfo info){
        //调用查询随访xml并输出到文件
        DistribFollowupMsg(info);
    }

    public void BuildEmergacyMsg(MessageInfo info){
        //调用应急处置情况并输出到文件
        DistribEmergencyMsg(info);
    }


    private void DistribPatMsg(MessageInfo info){

        switch (info.getMsgaction()){
            case 1:
            case 2:
                tools.buildPatInfoToXml(info);
                break;
            case 3:
                //删除
                tools.delDocumentToXml(info);
                break;
            case 4:
                //数据恢复
                tools.restoreDocumentToXml(info);
                break;
            case 5:
                //转死亡
                tools.turnDeathDocumentToXml(info);
                break;
            default:
                break;
        }
    }



    private void DistribReportcardMsg(MessageInfo info) {
        switch (info.getMsgaction()) {
            case 1:
            case 2:
                tools.buildReportToXml(info);
                break;
            case 3:
                //删除
                tools.delReportToXml(info);
                break;
            default:
                break;
        }
    }



    private void DistribDischargeMsg(MessageInfo info){
        switch (info.getMsgaction()) {
            case 1:
            case 2:
                tools.buildDischargeToXml(info);
                break;
            case 3:
                //删除
                tools.delDischargeToXml(info);
                break;
            default:
                break;
        }
    }



    private void DistribFollowupMsg(MessageInfo info){
        switch (info.getMsgaction()) {
            case 1:
            case 2:
                tools.buildFollowupToXml(info);
                break;
            case 3:
                //删除
                tools.delFollowupToXml(info);
                break;
            default:
                break;
        }
    }



    private void DistribEmergencyMsg(MessageInfo info){
        switch (info.getMsgaction()) {
            case 1:
            case 2:
                tools.buildEmergencyToXml(info);
                break;
            case 3:
                //删除
                tools.delEmergencyToXml(info);
                break;
            default:
                break;
        }
    }


    public void BuildSendMsg(MessageInfo info) {
        switch (info.getMsgtype()) {
            case 1:
                this.BuildPatInfoMsg(info);
                break;
            case 2:
                this.BuildReportCardMsg(info);
                break;
            case 3:
                this.BuildLeaveCardMsg(info);
                break;
            case 4:
                this.BuildFollowupMsg(info);
                break;
            case 5:
                this.BuildEmergacyMsg(info);
                break;
            default:
                break;
        }
    }


}

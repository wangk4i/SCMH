package com.hyd.subordmq.service;

import com.hyd.subordmq.domain.info.MessageInfo;
import com.hyd.subordmq.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
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
    @Async
    public void BuildPatInfoMsg(MessageInfo info){
        //调用查询档案xml并输出到文件
        try {
            DistribPatInfoMsg(info);
        } catch (Exception e) {
            LogUtil.warn("", e);
        }
    }

    @Async
    public void BuildReportCardMsg(MessageInfo info){
        //调用患者报告卡xml并输出到文件
        try {
            DistribReportcardMsg(info);
        } catch (Exception e) {
            LogUtil.warn("", e);
        }
    }

    @Async
    public void BuildLeaveCardMsg(MessageInfo info){
        //调用查询出院单xml并输出到文件
        try{
            DistribDischargeMsg(info);
        } catch (Exception e) {
            LogUtil.warn("", e);
        }
    }

    @Async
    public void BuildFollowupMsg(MessageInfo info){
        //调用查询随访xml并输出到文件
        try{
            DistribFollowupMsg(info);
        } catch (Exception e) {
            LogUtil.warn("", e);
        }
    }

    @Async
    public void BuildEmergacyMsg(MessageInfo info){
        //调用应急处置情况并输出到文件
        try {
            DistribEmergencyMsg(info);
        } catch (Exception e) {
            LogUtil.warn("", e);
        }
    }


    private void DistribPatInfoMsg(MessageInfo info){

        switch (info.getMsgaction()){
            case 1:
            case 2:
                tools.buildPatInfoToXml(info);
                break;
            case 3:
                //删除
                tools.delPatInfoToXml(info);
                break;
            case 4:
                // todo 年审
                break;
            case 5:
                //数据恢复
                tools.restorePatInfoToXml(info);
                break;
            case 6:
                //转死亡
                tools.turnDeathPatInfoToXml(info);
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


    public void DistribMsg(MessageInfo info) {
        switch (info.getMsgtype()) {
            case 1:
                this.DistribPatInfoMsg(info);
                break;
            case 2:
                this.DistribReportcardMsg(info);
                break;
            case 3:
                this.DistribDischargeMsg(info);
                break;
            case 4:
                this.DistribFollowupMsg(info);
                break;
            case 5:
                this.DistribEmergencyMsg(info);
                break;
            default:
                break;
        }
    }

}

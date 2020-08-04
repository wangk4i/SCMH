package com.hyd.subordtest.service;

import com.hyd.subordtest.domain.info.MessageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * Created by xieshuai on 2020/6/9 14:14
 *
 */

@Service
public class BasicInfoService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    OperateTools tools;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * 查询档案信息并输出到xml
     * @param info
     * @return
     */
    public void queryDocToXml(MessageInfo info){
        //调用查询档案xml并输出到文件
        syncDocumentToXml(info);
    }

    public void queryRepToXml(MessageInfo info){
        //调用患者报告卡xml并输出到文件
        syncReportcardToXml(info);
    }

    public void queryDiscToXml(MessageInfo info){
        //调用查询出院单xml并输出到文件
        syncDischargeToXml(info);
    }

    public void queryFollToXml(MessageInfo info){
        //调用查询随访xml并输出到文件
        syncFollowupToXml(info);
    }

    public void queryEmerToXml(MessageInfo info){
        //调用应急处置情况并输出到文件
        syncEmergencyToXml(info);
    }


    private void syncDocumentToXml(MessageInfo info){

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



    private void syncReportcardToXml(MessageInfo info) {
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



    private void syncDischargeToXml(MessageInfo info){
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



    private void syncFollowupToXml(MessageInfo info){
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



    private void syncEmergencyToXml(MessageInfo info){
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


}

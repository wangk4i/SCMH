package com.hyd.subordtest.service;

import com.hyd.subordtest.domain.info.MessageInfo;
import com.hyd.subordtest.domain.info.MsgBuildConf;
import com.hyd.subordtest.domain.info.MsgBuildXml;
import com.hyd.subordtest.mapper.BatchMsgBuildMapper;
import com.hyd.subordtest.utils.LogUtil;
import com.hyd.subordtest.utils.XmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Created by xieshuai on 2020/6/9 14:14
 *
 */

@Service
public class BasicInfoService {

    @Autowired
    OperateTools tools;


    @Autowired(required = false)
    private BatchMsgBuildMapper batchMsgBuildMapper;

    @Value("${config.docmentenum.reportOrgNam}")
    private String reportOrg;
    @Value("${config.docmentenum.reportOrgNam}")
    private String reportZone;

    @Value("${config.confPath}")
    private String confPath;


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


    public void batchProcessDocument(){

        MsgBuildXml msgInfo = (MsgBuildXml) XmlUtil.convertXmlFileToObject(MsgBuildXml.class, confPath);
        /* 过滤配置文件里 ifUse 为true 的消息 */
        List<MsgBuildConf> ConfList = msgInfo.getMsgInfo();
        Boolean ifUse = true;
        ConfList = ConfList.stream()
                .filter(MsgInfo -> ifUse.equals(MsgInfo.isIfUse())).collect(Collectors.toList());

        int todoF = 0;
        int doneF = 0;
        String startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        if (ConfList.size() > 0){
            for (MsgBuildConf conf:ConfList){
                List<Map<String,Object>> MsgList = batchMsgBuildMapper.queryBatchDataByConf(conf.getMsgSql());
                todoF += MsgList.size();
                for (Map<String,Object> map:MsgList) {
                    boolean suc = false;
                    MessageInfo info = new MessageInfo();
                    info.setId((String) map.get("Cd"));
                    info.setZone(reportZone);
                    info.setOrgan(reportOrg);
                    info.setMsgcate(conf.getMsgCate());
                    info.setMsgtype(conf.getMsgType());
                    info.setMsgaction(conf.getMsgAction());
                    switch (info.getMsgtype()){
                        case 1:
                            suc = syncDocumentToXml(info);
                            break;
                        case 2:
                            suc = syncReportcardToXml(info);
                            break;
                        case 3:
                            suc = syncDischargeToXml(info);
                            break;
                        case 4:
                            suc = syncFollowupToXml(info);
                            break;
                        case 5:
                            suc = syncEmergencyToXml(info);
                            break;
                        default:
                            break;
                    }if (suc) {
                        doneF++;
                    }
                } } }
        String endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        // todo 输出日志
        String dealLog = this.finishLog(startTime, endTime, todoF, doneF);
        LogUtil.buildLogFile(dealLog);
        System.out.println(dealLog);
    }


    private boolean syncDocumentToXml(MessageInfo info){
        boolean suc = false;
        switch (info.getMsgaction()){
            case 1:
            case 2:
                suc = tools.buildPatInfoToXml(info);
                break;
            case 3:
                //删除
                suc = tools.delDocumentToXml(info);
                break;
            case 4:
                //数据恢复
                suc = tools.restoreDocumentToXml(info);
                break;
            case 5:
                //转死亡
                suc = tools.turnDeathDocumentToXml(info);
                break;
            default:
                break;
        }
        return suc;
    }



    private boolean syncReportcardToXml(MessageInfo info) {
        boolean suc = false;
        switch (info.getMsgaction()) {
            case 1:
            case 2:
                suc = tools.buildReportToXml(info);
                break;
            case 3:
                //删除
                suc = tools.delReportToXml(info);
                break;
            default:
                break;
        }
        return suc;
    }



    private boolean syncDischargeToXml(MessageInfo info){
        boolean suc = false;
        switch (info.getMsgaction()) {
            case 1:
            case 2:
                suc = tools.buildDischargeToXml(info);
                break;
            case 3:
                //删除
                suc = tools.delDischargeToXml(info);
                break;
            default:
                break;
        }
        return suc;
    }



    private boolean syncFollowupToXml(MessageInfo info){
        boolean suc = false;
        switch (info.getMsgaction()) {
            case 1:
            case 2:
                suc = tools.buildFollowupToXml(info);
                break;
            case 3:
                //删除
                suc = tools.delFollowupToXml(info);
                break;
            default:
                break;
        }
        return suc;
    }



    private boolean syncEmergencyToXml(MessageInfo info){
        boolean suc = false;
        switch (info.getMsgaction()) {
            case 1:
            case 2:
                suc = tools.buildEmergencyToXml(info);
                break;
            case 3:
                //删除
                suc = tools.delEmergencyToXml(info);
                break;
            default:
                break;
        }
        return suc;
    }


    private String finishLog(String startTime, String endTime, int todoF, int doneF){
        StringBuffer sb = new StringBuffer();
        sb.append("\n开始时间: ").append(startTime).append("\n");
        sb.append("待处理: ").append(todoF).append("\n");
        sb.append("已处理: ").append(doneF).append("\n");
        sb.append("结束时间: ").append(endTime).append("\n\n");
        sb.append("##############################################\n");
        return sb.toString();
    }

}

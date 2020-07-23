package com.hyd.subordtest.controller;

import com.hyd.subordtest.domain.info.MessageInfo;
import com.hyd.subordtest.domain.info.MsgBuildConf;
import com.hyd.subordtest.domain.info.MsgBuildXml;
import com.hyd.subordtest.mapper.BatchMsgBuildMapper;
import com.hyd.subordtest.service.BasicInfoService;
import com.hyd.subordtest.utils.XmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/BatchProcess")
public class BatchProcessController {
    @Autowired
    BasicInfoService basicInfoService;
    @Autowired(required = false)
    BatchMsgBuildMapper batchMsgBuildMapper;

    @RequestMapping("/batchDocumentToXml")
    public String batchDocumentToXml(){
        String confPath = "../script/MsgBuildConf.xml";
        MsgBuildXml list01 = (MsgBuildXml) XmlUtil.convertXmlFileToObject(MsgBuildXml.class, confPath);
        /* 取配置文件里 ifUse 为true 的消息 */
        List<MsgBuildConf> list = list01.getMsgInfo();
        Boolean ifUse = true;
        list = list.stream()
                .filter(MsgInfo -> ifUse.equals(MsgInfo.isIfUse())).collect(Collectors.toList());
        if (list.size() > 0){
            for (MsgBuildConf conf:list){
                List<Map<String,Object>> list02 =  batchMsgBuildMapper.queryBatchDataByConf(conf.getMsgSql());
                for (Map<String,Object> map:list02) {
                    MessageInfo info = new MessageInfo();
                    info.setId((String) map.get("Cd"));
                    info.setMsgaction(conf.getMsgAction());
                    switch (conf.getMsgType()){
                        case 1:
                            basicInfoService.queryDocToXml(info);
                            break;
                        case 2:
                            basicInfoService.queryRepToXml(info);
                            break;
                        case 3:
                            basicInfoService.queryDiscToXml(info);
                            break;
                        case 4:
                            basicInfoService.queryFollToXml(info);
                            break;
                        case 5:
                            basicInfoService.queryEmerToXml(info);
                            break;
                        default:
                            break;
                    }

                }

            }


        }

        return "success";
    }




    @RequestMapping("/testm")
    public List testmapp(){
        String sqlStr="select top 10 Cd,ZoneCd,OrganCd,FIELDPK \n" +
                "\t\tfrom SPM_SPMPatInfoMana \n" +
                "\t\twhere ZoneCd like '51%' and State=1 and DelStatus='DelLogo001'\n" +
                "\t\tand len(isnull(FIELDPK,''))=0";
        List list=batchMsgBuildMapper.queryBatchDataByConf(sqlStr);
        return list;

    }
}

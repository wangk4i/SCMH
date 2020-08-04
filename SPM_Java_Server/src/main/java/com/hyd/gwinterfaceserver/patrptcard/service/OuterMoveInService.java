package com.hyd.gwinterfaceserver.patrptcard.service;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.hyd.gwinterfaceserver.patrptcard.info.PatMoveOut;
import com.hyd.gwinterfaceserver.patrptcard.vo.AcceptMoveIn;
import com.hyd.gwinterfaceserver.patrptcard.vo.PatInfo;
import com.hyd.gwinterfaceserver.patrptcard.info.PatReportMoveOutRequest;
import com.hyd.gwinterfaceserver.patrptcard.mapper.OuterMoveInMapper;
import com.hyd.gwinterfaceserver.patrptcard.vo.MoveOutInfo;
import com.hyd.gwinterfaceserver.patrptcard.vo.MoveOutReprotInfo;
import com.hyd.gwinterfaceserver.util.GWUrl;
import com.hyd.gwinterfaceserver.util.HttpUtils;
import com.hyd.gwinterfaceserver.util.ResultInfo;
import com.hyd.system.exceptclass.SqlException;
import com.hyd.system.factory.ResultFactorys;
import com.hyd.system.vo.ValidateVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OuterMoveInService {
    @Autowired
    OuterMoveInMapper outerMoveInMapper;

    private static final Logger logger = LogManager.getLogger("gw");

    //获取流转信息主键
    public String getMoveGWFIELDPK(String cd) {
        try {
            return outerMoveInMapper.getMoveGWFIELDPK(cd);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("获取流转主键失败");
        }
    }


    //获取流转信息
    public MoveOutInfo getMoveOutInfo(String cd) {
        try {
            return outerMoveInMapper.getMoveOutInfo(cd);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("获取流转信息失败");
        }
    }

    //获取机构国网编号
    public String getOrgCode(String inOrgCd) {
        try {
            return outerMoveInMapper.getOrgCode(inOrgCd);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询机构国网编号失败");
        }
    }


    public void PatReportDistributionBJ(PatReportMoveOutRequest input) {
        //获取流转信息
        MoveOutInfo moveOutInfo = getMoveOutInfo(input.getCd());

        //流转分配
        StringBuffer buffer = new StringBuffer();
        buffer.append("{")
                .append("\"MoveInAndOutId\":\"").append(moveOutInfo.fIELDPK)
                .append("\",\"InOrgZoneCode\":\"").append(input.getCurrentZone())
                .append("\",\"InOrgCode\":\"").append(getOrgCode(input.getCurrentOrgan()))
                .append("\"}");
        ResultInfo resultInfo = HttpUtils.post(GWUrl.mentalhealthbusinesssynergy + "distributionMove", buffer.toString(), ResultInfo.class);
        if ("-1".equals(resultInfo.code)) {
            throw new SqlException("流转分配失败:" + resultInfo.message);
        }
        //回写省网
        MoveOutReprotInfo moveOutReprotInfo = new MoveOutReprotInfo();
        try {
            moveOutReprotInfo.cd = input.getCd();
            moveOutReprotInfo.zoneCd = input.getCurrentZone();
            moveOutReprotInfo.organCd = input.getCurrentOrgan();
            moveOutReprotInfo.moveOutCause = input.getSignedDate();
            outerMoveInMapper.MoveOutBJ1(moveOutReprotInfo, input.getExtInfoObj());
        } catch (Exception e) {
            logger.error("患者报告卡外省迁入后分配至社区(本级1)回写失败：" + JSONObject.toJSONString(moveOutReprotInfo));
            throw new SqlException("回写省网失败");
        }
    }

    /**
     * 接收建档
     * 患者报告卡迁入社区新建患者基本信息后终结国网流转记录，国网报错就记日志，有问题根据日志来恢复（直报1）
     */
    public void PatReportAcceptMoveInZb1(PatReportMoveOutRequest input, ValidateVO validateVO) {
        //根据流转记录编号找到对应的报告卡然后再通过报告卡中的身份证号码找到对应的患者基本信息?
        PatInfo patInfo = outerMoveInMapper.getPatInfo(input.getRptCardID());

        //迁入接收建档
        Moveintoreceivefile(patInfo,validateVO);


    }


    public void Moveintoreceivefile(PatInfo patInfo,ValidateVO validateVO) {
        //构建json数据
        AcceptMoveIn acceptMoveIn = new AcceptMoveIn();
        acceptMoveIn.setPatInfo(patInfo);
        acceptMoveIn.setMoveInAndOutId(validateVO.getData());
        Gson gson = new Gson();
        String json = gson.toJson(acceptMoveIn);
        //迁入接收建档
        ResultInfo resultInfo = HttpUtils.post(GWUrl.mentalhealthbusinesssynergy + "acceptMoveIn", json, ResultInfo.class);
        if (resultInfo.code == -1) {
            throw new SqlException("迁入接收建档失败" + resultInfo.message);
        }
        //同步患者信息国网编号
//        outerMoveInMapper.syncPatInfo(PatInfo.ID, acceptMoveInResponseObj.keyMessage);
    }

    public void ResponseMoveInBj1(ValidateVO validateVO) {
        //GW接收
        StringBuffer buffer = new StringBuffer();
        buffer.append("{")
                .append("\"MoveInAndOutId\":\"").append(validateVO.getData())
                .append("\"}");
        ResultInfo resultInfo = HttpUtils.post(GWUrl.mentalhealthbusinesssynergy + "responseMoveIn", buffer.toString(), ResultInfo.class);
        if (resultInfo.code == -1) {
           throw new SqlException("国网接收失败:" + resultInfo.message);
        }
    }
}

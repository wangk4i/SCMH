package com.hyd.gwinterfaceserver.patrptcard.validate;

import com.hyd.gwinterfaceserver.patrptcard.info.PatInfoCallBackMoveRequest;
import com.hyd.gwinterfaceserver.patrptcard.info.PatMoveOut;
import com.hyd.gwinterfaceserver.patrptcard.info.PatReportMoveOutRequest;
import com.hyd.gwinterfaceserver.patrptcard.service.OuterMoveInService;
import com.hyd.gwinterfaceserver.patrptcard.service.OuterMoveOutService;
import com.hyd.gwinterfaceserver.patrptcard.vo.CaseReport2;
import com.hyd.gwinterfaceserver.patrptcard.vo.MoveOutInfo;
import com.hyd.gwinterfaceserver.patrptcard.vo.RetValidr;
import com.hyd.gwinterfaceserver.patrptcard.vo.RptCardJudgeVO;
import com.hyd.gwinterfaceserver.util.GWUrl;
import com.hyd.gwinterfaceserver.util.HttpUtils;
import com.hyd.gwinterfaceserver.util.ResultInfo;
import com.hyd.system.exceptclass.BusineExceptions;
import com.hyd.system.exceptclass.SqlException;
import com.hyd.system.factory.ResultFactorys;
import com.hyd.system.vo.ValidateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class CardBusinessValider {

    @Autowired
    OuterMoveOutService outerMoveOutService;

    @Autowired
    OuterMoveInService outerMoveInService;

    public RetValidr PatInfoCallBackMoveRequestValid(PatInfoCallBackMoveRequest input) {
        RetValidr retValidr = new RetValidr();
        //获取流转信息
        MoveOutInfo moveOutInfo = outerMoveOutService.getMoveOutInfo(input.moveCode);
        if (moveOutInfo.fIELDPK.isEmpty() || moveOutInfo.fIELDPK == null) {
            throw new BusineExceptions("该流转记录不满足回收要求：PK值为空");
        }
        if (!"DelLogo001".equals(moveOutInfo.delStatus)) {
            throw new BusineExceptions("该流转记录不满足回收要求：数据不正常");
        }
        return null;
    }


    public ValidateVO PatReportMoveOutRequestValid(PatReportMoveOutRequest input) {
        ValidateVO validateVO = new ValidateVO();
        //判断机构是否为空  有无基层直报
        //如果是社区并且机构为空
        if ("1".equals(input.getMoveoutType()) && (input.getCurrentOrgan().isEmpty() || input.getCurrentOrgan() == null)) {
            //迁入机构为空
            throw new BusineExceptions("请选择迁往机构");
        }
        //不是社区机构为空或者是社区机构不为空
        if (input.getCurrentOrgan().isEmpty() || input.getCurrentOrgan() == null) {
            input.setCurrentOrgan("000000000");
        } else { //判断机构下有无基层直报
            HashMap<String, String> map = new HashMap<>();
            map.put("org_code", input.getCurrentOrgan());
            ResultInfo resultInfo = HttpUtils.get(GWUrl.mentalhealthbusinesssynergy + "getUserInformationByOrgcode", map, ResultInfo.class);
            if (resultInfo.code == -1) { //获取失败无基层直报
                throw new BusineExceptions(resultInfo.message);
            } else {//返回基层直报信息
                validateVO.setData(resultInfo.data.contactInformation);
            }
        }

        //RptCardJudgeVO item
        // 还未同步 无国网主键 同步标记！=1
        // 同步有错
        // 迁出状态 空 迁出撤回 结束
        //判断这个报告卡是否存在

        // 判断是否同步 是否有错，
        RptCardJudgeVO rptCardJudgeVO = outerMoveOutService.getRptCardJudgeVO(input.getRptCardID());
        if (rptCardJudgeVO == null) {
            throw new BusineExceptions("患者报告卡不存在，无法迁出");
        }
        if (!"1".equals(rptCardJudgeVO.syncerror) || rptCardJudgeVO.fieldpk.isEmpty() || rptCardJudgeVO.fieldpk == null || !rptCardJudgeVO.syncerror.isEmpty()) {
            throw new BusineExceptions("未同步国网，请同步后进行操作" + rptCardJudgeVO.syncerror);
        }
        //判断当前报告卡是否在流转中
        if ("FlowState001".equals(rptCardJudgeVO.movestatues)) {
            throw new BusineExceptions("当前报告卡迁出中，不能重复迁出");
        }
        return validateVO;
    }


    public ValidateVO PatReportMoveOutBJ(PatReportMoveOutRequest input) {
        ValidateVO validateVO = new ValidateVO();
        //通过流转编号获取报告卡编号
        String InciRptCd = outerMoveOutService.getReportCardByMoveCd(input.getCd());
        if (InciRptCd.isEmpty() || InciRptCd == null) {
            throw new BusineExceptions("患者报告卡编号为空");
        }

        //判断机构是否为空  有无基层直报
        //如果是社区并且机构为空
        if ("1".equals(input.getMoveoutType()) && (input.getCurrentOrgan().isEmpty() || input.getCurrentOrgan() == null)) {
            //迁入机构为空
            throw new BusineExceptions("请选择迁往机构");
        }
        //不是社区机构为空或者是社区机构不为空
        if (input.getCurrentOrgan().isEmpty() || input.getCurrentOrgan() == null) {
            input.setCurrentOrgan("000000000");
        } else { //判断机构下有无基层直报
            HashMap<String, String> map = new HashMap<>();
            map.put("org_code", input.getCurrentOrgan());
            ResultInfo resultInfo = HttpUtils.get(GWUrl.mentalhealthbusinesssynergy + "getUserInformationByOrgcode", map, ResultInfo.class);
            if (resultInfo.code == -1) { //获取失败无基层直报
                throw new BusineExceptions(resultInfo.message);
            } else {//返回基层直报信息
                validateVO.setData(resultInfo.data.contactInformation);
            }
        }

        // 判断是否同步 是否有错，
        RptCardJudgeVO rptCardJudgeVO = outerMoveOutService.getRptCardJudgeVO(input.getRptCardID());
        if (rptCardJudgeVO == null) {
            throw new BusineExceptions("患者报告卡不存在，无法迁出");
        }
        if (!"1".equals(rptCardJudgeVO.syncerror) || rptCardJudgeVO.fieldpk.isEmpty() || rptCardJudgeVO.fieldpk == null || !rptCardJudgeVO.syncerror.isEmpty()) {
            throw new BusineExceptions("未同步国网，请同步后进行操作" + rptCardJudgeVO.syncerror);
        }
        //判断当前报告卡是否在流转中
        if ("FlowState001".equals(rptCardJudgeVO.movestatues)) {
            throw new BusineExceptions("当前报告卡迁出中，不能重复迁出");
        }
        return validateVO;
    }


    public ValidateVO PatReportAcceptMoveInValid(PatReportMoveOutRequest input) {
        ValidateVO validateVO=new ValidateVO();
        //找到流转记录对应的最初流转记录是否是国网流转
        String FIELDPK = outerMoveOutService.getMoveInfoIsGW(input.getCd());
        if(!(FIELDPK.isEmpty()||FIELDPK==null)){ //是国网流转返回pk值
            validateVO.setData(FIELDPK);
        }
        return validateVO;
    }

    public ValidateVO PatMoveOutValid(PatMoveOut patMoveOut) {
        ValidateVO validateVO=new ValidateVO();
        //找到流转记录对应的最初流转记录是否是国网流转
        String moveGWFIELDPK = outerMoveInService.getMoveGWFIELDPK(patMoveOut.moveOutCd);
        if (moveGWFIELDPK.isEmpty() || moveGWFIELDPK == null) {
            throw new BusineExceptions("国网流转编码不存在");
        }
        validateVO.setData(moveGWFIELDPK);
        return validateVO;
    }
}

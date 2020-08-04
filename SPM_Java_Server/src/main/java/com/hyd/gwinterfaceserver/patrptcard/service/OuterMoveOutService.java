package com.hyd.gwinterfaceserver.patrptcard.service;

import com.alibaba.fastjson.JSONObject;
import com.hyd.gwinterfaceserver.patrptcard.info.CallBackMoveInput;
import com.hyd.gwinterfaceserver.patrptcard.info.PatInfoCallBackMoveRequest;
import com.hyd.gwinterfaceserver.patrptcard.info.PatReportMoveOutRequest;
import com.hyd.gwinterfaceserver.patrptcard.mapper.OuterMoveOutMapper;
import com.hyd.gwinterfaceserver.patrptcard.vo.*;
import com.hyd.gwinterfaceserver.util.GWUrl;
import com.hyd.gwinterfaceserver.util.HttpUtils;
import com.hyd.gwinterfaceserver.util.Pageinfos;
import com.hyd.gwinterfaceserver.util.ResultInfo;
import com.hyd.system.exceptclass.ResultExceptions;
import com.hyd.system.exceptclass.SqlException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class OuterMoveOutService {

    @Autowired
    OuterMoveOutMapper outerMoveOutMapper;

    private static final Logger logger = LogManager.getLogger("gw");

    /**
     * 患者报告卡迁出外省(直报2)
     */
    public void PatReportMoveOutByZb2(PatReportMoveOutRequest input) {
        //获取患者报告卡信息
        CaseReport2 caseReport2 = getPatReportCard(input.getRptCardID());
        //迁出
        RetValidr retValidr = moveOutInformationZb2(input, caseReport2);
        //回写
        moveOutInformationZb2writeback(input, caseReport2, retValidr);
    }

    //迁出(直报2)
    public RetValidr moveOutInformationZb2(PatReportMoveOutRequest input, CaseReport2 caseReport2) {
        RetValidr retValidr = new RetValidr();
        //1.定义格式
        DateTimeFormatter  df= DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter informeddf = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter informeddate= DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        //2.把字符串转成localDate
        LocalDate localDate = LocalDate.parse(caseReport2.informedDate, df);

        //3.把 2019-01-01  转成  20190101
        String informedDate = localDate.format(informeddf);
        String Date = LocalDateTime.now().format(informeddate);
        StringBuffer buffer = new StringBuffer();

        buffer.append("{")
                .append("\"MoveInAndOutId\":\"").append("")
                .append("\",\"MoveInAndOutType\":\"").append("2")
                .append("\",\"ReportID\":\"").append(caseReport2.newCaseReportId) //pk值
                .append("\",\"PersonID\":\"").append(caseReport2.basicInformationId)
                .append("\",\"OutZoneCode\":\"").append(input.getMoveOutZone())
                .append("\",\"OutOrgCode\":\"").append(getOrgCode(input.getMoveOutOrgan()))
                .append("\",\"MoveOutTime\":\"").append(Date)
                .append("\",\"InZoneCode\":\"").append(input.getCurrentZone())
                .append("\",\"InOrgCode\":\"").append("000000000".equals(input.getCurrentOrgan()) ? "000000000" : getOrgCode(input.getCurrentOrgan()))
                .append("\",\"MoveInTime\":\"").append("")
                .append("\",\"MoveOutCause\":\"").append(input.getSignedDate())
                .append("\",\"SignedInformedConsentDate\":\"").append(informedDate)
                .append("\",\"RefuseCause\":\"").append("")
                .append("\"}");
        //迁出
        ResultInfo resultInfo = HttpUtils.post(GWUrl.mentalhealthbusinesssynergy + "moveOutInformation", buffer.toString(), ResultInfo.class);
        if (resultInfo.code == -1) {
            throw new SqlException("迁出失败" + resultInfo.message);
        }
        retValidr.data = resultInfo.data.iD; //返回迁出成功返回值
        return retValidr;
    }

    //回写(直报2)
    public void moveOutInformationZb2writeback(PatReportMoveOutRequest input, CaseReport2 caseReport2, RetValidr retValidr) {
        MoveOutReprotInfo moveOutInfo = new MoveOutReprotInfo();
        try {
            //回写省网
            moveOutInfo.cd = caseReport2.iD;
            moveOutInfo.zoneCd = input.getCurrentZone();
            moveOutInfo.organCd = input.getCurrentOrgan();
            moveOutInfo.iDCode = caseReport2.iDCode;
            moveOutInfo.signedDate = input.getSignedDate();
            moveOutInfo.outZoneCd = input.getMoveOutZone();
            moveOutInfo.outOrganCd = input.getMoveOutOrgan();
            moveOutInfo.fIELDPK = retValidr.data;
            MoveOutZB2(moveOutInfo, input.getExtInfoObj());
        } catch (Exception e) { //回写失败
            //报告卡迁出收回
            StringBuffer buffer1 = new StringBuffer();
            buffer1.append("{")
                    .append("\"MoveInAndOutId\":\"").append(retValidr.data)
                    .append("\"}");
            HttpUtils.post(GWUrl.mentalhealthbusinesssynergy + "callBackMove", buffer1.toString(), ResultInfo.class);
            logger.error("患者报告卡迁出外省(直报2)回写失败： " + JSONObject.toJSONString(moveOutInfo));
            throw new SqlException("回写省网失败");
        }
    }


    /**
     * 患者报告卡迁出外省(本级1)
     *
     * @param input
     */
    public void PatReportMoveOutBJ(PatReportMoveOutRequest input) {
        //获取患者报告卡信息
        CaseReport2 caseReport2 = getPatReportCard(input.getRptCardID());
        //GW迁出
        RetValidr retValidr = moveOutformationBj1(input, caseReport2);
        //回写
        moveOutformationBj1writeback(input, caseReport2, retValidr);
    }

    //迁出(本级1)
    private RetValidr moveOutformationBj1(PatReportMoveOutRequest input, CaseReport2 caseReport2) {
        RetValidr retValidr = new RetValidr();
        //1.定义格式
        DateTimeFormatter  df= DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter informeddf = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter informeddate= DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        //2.把字符串转成localDate
        LocalDate localDate = LocalDate.parse(caseReport2.informedDate, df);

        //3.把 2019-01-01  转成  20190101
        String informedDate = localDate.format(informeddf);
        String Date = LocalDateTime.now().format(informeddate);

        StringBuffer buffer = new StringBuffer();
        buffer.append("{")
                .append("\"MoveInAndOutId\":\"").append("")
                .append("\",\"MoveInAndOutType\":\"").append("2")
                .append("\",\"ReportID\":\"").append(caseReport2.newCaseReportId) //pk值
                .append("\",\"PersonID\":\"").append(caseReport2.basicInformationId)
                .append("\",\"OutZoneCode\":\"").append(input.getMoveOutZone())
                .append("\",\"OutOrgCode\":\"").append(getOrgCode(input.getMoveOutOrgan()))
                .append("\",\"MoveOutTime\":\"").append(Date)
                .append("\",\"InZoneCode\":\"").append(input.getCurrentZone())
                .append("\",\"InOrgCode\":\"").append("000000000".equals(input.getCurrentOrgan()) ? "000000000" : getOrgCode(input.getCurrentOrgan()))
                .append("\",\"MoveInTime\":\"").append("")
                .append("\",\"MoveOutCause\":\"").append(input.getSignedDate())
                .append("\",\"SignedInformedConsentDate\":\"").append(informedDate)
                .append("\",\"RefuseCause\":\"").append("")
                .append("\"}");
        //迁出
        ResultInfo resultInfo = HttpUtils.post(GWUrl.mentalhealthbusinesssynergy + "moveOutInformation", buffer.toString(), ResultInfo.class);
        if (resultInfo.code == -1) {
            throw new SqlException("迁出失败" + resultInfo.message);
        }
        retValidr.data = resultInfo.data.iD; //返回迁出成功返回值
        return retValidr;
    }

    //回写(本级1)
    public void moveOutformationBj1writeback(PatReportMoveOutRequest input, CaseReport2 caseReport2, RetValidr retValidr) {
        //回写省网
        MoveOutReprotInfo moveOutInfo = new MoveOutReprotInfo();
        try {
            moveOutInfo.cd = caseReport2.iD;
            moveOutInfo.zoneCd = input.getCurrentZone();
            moveOutInfo.organCd = input.getCurrentOrgan();
            moveOutInfo.moveOutCause = input.getSignedDate();
            moveOutInfo.fIELDPK = retValidr.data;
            MoveOutBJ1(moveOutInfo, input.getExtInfoObj());
        } catch (Exception e) {
            //报告卡迁出收回
            StringBuffer buffer1 = new StringBuffer();
            buffer1.append("{")
                    .append("\"MoveInAndOutId\":\"").append(retValidr.data)
                    .append("\"}");
            HttpUtils.post(GWUrl.mentalhealthbusinesssynergy + "callBackMove", buffer1.toString(), ResultInfo.class);
            logger.error("患者报告卡迁出外省(本级1)回写失败： " + JSONObject.toJSONString(moveOutInfo));
            throw new SqlException("回写省网失败");
        }
    }


    /**
     * 患者报告卡回收(直报2)
     *
     * @param input
     */
    public void PatInfoCallBackMoveZb2(PatInfoCallBackMoveRequest input) {
        //获取流转信息
        MoveOutInfo moveOutInfo = getMoveOutInfo(input.moveCode);
        //GW收回
        moveOutPatRecycle(input, moveOutInfo);
        //回写
        moveOutPatRecyclewriteback(input, moveOutInfo);
    }

    public void moveOutPatRecycle(PatInfoCallBackMoveRequest input, MoveOutInfo moveOutInfo) {
        //报告卡迁出收回
        StringBuffer buffer = new StringBuffer();
        buffer.append("{")
                .append("\"MoveInAndOutId\":\"").append(moveOutInfo.fIELDPK)
                .append("\"}");
        ResultInfo resultInfo1 = HttpUtils.post(GWUrl.mentalhealthbusinesssynergy + "callBackMove", buffer.toString(), ResultInfo.class);
        if (resultInfo1.code == -1) {
            throw new SqlException("回收失败:" + resultInfo1.message);
        }
    }

    public void moveOutPatRecyclewriteback(PatInfoCallBackMoveRequest input, MoveOutInfo moveOutInfo) {
        try {
            //回写省网(收回)
            //患者信息流转
            if ("OutType001".equals(moveOutInfo.moveOutCd)) {
                //收回患者信息(省网回写)
                RecoveryOutPat(input.moveCode, null, null, input.extInfoObj);
            }
            //患者报告卡流转
            else if ("OutType003".equals(moveOutInfo.moveOutCd)) {
                //收回患者报告卡(省网回写)
                CallBackMoveOut(input.moveCode, input.extInfoObj);
            }
        } catch (SqlException e) {
            logger.error("收回患者信息回写失败： " + JSONObject.toJSONString(input));
            throw new SqlException(e.getMsg());
        }
    }


    //检验迁入地区是否是区县级
    public Boolean getInZoneLv(String inZoneCd) {
        try {
            return outerMoveOutMapper.getInZoneLv(inZoneCd) == 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("检验迁入地区是否是区县级失败");
        }
    }

    //获取机构国网编号
    public String getOrgCode(String inOrgCd) {
        try {
            return outerMoveOutMapper.getOrgCode(inOrgCd);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("查询机构国网编号失败");
        }
    }


    //获取患者报告卡信息
    public CaseReport2 getPatReportCard(String inciRptCd) {
        try {
            return outerMoveOutMapper.getPatReportCard(inciRptCd);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("获取患者报告卡信息失败");
        }
    }

    //报告卡判断
    public RptCardJudgeVO getRptCardJudgeVO(String inciRptCd) {
        try {
            return outerMoveOutMapper.getRptCardJudgeVO(inciRptCd);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("获取患者报告卡信息失败");
        }
    }


    //通过流转编号获取报告卡编号
    public String getReportCardByMoveCd(String cd) {
        try {
            return outerMoveOutMapper.getReportCardByMoveCd(cd);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("获取报告卡编号失败");
        }
    }


    /**
     *  迁出收回方法(本级1) (直报1)
     */
    public void callBackMoveZb1Bj1(CallBackMoveInput input) {
        //判断报告卡流转记录的国网状态
        String PK = "";
        if (input.isPK.isEmpty() || input.isPK == null) {
            PK = outerMoveOutMapper.getPKByLeftRptCd(input.moveInAndOutId);
            if (PK.isEmpty() || PK == null) {
                throw new ResultExceptions("回收失败，未同步国网数据，请先同步！", 3);
            }
        } else {
            if ("1".equals(input.isPK)) {
                PK = input.moveInAndOutId;
            } else {
                throw new ResultExceptions("回收失败，入参isPK错误！", 3);
            }
        }
        //构建国网json数据，条用回收接口，失败返回国网消息给用户，成功的时候更新流转记录
        StringBuffer buffer = new StringBuffer();
        buffer.append("{")
                .append("\"MoveInAndOutId\":\"").append(PK)
                .append("\"}");
        ResultInfo resultInfo1 = HttpUtils.post(GWUrl.mentalhealthbusinesssynergy + "callBackMove", buffer.toString(), ResultInfo.class);
        if (!"1".equals(resultInfo1.code)) {
            throw new ResultExceptions("迁出接口执行未成功", 2);
        }
    }

    //迁出患者报告卡（省网回写）直报2
    public void MoveOutZB2(MoveOutReprotInfo moveOutInfo, ExtInfo extInfoObj) {
        String errMsg = "";
        try {
            outerMoveOutMapper.MoveOutZB2(moveOutInfo, extInfoObj);
            errMsg = moveOutInfo.errMsg;
            if (!"".equals(errMsg) && errMsg != null) {
                throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("回写省网失败" + errMsg);
        }
    }

    //迁出患者报告卡（省网回写）本级1
    public void MoveOutBJ1(MoveOutReprotInfo moveOutInfo, ExtInfo extInfoObj) {
        String errMsg = "";
        try {
            outerMoveOutMapper.MoveOutBJ1(moveOutInfo, extInfoObj);
            errMsg = moveOutInfo.errMsg;
            if (!"".equals(errMsg) && errMsg != null) {
                throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("回写省网失败" + errMsg);
        }
    }

    //收回患者信息(省网回写)
    public void RecoveryOutPat(String moveCode, String zoneCd, String organCd, ExtInfo extInfoObj) {
        String errMsg = "";
        try {
            Pageinfos pageinfos = new Pageinfos();
            outerMoveOutMapper.RecoveryOutPat(moveCode, zoneCd, organCd, extInfoObj, pageinfos);
            errMsg = pageinfos.errMsg;
            if (!"".equals(errMsg) && errMsg != null) {
                throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("回写省网失败" + errMsg);
        }
    }

    //患者报告卡流转
    public void CallBackMoveOut(String moveCode, ExtInfo extInfoObj) {
        String errMsg = "";
        try {
            Pageinfos pageinfos = new Pageinfos();
            outerMoveOutMapper.CallBackMoveOut(moveCode, extInfoObj, pageinfos);
            errMsg = pageinfos.errMsg;
            if (!"".equals(errMsg) && errMsg != null) {
                throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("回写省网失败" + errMsg);
        }
    }


    //获取流转信息
    public MoveOutInfo getMoveOutInfo(String cd) {
        try {
            return outerMoveOutMapper.getMoveOutInfo(cd);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("获取流转信息失败");
        }
    }
    public String getMoveInfoIsGW(String cd) {
      try {
            return outerMoveOutMapper.getMoveInfoIsGW(cd);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlException("获取流转信息失败");
        }
    }
}


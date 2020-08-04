//package com.hyd.gwinterfaceserver.patrptcard.service.impl;
//
//
//import com.hyd.gwinterfaceserver.patrptcard.info.CallBackMoveInput;
//import com.hyd.gwinterfaceserver.patrptcard.info.PatReportMoveOutRequest;
//import com.hyd.gwinterfaceserver.patrptcard.mapper.MoveInAndOutService2Mapper;
//import com.hyd.gwinterfaceserver.patrptcard.service.MoveInAndOutService2Service;
//import com.hyd.gwinterfaceserver.util.GWUrl;
//import com.hyd.gwinterfaceserver.patrptcard.vo.*;
//import com.hyd.gwinterfaceserver.util.HttpUtils;
//import com.hyd.gwinterfaceserver.util.Pageinfos;
//import com.hyd.gwinterfaceserver.util.ResultInfo;
//import com.hyd.system.exceptclass.BusineExceptions;
//import com.hyd.system.exceptclass.ResultExceptions;
//import com.hyd.system.exceptclass.SqlException;
//import com.hyd.system.vo.ResultInfo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//
//@Service
//public class MoveInAndOutService2Serviceimpl implements MoveInAndOutService2Service {
//
//    @Autowired
//    MoveInAndOutService2Mapper moveInAndOutService2Mapper;
//
//
//    @Override
//    public Boolean getInZoneLv(String inZoneCd) {
//        try {
//            return moveInAndOutService2Mapper.getInZoneLv(inZoneCd) == 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new SqlException("检验迁入地区是否是区县级失败");
//        }
//    }
//
//    @Override
//    public String getOrgCode(String inOrgCd) {
//        try {
//            return moveInAndOutService2Mapper.getOrgCode(inOrgCd);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new SqlException("查询机构国网编号失败");
//        }
//    }
//
//    @Override
//    public Boolean getPatReportIsA(String inciRptCd) {
//        try {
//            return moveInAndOutService2Mapper.getPatReportIsA(inciRptCd) == 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new SqlException("判断报告卡是否存在失败");
//        }
//    }
//
//    @Override
//    public CaseReport2 getPatReportCard(String inciRptCd) {
//        try {
//            return moveInAndOutService2Mapper.getPatReportCard(inciRptCd);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new SqlException("获取患者报告卡信息失败");
//        }
//    }
//
//
//    @Override
//    public MoveOutInfo getMoveOutInfo(String cd) {
//        try {
//            return moveInAndOutService2Mapper.getMoveOutInfo(cd);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new SqlException("获取流转信息失败");
//        }
//    }
//
//    @Override
//    public String getReportCardByMoveCd(String cd) {
//        try {
//            return moveInAndOutService2Mapper.getReportCardByMoveCd(cd);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new SqlException("获取报告卡编号失败");
//        }
//    }
//
//    @Override
//    public String getMoveGWFIELDPK(String cd) {
//        try {
//            return moveInAndOutService2Mapper.getMoveGWFIELDPK(cd);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new SqlException("获取流转主键失败");
//        }
//    }
//
//
//    @Override
//    public JsonResult callBackMove(CallBackMoveInput callBackMoveInput) {
//        try {
//            String PK = "";
//            if (callBackMoveInput.isPK.isEmpty() || callBackMoveInput.isPK == null) {
//                PK = moveInAndOutService2Mapper.getPKByLeftRptCd(callBackMoveInput.moveInAndOutId);
//                if (PK.isEmpty() || PK == null) {
//                    throw new ResultExceptions("回收失败，未同步国网数据，请先同步！", 3);
//                }
//            } else {
//                if ("1".equals(callBackMoveInput.isPK)) {
//                    PK = callBackMoveInput.moveInAndOutId;
//                } else {
//                    throw new ResultExceptions("回收失败，入参isPK错误！", 3);
//                }
//            }
//            StringBuffer buffer = new StringBuffer();
//            buffer.append("{")
//                    .append("\"MoveInAndOutId\":\"").append(PK)
//                    .append("\"}");
//            ResultInfo resultInfo1 = HttpUtils.post(GWUrl.mentalhealthbusinesssynergy + "callBackMove", buffer.toString(), ResultInfo.class);
//            if (!"1".equals(resultInfo1.code)) {
//                throw new ResultExceptions("迁出接口执行未成功", 2);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new SqlException("判断报告卡是否存在失败");
//        }
//        return null;
//    }
//
//    @Override
//    public void MoveOutZB2(MoveOutReprotInfo moveOutInfo, ExtInfo extInfoObj) {
//        String errMsg = "";
//        try {
//            moveInAndOutService2Mapper.MoveOutZB2(moveOutInfo, extInfoObj);
//            errMsg = moveOutInfo.errMsg;
//            if (!"".equals(errMsg) && errMsg != null) {
//                throw new Exception();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new SqlException("回写省网失败" + errMsg);
//        }
//    }
//
//    @Override
//    public void MoveOutBJ1(MoveOutReprotInfo moveOutInfo, ExtInfo extInfoObj) {
//        String errMsg = "";
//        try {
//            moveInAndOutService2Mapper.MoveOutBJ1(moveOutInfo, extInfoObj);
//            errMsg = moveOutInfo.errMsg;
//            if (!"".equals(errMsg) && errMsg != null) {
//                throw new Exception();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new SqlException("回写省网失败" + errMsg);
//        }
//    }
//
//    @Override
//    public void RecoveryOutPat(String moveCode, String zoneCd, String organCd, ExtInfo extInfoObj) {
//        String errMsg = "";
//        try {
//            Pageinfos pageinfos = new Pageinfos();
//            moveInAndOutService2Mapper.RecoveryOutPat(moveCode, zoneCd, organCd, extInfoObj, pageinfos);
//            errMsg = pageinfos.errMsg;
//            if (!"".equals(errMsg) && errMsg != null) {
//                throw new Exception();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new SqlException("回写省网失败" + errMsg);
//        }
//    }
//
//    @Override
//    public void CallBackMoveOut(String moveCode, ExtInfo extInfoObj) {
//        String errMsg = "";
//        try {
//            Pageinfos pageinfos = new Pageinfos();
//            moveInAndOutService2Mapper.CallBackMoveOut(moveCode, extInfoObj, pageinfos);
//            errMsg = pageinfos.errMsg;
//            if (!"".equals(errMsg) && errMsg != null) {
//                throw new Exception();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new SqlException("回写省网失败" + errMsg);
//        }
//    }
//
//    @Override
//    public void PatReportMoveOut(RetValidr retValidr) {
//        //迁出  GW
////        MoveOutInfoGW moveOutInfoGW = new MoveOutInfoGW() {
////            MoveInAndOutId = "",
////            MoveInAndOutType = "2",
////            NewCaseReportId = ,
////            OoutOrgCountyCode = input.MoveOutInfo.OutZoneCd,
////            OutOrgCode = GWSyncController.getOrgCode(input.MoveOutInfo.OutOrgCd),
////            MoveOutTime = now,
////            InOrgCountyCode = input.MoveOutInfo.InZoneCd,
////            InOrgCode = InOrgCd,
////            MoveInTime = "",
////            MoveOutCause = input.MoveOutInfo.MoveOutCause,
////            SignedInformedConsentDate = reportCard.InformedDate,
////            RefuseCause = "",
////        };
//        //迁出json
//        StringBuffer buffer = new StringBuffer();
//        buffer.append("{")
//                .append("\"MoveInAndOutId\":\"").append("")
//                .append("\",\"MoveInAndOutType\":\"").append("2")
//                .append("\",\"ReportID\":\"").append(retValidr.caseReport2.newCaseReportId) //pk值
//                .append("\",\"PersonID\":\"").append(retValidr.caseReport2.basicInformationId)
//                .append("\",\"OutZoneCode\":\"").append(retValidr.patReportMoveOutRequest.getMoveOutInfo().outZoneCd)
//                .append("\",\"OutOrgCode\":\"").append(getOrgCode(retValidr.patReportMoveOutRequest.getMoveOutInfo().outZoneCd))
//                .append("\",\"MoveOutTime\":\"").append(new Date())
//                .append("\",\"InZoneCode\":\"").append(retValidr.patReportMoveOutRequest.getMoveOutInfo().inZoneCd)
//                .append("\",\"InOrgCode\":\"").append(retValidr.retProperty.inOrgCd)
//                .append("\",\"MoveInTime\":\"").append("")
//                .append("\",\"MoveOutCause\":\"").append(retValidr.patReportMoveOutRequest.getMoveOutInfo().moveOutCause)
//                .append("\",\"SignedInformedConsentDate\":\"").append(retValidr.caseReport2.informedDate)
//                .append("\",\"RefuseCause\":\"").append("")
//                .append("\"}");
//        //迁出
//        ResultInfo resultInfo = HttpUtils.post(GWUrl.mentalhealthbusinesssynergy + "moveOutInformation", buffer.toString(), ResultInfo.class);
//        if (resultInfo.code == "-1") {
//            throw new SqlException("迁出失败" + resultInfo.message);
//        }
//        try {
//            //回写省网
//            MoveOutReprotInfo moveOutInfo = new MoveOutReprotInfo();
//            moveOutInfo.cd = retValidr.caseReport2.iD;
//            moveOutInfo.zoneCd = retValidr.patReportMoveOutRequest.getMoveOutInfo().inZoneCd;
//            moveOutInfo.organCd = retValidr.patReportMoveOutRequest.getMoveOutInfo().inOrgCd;
//            moveOutInfo.iDCode = retValidr.caseReport2.iDCode;
//            moveOutInfo.signedDate = retValidr.patReportMoveOutRequest.getMoveOutInfo().signedDate;
//            moveOutInfo.outZoneCd = retValidr.patReportMoveOutRequest.getMoveOutInfo().outZoneCd;
//            moveOutInfo.outOrganCd = retValidr.patReportMoveOutRequest.getMoveOutInfo().outOrgCd;
//            moveOutInfo.fIELDPK = resultInfo.data.iD;
//            MoveOutZB2(moveOutInfo, retValidr.patReportMoveOutRequest.getExtInfoObj());
////            GWSyncController.MoveOutZB2(moveOutInfo, input.ExtInfoObj);
//        } catch (Exception e) {
//            //报告卡迁出收回
//            StringBuffer buffer1 = new StringBuffer();
//            buffer.append("{")
//                    .append("\"MoveInAndOutId\":\"").append(resultInfo.data.iD)
//                    .append("\"}");
//            HttpUtils.post(GWUrl.mentalhealthbusinesssynergy + "callBackMove", buffer1.toString(), ResultInfo.class);
//            throw new SqlException("回写省网失败");
//        }
//    }
//
//    @Override
//    public void PatReportDistributionBJ(PatReportMoveOutRequest patReportMoveOutRequest) {
//
//    }
//
//    @Override
//    public void PatReportMoveOutBJ(RetValidr retValidr)
//    {
//        //迁出  GW
////        MoveOutInfoGW MoveOutInfoGW = new MoveOutInfoGW()
////        {
////            MoveInAndOutId = "",
////            MoveInAndOutType = "2",
////            NewCaseReportId = reportCard.NewCaseReportId,
////            OoutOrgCountyCode = input.MoveOutInfo.OutZoneCd,
////            OutOrgCode = GWSyncController.getOrgCode(input.MoveOutInfo.OutOrgCd),
////            MoveOutTime = now,
////            InOrgCountyCode = input.MoveOutInfo.InZoneCd,
////            InOrgCode = InOrgCd,
////            MoveInTime = "",
////            MoveOutCause = input.MoveOutInfo.MoveOutCause,
////            SignedInformedConsentDate = reportCard.InformedDate,
////            RefuseCause = "",
////        };
//        //迁出json
//        StringBuffer buffer = new StringBuffer();
//        buffer.append("{")
//                .append("\"MoveInAndOutId\":\"").append("")
//                .append("\",\"MoveInAndOutType\":\"").append("2")
//                .append("\",\"ReportID\":\"").append(retValidr.caseReport2.newCaseReportId) //pk值
//                .append("\",\"PersonID\":\"").append(retValidr.caseReport2.basicInformationId)
//                .append("\",\"OutZoneCode\":\"").append(retValidr.patReportMoveOutRequest.getMoveOutInfo().outZoneCd)
//                .append("\",\"OutOrgCode\":\"").append(getOrgCode(retValidr.patReportMoveOutRequest.getMoveOutInfo().outZoneCd))
//                .append("\",\"MoveOutTime\":\"").append(new Date())
//                .append("\",\"InZoneCode\":\"").append(retValidr.patReportMoveOutRequest.getMoveOutInfo().inZoneCd)
//                .append("\",\"InOrgCode\":\"").append(retValidr.retProperty.inOrgCd)
//                .append("\",\"MoveInTime\":\"").append("")
//                .append("\",\"MoveOutCause\":\"").append(retValidr.patReportMoveOutRequest.getMoveOutInfo().moveOutCause)
//                .append("\",\"SignedInformedConsentDate\":\"").append(retValidr.caseReport2.informedDate)
//                .append("\",\"RefuseCause\":\"").append("")
//                .append("\"}");
//        //迁出
//        ResultInfo resultInfo = HttpUtils.post(GWUrl.mentalhealthbusinesssynergy + "moveOutInformation", buffer.toString(), ResultInfo.class);
//        if (resultInfo.code == "-1") {
//            throw new SqlException("迁出失败" + resultInfo.message);
//        }
//        try {
//            //回写省网
//            MoveOutReprotInfo moveOutInfo = new MoveOutReprotInfo();
//            moveOutInfo.cd = retValidr.caseReport2.iD;
//            moveOutInfo.zoneCd = retValidr.patReportMoveOutRequest.getMoveOutInfo().inZoneCd;
//            moveOutInfo.organCd = retValidr.patReportMoveOutRequest.getMoveOutInfo().inOrgCd;
//            moveOutInfo.moveOutCause=retValidr.patReportMoveOutRequest.getMoveOutInfo().moveOutCause;
//            moveOutInfo.fIELDPK = resultInfo.data.iD;
//            MoveOutBJ1(moveOutInfo, retValidr.patReportMoveOutRequest.getExtInfoObj());
////            GWSyncController.MoveOutBJ1(moveOutInfo, input.ExtInfoObj);
//        } catch (Exception e) {
//            //报告卡迁出收回
//            StringBuffer buffer1 = new StringBuffer();
//            buffer.append("{")
//                    .append("\"MoveInAndOutId\":\"").append(resultInfo.data.iD)
//                    .append("\"}");
//            HttpUtils.post(GWUrl.mentalhealthbusinesssynergy + "callBackMove", buffer1.toString(), ResultInfo.class);
//            throw new SqlException("回写省网失败");
//        }
//    }
//
//}

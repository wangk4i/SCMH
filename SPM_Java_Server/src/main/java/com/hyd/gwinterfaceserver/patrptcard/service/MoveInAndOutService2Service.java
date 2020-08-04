//package com.hyd.gwinterfaceserver.patrptcard.service;
//
//
//import com.hyd.gwinterfaceserver.patrptcard.info.CallBackMoveInput;
//import com.hyd.gwinterfaceserver.patrptcard.info.PatReportMoveOutRequest;
//import com.hyd.gwinterfaceserver.patrptcard.vo.*;
//import org.springframework.stereotype.Service;
//
//@Service
//public interface MoveInAndOutService2Service {
//
//
//    //检验迁入地区是否是区县级
//    Boolean getInZoneLv(String inZoneCd);
//
//    //获取机构国网编号
//    String getOrgCode(String inOrgCd);
//
//    //患者报告卡是否存在
//    Boolean getPatReportIsA(String inciRptCd);
//
//    //获取患者报告卡信息
//    CaseReport2 getPatReportCard(String inciRptCd);
//
//    //获取流转信息
//    MoveOutInfo getMoveOutInfo(String cd);
//
//    //通过流转编号获取报告卡编号
//    String getReportCardByMoveCd(String cd);
//
//    //获取流转信息主键
//    String getMoveGWFIELDPK(String cd);
//
//    //迁出回收方法
//    JsonResult callBackMove(CallBackMoveInput callBackMoveInput);
//
//    //迁出患者报告卡（省网回写）直报2
//    void MoveOutZB2(MoveOutReprotInfo moveOutInfo, ExtInfo extInfoObj);
//
//    //迁出患者报告卡（省网回写）本级1
//    void MoveOutBJ1(MoveOutReprotInfo moveOutInfo, ExtInfo extInfoObj);
//
//    //收回患者信息(省网回写)
//    void RecoveryOutPat(String moveCode, String o, String o1, ExtInfo extInfoObj);
//
//    //患者报告卡流转
//    void CallBackMoveOut(String moveCode, ExtInfo extInfoObj);
//
//
//    //患者报告卡迁出外省(直报2)
//    void PatReportMoveOut(RetValidr retValidr);
//
//    //患者报告卡外省迁入后分配至社区(本级1)
//    void PatReportDistributionBJ(PatReportMoveOutRequest patReportMoveOutRequest);
//
//    //患者报告卡迁出外省(本级1)
//    void PatReportMoveOutBJ(RetValidr retValidr);
//
//}

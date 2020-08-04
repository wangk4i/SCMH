package com.hyd.gwinterfaceserver.patrptcard.mapper;


import com.hyd.gwinterfaceserver.patrptcard.vo.*;
import com.hyd.gwinterfaceserver.util.Pageinfos;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

@Mapper
public interface OuterMoveOutMapper {
    //判断是否为区县
    @Select("select count(1) from SPM_SPMZone(nolock) where Cd=#{cd} LevCd='RegLevel003' ")
    Long getInZoneLv(String cd);

//
//    //判断报告卡存在
//    @Select("select count(1) from SPM_SPMPatInciRpt(nolock) where  Cd=#{inciRptCd} ")
//    Long getPatReportIsA(String inciRptCd);

    //获取国网编号
    @Select("select OrgCode from SPM_SPMOrgan(nolock) where Cd = #{inOrgCd}  ")
    String getOrgCode(String inOrgCd);

    //患者报告卡信息
    @Select("select * from V_Center_CaseReport2(nolock) where ID= #{inciRptCd} ")
    CaseReport2 getPatReportCard(String inciRptCd);




    @Select("select InciRptCd from SPM_SPMPatMoveOut(nolock) where Cd= #{cd} ")
    String getReportCardByMoveCd(String cd);




    @Select("select FIELDPK from SPM_SPMPatMoveOut where Cd=#{cd} ")
    String getPKByLeftRptCd(String cd);

    @Select("{call MoveInOutService2_MoveOutZB2( "+
            "#{ExtInfo.from,mode=IN,jdbcType=VARCHAR}," +
            "#{ExtInfo.to,mode=IN,jdbcType=VARCHAR}," +
            "#{ExtInfo.state,mode=IN,jdbcType=VARCHAR}," +
            "#{ExtInfo.psnCd,mode=IN,jdbcType=VARCHAR}," +
            "#{ExtInfo.organCode,mode=IN,jdbcType=VARCHAR}," +
            "#{MoveOutReprotInfo.totalNum,mode=OUT,jdbcType=INTEGER}," +
            "#{MoveOutReprotInfo.errMsg,mode=OUT,jdbcType=VARCHAR}," +
            "#{MoveOutReprotInfo.cd,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutReprotInfo.zoneCd,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutReprotInfo.organCd,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutReprotInfo.iDCode,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutReprotInfo.signedDate,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutReprotInfo.outZoneCd,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutReprotInfo.outOrganCd,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutReprotInfo.fIELDPK,mode=IN,jdbcType=VARCHAR},"+
            "'','')}")
    @Options(statementType= StatementType.CALLABLE)
    void MoveOutZB2(@Param("MoveOutReprotInfo") MoveOutReprotInfo moveOutInfo,@Param("ExtInfo") ExtInfo extInfoObj);


    @Select("select * from SPM_SPMPatMoveOut(nolock) where Cd= #{cd} ")
    MoveOutInfo getMoveOutInfo(String cd);

    @Select("{call MoveInOutService2_MoveOutBJ1( "+
            "#{ExtInfo.from,mode=IN,jdbcType=VARCHAR}," +
            "#{ExtInfo.to,mode=IN,jdbcType=VARCHAR}," +
            "#{ExtInfo.state,mode=IN,jdbcType=VARCHAR}," +
            "#{ExtInfo.psnCd,mode=IN,jdbcType=VARCHAR}," +
            "#{ExtInfo.organCode,mode=IN,jdbcType=VARCHAR}," +
            "#{MoveOutReprotInfo.totalNum,mode=OUT,jdbcType=INTEGER}," +
            "#{MoveOutReprotInfo.errMsg,mode=OUT,jdbcType=VARCHAR}," +
            "#{MoveOutReprotInfo.cd,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutReprotInfo.zoneCd,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutReprotInfo.organCd,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutReprotInfo.moveOutCause,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutReprotInfo.fIELDPK,mode=IN,jdbcType=VARCHAR})")
    @Options(statementType= StatementType.CALLABLE)
    void MoveOutBJ1(@Param("MoveOutReprotInfo") MoveOutReprotInfo moveOutInfo,@Param("ExtInfo") ExtInfo extInfoObj);


    @Select("{call QM_PatManageService_RecoveryOutPat(" +
            "#{ExtInfo.from,mode=IN,jdbcType=VARCHAR}," +
            "#{ExtInfo.to,mode=IN,jdbcType=VARCHAR}," +
            "#{ExtInfo.state,mode=IN,jdbcType=VARCHAR}," +
            "#{ExtInfo.psnCd,mode=IN,jdbcType=VARCHAR}," +
            "#{ExtInfo.organCode,mode=IN,jdbcType=VARCHAR}," +
            "#{Pageinfos.totalNum,mode=OUT,jdbcType=INTEGER}," +
            "#{Pageinfos.errMsg,mode=OUT,jdbcType=VARCHAR}," +
            "#{moveCode,mode=IN,jdbcType=VARCHAR},"+
            "#{zoneCd.zoneCd,mode=IN,jdbcType=VARCHAR},"+
            "#{organCd,mode=IN,jdbcType=VARCHAR},"+
            ")}")
    @Options(statementType= StatementType.CALLABLE)
    void RecoveryOutPat(String moveCode, String zoneCd, String organCd, @Param("ExtInfo") ExtInfo extInfoObj,@Param("Pageinfos") Pageinfos pageinfos);

    @Select("{call MoveInOutService2_CallBackMoveOut(" +
            "#{ExtInfo.from,mode=IN,jdbcType=VARCHAR}," +
            "#{ExtInfo.to,mode=IN,jdbcType=VARCHAR}," +
            "#{ExtInfo.state,mode=IN,jdbcType=VARCHAR}," +
            "#{ExtInfo.psnCd,mode=IN,jdbcType=VARCHAR}," +
            "#{ExtInfo.organCode,mode=IN,jdbcType=VARCHAR}," +
            "#{Pageinfos.totalNum,mode=OUT,jdbcType=INTEGER}," +
            "#{Pageinfos.errMsg,mode=OUT,jdbcType=VARCHAR}," +
            "#{moveCode,mode=IN,jdbcType=VARCHAR},"+
            ")}")
    @Options(statementType= StatementType.CALLABLE)
    void CallBackMoveOut(String moveCode,@Param("ExtInfo") ExtInfo extInfoObj,@Param("Pageinfos") Pageinfos pageinfos);

//    @Select("SELECT count(1) FROM SPM_SPMPatMoveOut  WHERE MoveStatusCd='FlowState001' and InciRptCd=#{inciRptCd}")
//    Long isRoam(String inciRptCd);


    @Select("select R.FIELDPK,R.SyncStatus,R.SyncError,M.MoveStatusCd from SPM_SPMPatInciRpt R left join  SPM_SPMPatMoveOut M ON R.FIELDPK=M.InciRptCd  where R.Cd=#{inciRptCd}")
    RptCardJudgeVO getRptCardJudgeVO(String inciRptCd);


    @Select("select FIELDPK from SPM_SPMPatMoveOut(nolock) where InciRptCd = (select InciRptCd from  SPM_SPMPatMoveOut(nolock) where Cd = #{cd}) and len(isnull(FIELDPK,'')) > 0 ")
    String getMoveInfoIsGW(String cd);

//    @Select("{call PatManageService_ViewPatInfo2(" +
//            "#{CommunityOutPat.limit,mode=IN,jdbcType=VARCHAR}," +
//            "#{CommunityOutPat.limit,mode=IN,jdbcType=VARCHAR}," +
//            "1," +
//            "#{CommunityOutPat.extInfoObj.psnCd,mode=IN,jdbcType=VARCHAR}," +
//            "#{CommunityOutPat.extInfoObj.organCode,mode=IN,jdbcType=VARCHAR}," +
//            "#{CommunityOutPat.count,mode=OUT,jdbcType=INTEGER}," +
//            "#{CommunityOutPat.errMsg,mode=OUT,jdbcType=VARCHAR}," +
//            "#{CommunityOutPat.code,mode=IN,jdbcType=VARCHAR}" +
//            ")}"
//    )
//    @Options(statementType= StatementType.CALLABLE)
//    ViewPat viewOutPat(@Param("CommunityOutPat") QueryCommunityOutPatinfo queryCommunityOutPatinfo);
}



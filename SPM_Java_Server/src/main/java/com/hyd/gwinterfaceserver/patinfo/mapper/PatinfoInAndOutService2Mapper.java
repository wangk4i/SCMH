package com.hyd.gwinterfaceserver.patinfo.mapper;


import com.hyd.gwinterfaceserver.patinfo.info.ApplyRepeatInfoRequest;
import com.hyd.gwinterfaceserver.patinfo.info.RepeatCallBackApplyRequest;
import com.hyd.gwinterfaceserver.patinfo.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

@Mapper
public interface PatinfoInAndOutService2Mapper {


    @Select("select Cd,Nam from SPM_SPMOrgan(nolock) where OrgCode=#{orgcode}")
    basicInfo GetOrgan(String orgcode);

    @Select("select Cd,Nam,AllNam from SPM_SPMZone(nolock) where Code =#{zonecode}")
    basicInfo GetZone(String zonecode);

    //判断是否为区县
    @Select("select count(1) from SPM_SPMZone(nolock) where Cd=#{cd} LevCd='RegLevel003' ")
    Long getInZoneLv(String cd);

    //判断是否为区县
    @Select("select count(1) from SPM_SPMPatInfoMana(nolock) where Cd=#{patCode} ")
    Long getPatInfoIsA(String patCode);



    //获取国网编号
    @Select("select OrgCode from SPM_SPMOrgan(nolock) where Cd = #{inOrgCd}  ")
    String getOrgCode(String inOrgCd);

    //获取患者信息
    @Select("select FIELDPK,SyncStatus,SyncTime,SyncError,ICSignDate from SPM_SPMPatInfoMana(nolock) where Cd=#{cd}")
    PatInfoIsSyn getPatInfoIsSync(String cd);

    //获取流转信息
    @Select("select * from SPM_SPMPatMoveOut(nolock) where Cd=#{moveCode}")
    MoveOutInfo getMoveOutInfo(String moveCode);

    //获取患者信息
    @Select("select * from V_Center_BasicInfo2(nolock) where ID=#{patInfoCd}")
    PatInfo2 getPatInfo(String patInfoCd);

    //查询该患者是否有流转记录未终结
    @Select("select count(1) from SPM_RepeatPat(nolock) where DelStatus='DelLogo001' and ApplyState = '已申请' and IDCode = #{idCode}")
    Long getRepeattMoveOutInfoByIDCode(String idCode);

    //获取患者信息
    @Select("select * from SPM_SPMPatInfoMana(nolock) where IDCode=#{idCode}")
    PatInfoMana getPatInfoByIDCode(String idCode);

    //查询该患者是否有流转记录未终结
    @Select("select count(1) from SPM_SPMPatMoveOut(nolock) where DelStatus='DelLogo001' and MoveStatusCd = 'FlowState001' and PatInfoCd=#{cd}")
    Long getMoveOutInfoByPatInfoCd(String cd);

    @Select("update SPM_RepeatPat with (rowlock) set DelStatus='DelLogo002',LastModiCd=#{repeatCallBackApplyRequest.extInfoObj.psnCd},LastModTime=convert(varchar(50),getdate(),120) where Cd =#{repeatCallBackApplyRequest.repeatInfoId}")
    void CallBackApply(@Param("RepeatCallBackApplyRequest") RepeatCallBackApplyRequest repeatCallBackApplyRequest);


    //获取重复患者申请记录
    @Select("select a.*,h.Nam LivingAddressAttributionName,i.Nam RelationToPatientName,b.Nam GenderName,c.Nam DiagnosisName,d.Nam ApplyZoneName,e.Nam ApplyOrganName,f.Nam AuditZoneName,g.Nam AuditOrganName from SPM_RepeatPat(nolock) a " +
            " left join SPM_SPMDict b on a.GenderCode = b.Cd " +
            " left join SPM_SPMDict c on a.Diagnosis = c.Cd " +
            " left join SPM_SPMZone d on a.ApplyZone = d.Cd " +
            " left join SPM_SPMOrgan e on a.ApplyOrgan = e.Cd " +
            " left join SPM_SPMZone f on a.AuditZone = f.Cd " +
            " left join SPM_SPMOrgan g on a.AuditOrgan = g.Cd " +
            " left join SPM_SPMDict h on a.LivingAddressAttributionCode = h.Cd " +
            " left join SPM_SPMDict i on a.RelationToPatient = i.Cd " +
            " where a.Cd =#{repeatInfoId}")
    QueryRepeatInfoApplyResponse GetRepeatInfo(String repeatInfoId);

    //获取患者信息
    @Select("select * from SPM_SPMPatInfoMana(nolock) where FIELDPK=#{dischargeInformationId}")
    PatInfoMana getPatInfoByFIELDPK(String dischargeInformationId);

    //重复患者通过回写
    @Select("update SPM_RepeatPat with (rowlock) set ApplyState='申请通过',MoveCode=#{ApplyRepeatInfoRequest.moveCode},LastModiCd=#{ApplyRepeatInfoRequest.extInfoObj.psnCd},LastModTime=convert(varchar(50),getdate(),120) where Cd = #{ApplyRepeatInfoRequest.repeatInfoId}")
    void ThroughApply(@Param("ApplyRepeatInfoRequest") ApplyRepeatInfoRequest applyRepeatInfoRequest);

    //重复患者通过回写患者信息
    @Select("update SPM_SPMPatInfoMana with (rowlock) set ZoneCd=#{MoveOutInfo.InZoneCd},ZoneNam=#{MoveOutInfo.inZoneNam},OrganCd=#{MoveOutInfo.inOrgCd},OrgNam=#{MoveOutInfo.inOrgNam} where Cd=#{MoveOutInfo.patInfoCd}")
    void ThroughApplyPatInfo(@Param("MoveOutInfo") MoveOutInfo moveInfo);

    //重复患者驳回回写
    @Select("update SPM_RepeatPat with (rowlock) set ApplyState='申请驳回',LastModiCd=#{ApplyRepeatInfoRequest.extInfoObj.psnCd},LastModTime=convert(varchar(50),getdate(),120) where Cd = #{ApplyRepeatInfoRequest.repeatInfoId}")
    void RejectedApply(@Param("ApplyRepeatInfoRequest") ApplyRepeatInfoRequest applyRepeatInfoRequest);

    //
    @Select("select * from V_Center_FollowupInfo2(nolock) where PatInfoCd=#{patInfoCd} and DelStatus = 'DelLogo001'")
//    @Select("select * from SPM_SPMPatFollowup(nolock) where PatInfoCd=#{patInfoCd} and DelStatus ='DelLogo001'")
    List<Followup2> getPatFollow(String patInfoCd);

    @Select("{call QM_PatManageService_PatInfoMoveOut( "+
            "#{ExtInfo.from,mode=IN,jdbcType=VARCHAR}," +
            "#{ExtInfo.to,mode=IN,jdbcType=VARCHAR}," +
            "#{ExtInfo.state,mode=IN,jdbcType=VARCHAR}," +
            "#{ExtInfo.psnCd,mode=IN,jdbcType=VARCHAR}," +
            "#{ExtInfo.organCode,mode=IN,jdbcType=VARCHAR}," +
            "#{MoveOutInfo.totalNum,mode=OUT,jdbcType=INTEGER}," +
            "#{MoveOutInfo.errMsg,mode=OUT,jdbcType=VARCHAR}," +
            "#{MoveOutInfo.cd,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.moveOutCd,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.moveOutCdNam,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.nam,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.patLeftRptCd,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.patInfoCd,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.outZoneCd,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.outZoneNam,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.outOrgCd,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.outOrgNam,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.outDate,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.inZoneCd,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.inZoneNam,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.inOrgCd,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.inOrgNam,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.nam,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.inDate,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.moveOutCause,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.signedDate,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.refuseCause,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.moveStatusCd,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.moveStatusCdNam,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.delStatus,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.delStatusNam,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.upZoneCd,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.upZoneNam,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.upState,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.isToArea,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.refuseToArea,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.ifResponse,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.responseDate,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.sourceType,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.fIELDPK,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.syncStatus,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.fromSrc,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.outCd,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.creaCd,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.creTime,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.lastModiCd,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.lastModTime,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.state,mode=IN,jdbcType=VARCHAR})}")
    @Options(statementType= StatementType.CALLABLE)
    void PatInfoMoveOut(@Param("MoveOutInfo") MoveOutInfo moveOutInfo,@Param("ExtInfo") ExtInfo extInfoObj);


    @Select("{call QM_MoveInOutService_UpdatePatInfoOutRefuse( "+
            "#{ExtInfo.from,mode=IN,jdbcType=VARCHAR}," +
            "#{ExtInfo.to,mode=IN,jdbcType=VARCHAR}," +
            "#{ExtInfo.state,mode=IN,jdbcType=VARCHAR}," +
            "#{ExtInfo.psnCd,mode=IN,jdbcType=VARCHAR}," +
            "#{ExtInfo.organCode,mode=IN,jdbcType=VARCHAR}," +
            "#{MoveOutInfo.totalNum,mode=OUT,jdbcType=INTEGER}," +
            "#{MoveOutInfo.errMsg,mode=OUT,jdbcType=VARCHAR}," +
            "#{MoveOutInfo.cd,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.InZoneCd,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.InOrgCd,mode=IN,jdbcType=VARCHAR},"+
            ","+
            "#{MoveOutInfo.MoveOutCause,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.OutZoneCd,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.OutOrgCd,mode=IN,jdbcType=VARCHAR},"+
            "#{MoveOutInfo.FIELDPK,mode=IN,jdbcType=VARCHAR})}")
    @Options(statementType= StatementType.CALLABLE)
    void UpdatePatInfoOutRefuse(@Param("MoveOutInfo") MoveOutInfo moveOutInfo,@Param("ExtInfo") ExtInfo extInfoObj);

}



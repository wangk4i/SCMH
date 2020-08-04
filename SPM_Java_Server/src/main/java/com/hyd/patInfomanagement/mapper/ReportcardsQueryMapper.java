package com.hyd.patInfomanagement.mapper;


import com.hyd.patInfomanagement.info.ReportcardsQueryinfo;
import com.hyd.patInfomanagement.vo.*;
import com.hyd.system.info.ExtInfoObj;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

@Mapper
public interface ReportcardsQueryMapper {

//    @Select("<script> select Cd Code,Nam Name from SPM_SPMZone where    State='1' " +
//
//            "<choose> " +
//            "<when test='ReportcardsQueryinfo.expcd!=\"\" and ReportcardsQueryinfo.expcd!=null ' >   and Code=#{ReportcardsQueryinfo.extInfoObj.depCd} </when>" +
//            "<otherwise> and ParCd=#{ReportcardsQueryinfo.code} and LevCd=#{ReportcardsQueryinfo.level}  </otherwise> " +
//            "</choose>" +
//
//            "</script>")
//    List<Region> initProvince(@Param("ReportcardsQueryinfo") ReportcardsQueryinfo reportcardsQueryinfo);
//
//
//    @Select(" select Cd Code,Nam Name from  SPM_SPMZone  where Code=(select ParCd from SPM_SPMZone where Cd=#{ExtInfoObj.depCd}) AND LevCd=#{ExtInfoObj.expcd} ")
//    Region getParentZone(@Param("ExtInfoObj") ExtInfoObj extInfoObj);


//    //获取机构
//    @Select("<script>" +
//
//            "select Cd,Nam from SPM_SPMOrgan" +
//
//            " where  ZoneCd like CONCAT(#{ReportcardsQueryinfo.code},\'%\')  and IsVillage is null and State='1' " +
//
//            "<choose> " +
//            " <when test='ReportcardsQueryinfo.level==\"Province\" '> and LevCd='InsLevel002'  </when>" +
//            " <when test='ReportcardsQueryinfo.level==\"City\" '> and LevCd='InsLevel003'  </when>" +
//            "</choose>" +
//
//            "</script>")
//    List<Organ> initOrgan(@Param("ReportcardsQueryinfo") ReportcardsQueryinfo reportcardsQueryinfo);


    //获取流转记录
    @Select(" select " +
            "P.refuseCause,"+
            "P.moveStatusCd,"+
            "P.inciRptCd cd,"+
            "P.outZoneNam," +
            "P.outOrgNam," +
            "P.inZoneNam," +
            "P.inOrgNam," +
            "P.moveOutCause," +
            "P.ifResponse," +
            "P.responseDate," +
            "P.creTime,N.PatNam nam from SPM_SPMPatMoveOut P left join  SPM_SPMPatInciRpt N on P.InciRptCd=N.Cd where P.InciRptCd=#{ReportcardsQueryinfo.cd} and P.State=1 " +
            " Order by P.CreTime DESC  offset #{ReportcardsQueryinfo.startNum} rows fetch next #{ReportcardsQueryinfo.limit} rows only")
    List<MoveReport> moverecard(@Param("ReportcardsQueryinfo") ReportcardsQueryinfo reportcardsQueryinfo);


    //获取流转记录总条数
    @Select(" select count(1) from SPM_SPMPatMoveOut where InciRptCd=#{ReportcardsQueryinfo.cd} and State=1 and  DelStatus='DelLogo001' ")
    Integer moverecardCount(@Param("ReportcardsQueryinfo") ReportcardsQueryinfo reportcardsQueryinfo);


    @Select("{ call Query_SPM_PatInciRpt(" +
            "#{ReportcardsQueryinfo.errMsg,mode=OUT,jdbcType=VARCHAR}," +
            "#{ReportcardsQueryinfo.count,mode=OUT,jdbcType=INTEGER}," +

            "#{ReportcardsQueryinfo.zoneCd,mode=IN,jdbcType=VARCHAR}," +
            "#{ReportcardsQueryinfo.organCd,mode=IN,jdbcType=VARCHAR}," +

            "#{ReportcardsQueryinfo.patNam,mode=IN,jdbcType=VARCHAR}," +
            "#{ReportcardsQueryinfo.iDCode,mode=IN,jdbcType=VARCHAR}," +
            "#{ReportcardsQueryinfo.diagICD,mode=IN,jdbcType=VARCHAR}," +
            "#{ReportcardsQueryinfo.startNum,mode=IN,jdbcType=INTEGER}," +
            "#{ReportcardsQueryinfo.limit,mode=IN,jdbcType=INTEGER}" +
            ")}")
    @Options(statementType = StatementType.CALLABLE)
    List<ReportcardsQueryVo> reportcardList(@Param("ReportcardsQueryinfo") ReportcardsQueryinfo reportcardsQueryinfo);


    @Select("{call PatRptCardService_ViewPatRptCard(" +
            "#{ReportcardsQueryinfo.limit,mode=IN,jdbcType=VARCHAR}," +
            "#{ReportcardsQueryinfo.limit,mode=IN,jdbcType=VARCHAR}," +
            "1," +
            "#{ReportcardsQueryinfo.extInfoObj.psnCd,mode=IN,jdbcType=VARCHAR}," +
            "#{ReportcardsQueryinfo.extInfoObj.organCode,mode=IN,jdbcType=VARCHAR}," +
            "#{ReportcardsQueryinfo.count,mode=OUT,jdbcType=INTEGER}," +
            "#{ReportcardsQueryinfo.errMsg,mode=OUT,jdbcType=VARCHAR}," +
            "#{ReportcardsQueryinfo.code,mode=IN,jdbcType=VARCHAR}" +
            ")}"
    )
    @Options(statementType= StatementType.CALLABLE)
    ViewPat viewPat(@Param("ReportcardsQueryinfo") ReportcardsQueryinfo reportcardsQueryinfo);
    
    
    
    
    @Select("{call PatRptCardService_EditPatRptCardMed(" +
            "#{ReportcardsQueryinfo.limit,mode=IN,jdbcType=VARCHAR}," +
            "#{ReportcardsQueryinfo.limit,mode=IN,jdbcType=VARCHAR}," +
            "1," +
            "#{ReportcardsQueryinfo.extInfoObj.psnCd,mode=IN,jdbcType=VARCHAR}," +
            "#{ReportcardsQueryinfo.extInfoObj.organCode,mode=IN,jdbcType=VARCHAR}," +
            "#{ReportcardsQueryinfo.count,mode=OUT,jdbcType=INTEGER}," +
            "#{ReportcardsQueryinfo.errMsg,mode=OUT,jdbcType=VARCHAR}," +
            "#{ReportcardsQueryinfo.code,mode=IN,jdbcType=VARCHAR}" +
            ")}"
    )
    @Options(statementType= StatementType.CALLABLE)
    List<PatRptCardMed> patRptCardMedList(@Param("ReportcardsQueryinfo") ReportcardsQueryinfo reportcardsQueryinfo);
}

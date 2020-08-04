package com.hyd.patrptcard.mapper;


import com.hyd.patrptcard.info.PatRptCardListinfo;
import com.hyd.patrptcard.vo.MoveReport;
import com.hyd.patrptcard.vo.PatRptCardMed;
import com.hyd.patrptcard.vo.PatRptCardVo;
import com.hyd.patrptcard.vo.ViewPat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

@Mapper
public interface PatRptCardListMapper {




    
    @Select("{call PatRptCardService_QueryPatRptCard(" +
            "#{PatRptCardListinfo.startNum,mode=IN,jdbcType=VARCHAR}," +
            "#{PatRptCardListinfo.endNum,mode=IN,jdbcType=VARCHAR}," +
            "1," +
            "#{PatRptCardListinfo.extInfoObj.psnCd,mode=IN,jdbcType=VARCHAR}," +
            "#{PatRptCardListinfo.extInfoObj.organCode,mode=IN,jdbcType=VARCHAR}," +
            "#{PatRptCardListinfo.count,mode=OUT,jdbcType=INTEGER}," +
            "#{PatRptCardListinfo.errMsg,mode=OUT,jdbcType=VARCHAR}," +
            "#{PatRptCardListinfo.zoneCd,mode=IN,jdbcType=VARCHAR}," +
            "#{PatRptCardListinfo.organCd,mode=IN,jdbcType=VARCHAR}," +
            "#{PatRptCardListinfo.patNam,mode=IN,jdbcType=VARCHAR}," +
            "#{PatRptCardListinfo.patCode,mode=IN,jdbcType=VARCHAR}," +
            "#{PatRptCardListinfo.idCode,mode=IN,jdbcType=VARCHAR}," +
            "#{PatRptCardListinfo.genderCd,mode=IN,jdbcType=VARCHAR}," +
            "#{PatRptCardListinfo.diagICD,mode=IN,jdbcType=VARCHAR}," +
            "#{PatRptCardListinfo.birthDateStart,mode=IN,jdbcType=VARCHAR}," +
            "#{PatRptCardListinfo.birthDateEnd,mode=IN,jdbcType=VARCHAR}," +
            "#{PatRptCardListinfo.creDateStart,mode=IN,jdbcType=VARCHAR}," +
            "#{PatRptCardListinfo.creDateEnd,mode=IN,jdbcType=VARCHAR}" +
            ")}"
    )
    @Options(statementType= StatementType.CALLABLE)
    List<PatRptCardVo> patRptCardListZB(@Param("PatRptCardListinfo") PatRptCardListinfo patRptCardListinfo);




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
            "P.creTime,N.PatNam nam from SPM_SPMPatMoveOut P left join  SPM_SPMPatInciRpt N on P.InciRptCd=N.Cd where P.InciRptCd=#{PatRptCardListinfo.cd} and P.State=1 " +
            " Order by P.CreTime DESC  offset #{PatRptCardListinfo.startNum} rows fetch next #{PatRptCardListinfo.limit} rows only")
    List<MoveReport> moverecard(@Param("PatRptCardListinfo") PatRptCardListinfo patRptCardListinfo);


    //获取流转记录总条数
    @Select(" select count(1) from SPM_SPMPatMoveOut where InciRptCd=#{PatRptCardListinfo.cd} and State=1 and  DelStatus='DelLogo001' ")
    Integer moverecardCount(@Param("PatRptCardListinfo") PatRptCardListinfo patRptCardListinfo);


    @Select("{call PatRptCardService_ViewPatRptCard(" +
            "#{PatRptCardListinfo.limit,mode=IN,jdbcType=VARCHAR}," +
            "#{PatRptCardListinfo.limit,mode=IN,jdbcType=VARCHAR}," +
            "1," +
            "#{PatRptCardListinfo.extInfoObj.psnCd,mode=IN,jdbcType=VARCHAR}," +
            "#{PatRptCardListinfo.extInfoObj.organCode,mode=IN,jdbcType=VARCHAR}," +
            "#{PatRptCardListinfo.count,mode=OUT,jdbcType=INTEGER}," +
            "#{PatRptCardListinfo.errMsg,mode=OUT,jdbcType=VARCHAR}," +
            "#{PatRptCardListinfo.code,mode=IN,jdbcType=VARCHAR}" +
            ")}"
    )
    @Options(statementType= StatementType.CALLABLE)
    ViewPat viewPat(@Param("PatRptCardListinfo") PatRptCardListinfo patRptCardListinfo);




    @Select("{call PatRptCardService_EditPatRptCardMed(" +
            "#{PatRptCardListinfo.limit,mode=IN,jdbcType=VARCHAR}," +
            "#{PatRptCardListinfo.limit,mode=IN,jdbcType=VARCHAR}," +
            "1," +
            "#{PatRptCardListinfo.extInfoObj.psnCd,mode=IN,jdbcType=VARCHAR}," +
            "#{PatRptCardListinfo.extInfoObj.organCode,mode=IN,jdbcType=VARCHAR}," +
            "#{PatRptCardListinfo.count,mode=OUT,jdbcType=INTEGER}," +
            "#{PatRptCardListinfo.errMsg,mode=OUT,jdbcType=VARCHAR}," +
            "#{PatRptCardListinfo.code,mode=IN,jdbcType=VARCHAR}" +
            ")}"
    )
    @Options(statementType= StatementType.CALLABLE)
    List<PatRptCardMed> patRptCardMedList(@Param("PatRptCardListinfo") PatRptCardListinfo patRptCardListinfo);
    
    
}

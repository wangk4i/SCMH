package com.hyd.consmanage.mapper;


import com.hyd.consmanage.info.QueryMoveInOutBJReportinfo;
import com.hyd.consmanage.vo.*;
import com.hyd.system.info.ExtInfoObj;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

@Mapper
public interface MoveInOutBJReportMapper {

//    @Select("<script> select Cd Code,Nam Name from SPM_SPMZone where    State='1' " +
//
//            "<choose> " +
//            "<when test='MoveInOutBJReportinfo.expcd!=\"\" and MoveInOutBJReportinfo.expcd!=null ' >   and Code=#{MoveInOutBJReportinfo.extInfoObj.depCd} </when>" +
//            "<otherwise> and ParCd=#{MoveInOutBJReportinfo.code} and LevCd=#{MoveInOutBJReportinfo.level}  </otherwise> "+
//            "</choose>" +
//
//            "</script>")
//    List<Region> initProvince(@Param("MoveInOutBJReportinfo") QueryMoveInOutBJReportinfo queryMoveInOutBJReportinfo);
//
//
//    @Select(" select Cd Code,Nam Name from  SPM_SPMZone  where Code=(select ParCd from SPM_SPMZone where Cd=#{ExtInfoObj.depCd}) AND LevCd=#{ExtInfoObj.expcd} ")
//    Region getParentZone(@Param("ExtInfoObj") ExtInfoObj extInfoObj);
//
//
//
//    //获取机构
//    @Select("<script>" +
//
//            "select Cd,Nam from SPM_SPMOrgan"  +
//
//            " where  ZoneCd like CONCAT(#{QueryMoveInOutBJReportinfo.code},\'%\')  and IsVillage is null and State='1' " +
//
//            "<choose> " +
//            " <when test='QueryMoveInOutBJReportinfo.level==\"Province\" '> and LevCd='InsLevel002'  </when>" +
//            " <when test='QueryMoveInOutBJReportinfo.level==\"City\" '> and LevCd='InsLevel003'  </when>" +
//            "</choose>" +
//
//            "</script>")
//    List<Organ> initOrgan(@Param("QueryMoveInOutBJReportinfo") QueryMoveInOutBJReportinfo queryMoveInOutBJReportinfo);
    


    @Select("{call MoveInOutService2_QueryMoveInCityBJ1(" +
            "#{QueryMoveInOutBJReportinfo.startNum,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.endNum,mode=IN,jdbcType=VARCHAR}," +
            "1," +
            "#{QueryMoveInOutBJReportinfo.extInfoObj.psnCd,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.extInfoObj.organCode,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.count,mode=OUT,jdbcType=INTEGER}," +
            "#{QueryMoveInOutBJReportinfo.errMsg,mode=OUT,jdbcType=VARCHAR}," +

            "#{QueryMoveInOutBJReportinfo.zoneCd,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.organCd,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.patNam,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.inciReportNum,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.iDCode,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.genderCd,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.dischgDiagCd,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.birthDateStart,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.birthDateEnd,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.outDateStart,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.outDateEnd,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.moveStatusCd,mode=IN,jdbcType=VARCHAR}" +
            ")}"
    )
    @Options(statementType= StatementType.CALLABLE)
    List<MoveInOutBJReport> queryByInUserList(@Param("QueryMoveInOutBJReportinfo") QueryMoveInOutBJReportinfo queryMoveInOutBJReportinfo);


    @Select("{call MoveInOutService2_QueryMoveOutCityBJ1(" +
            "#{QueryMoveInOutBJReportinfo.startNum,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.endNum,mode=IN,jdbcType=VARCHAR}," +
            "1," +
            "#{QueryMoveInOutBJReportinfo.extInfoObj.psnCd,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.extInfoObj.organCode,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.count,mode=OUT,jdbcType=INTEGER}," +
            "#{QueryMoveInOutBJReportinfo.errMsg,mode=OUT,jdbcType=VARCHAR}," +

            "#{QueryMoveInOutBJReportinfo.zoneCd,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.organCd,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.patNam,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.inciReportNum,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.iDCode,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.genderCd,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.dischgDiagCd,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.birthDateStart,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.birthDateEnd,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.outDateStart,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.outDateEnd,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.moveStatusCd,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.moveOutCd,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.other1,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.other2,mode=IN,jdbcType=VARCHAR}" +
            ")}"
    )
    @Options(statementType= StatementType.CALLABLE)
    List<MoveInOutBJReport> queryByOutUserList(@Param("QueryMoveInOutBJReportinfo") QueryMoveInOutBJReportinfo queryMoveInOutBJReportinfo);
    
    
    

    @Select("{call PatRptCardService_ViewPatRptCard(" +
            "#{QueryMoveInOutBJReportinfo.limit,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.limit,mode=IN,jdbcType=VARCHAR}," +
            "1," +
            "#{QueryMoveInOutBJReportinfo.extInfoObj.psnCd,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.extInfoObj.organCode,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.count,mode=OUT,jdbcType=INTEGER}," +
            "#{QueryMoveInOutBJReportinfo.errMsg,mode=OUT,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.code,mode=IN,jdbcType=VARCHAR}" +
            ")}"
    )
    @Options(statementType= StatementType.CALLABLE)
    ViewInPat viewInPat(@Param("QueryMoveInOutBJReportinfo") QueryMoveInOutBJReportinfo queryMoveInOutBJReportinfo);



    @Select("{call PatRptCardService_ViewPatRptCard(" +
            "#{QueryMoveInOutBJReportinfo.limit,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.limit,mode=IN,jdbcType=VARCHAR}," +
            "1," +
            "#{QueryMoveInOutBJReportinfo.extInfoObj.psnCd,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.extInfoObj.organCode,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.count,mode=OUT,jdbcType=INTEGER}," +
            "#{QueryMoveInOutBJReportinfo.errMsg,mode=OUT,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.code,mode=IN,jdbcType=VARCHAR}" +
            ")}"
    )
    @Options(statementType= StatementType.CALLABLE)
    ViewOutPat viewOutPat(@Param("QueryMoveInOutBJReportinfo") QueryMoveInOutBJReportinfo queryMoveInOutBJReportinfo);




    @Select("{call PatRptCardService_EditPatRptCardMed(" +
            "#{QueryMoveInOutBJReportinfo.limit,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.limit,mode=IN,jdbcType=VARCHAR}," +
            "1," +
            "#{QueryMoveInOutBJReportinfo.extInfoObj.psnCd,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.extInfoObj.organCode,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.count,mode=OUT,jdbcType=INTEGER}," +
            "#{QueryMoveInOutBJReportinfo.errMsg,mode=OUT,jdbcType=VARCHAR}," +
            "#{QueryMoveInOutBJReportinfo.code,mode=IN,jdbcType=VARCHAR}" +
            ")}"
    )
    @Options(statementType= StatementType.CALLABLE)
    List<PatRptCardMed> patRptCardMedList(@Param("QueryMoveInOutBJReportinfo") QueryMoveInOutBJReportinfo queryMoveInOutBJReportinfo);


}



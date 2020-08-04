package com.hyd.qualitycontrol.mapper;


import com.hyd.qualitycontrol.info.NotInspectYearPatinfo;
import com.hyd.qualitycontrol.vo.NotInspectYearPat;
import com.hyd.qualitycontrol.vo.Organ;
import com.hyd.qualitycontrol.vo.Region;
import com.hyd.system.info.ExtInfoObj;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

@Mapper
public interface NotInspectYearPatMapper {

//    @Select("<script> select Cd Code,Nam Name from SPM_SPMZone where    State='1' " +
//
//            "<choose> " +
//            "<when test='NotInspectYearPatinfo.expcd!=\"\" and NotInspectYearPatinfo.expcd!=null ' >   and Code=#{NotInspectYearPatinfo.extInfoObj.depCd} </when>" +
//            "<otherwise> and ParCd=#{NotInspectYearPatinfo.code} and LevCd=#{NotInspectYearPatinfo.level}  </otherwise> "+
//            "</choose>" +
//
//            "</script>")
//    List<Region> initProvince(@Param("NotInspectYearPatinfo") NotInspectYearPatinfo notInspectYearPatinfo);
//
//
//    @Select(" select Cd Code,Nam Name from  SPM_SPMZone  where Code=(select ParCd from SPM_SPMZone where Cd=#{ExtInfoObj.depCd}) AND LevCd=#{ExtInfoObj.expcd} ")
//    Region getParentZone(@Param("ExtInfoObj") ExtInfoObj extInfoObj);
//
//    //获取机构
//
//    @Select("<script>" +
//
//            "select Cd,Nam from SPM_SPMOrgan"  +
//
//            " where  ZoneCd like CONCAT(#{NotInspectYearPatinfo.code},\'%\')  and IsVillage is null and State='1' " +
//
//            "<choose> " +
//            " <when test='NotInspectYearPatinfo.level==\"Province\" '> and LevCd='InsLevel002'  </when>" +
//            " <when test='NotInspectYearPatinfo.level==\"City\" '> and LevCd='InsLevel003'  </when>" +
//            "</choose>" +
//
//            "</script>")
//    List<Organ> initOrgan(@Param("NotInspectYearPatinfo") NotInspectYearPatinfo notInspectYearPatinfo);


    @Select("{call QM_PatManageService_QueryNotInspectYearPat(" +
            "#{NotInspectYearPatinfo.startNum,mode=IN,jdbcType=VARCHAR}," +
            "#{NotInspectYearPatinfo.endNum,mode=IN,jdbcType=VARCHAR}," +
            "1," +
            "#{NotInspectYearPatinfo.extInfoObj.psnCd,mode=IN,jdbcType=VARCHAR}," +
            "#{NotInspectYearPatinfo.extInfoObj.organCode,mode=IN,jdbcType=VARCHAR}," +

            "#{NotInspectYearPatinfo.count,mode=OUT,jdbcType=INTEGER}," +
            "#{NotInspectYearPatinfo.errMsg,mode=OUT,jdbcType=VARCHAR}," +

            "#{NotInspectYearPatinfo.zoneCd,mode=IN,jdbcType=VARCHAR}," +
            "#{NotInspectYearPatinfo.organCd,mode=IN,jdbcType=VARCHAR}," +
            "#{NotInspectYearPatinfo.baseStatusCd,mode=IN,jdbcType=VARCHAR}" +
            ")}")
    @Options(statementType= StatementType.CALLABLE)
    List<NotInspectYearPat> queryByUserList(@Param("NotInspectYearPatinfo") NotInspectYearPatinfo notInspectYearPatinfo);



}



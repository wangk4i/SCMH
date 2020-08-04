package com.hyd.consmanage.mapper;


import com.hyd.consmanage.info.QueryCommunityOutPatinfo;
import com.hyd.consmanage.vo.CommunityOutPat;
import com.hyd.consmanage.vo.Organ;
import com.hyd.consmanage.vo.Region;
import com.hyd.consmanage.vo.ViewPat;
import com.hyd.system.info.ExtInfoObj;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

@Mapper
public interface CommunityOutPatMapper {

//    @Select("<script> select Cd Code,Nam Name from SPM_SPMZone where    State='1' " +
//
//            "<choose> " +
//            "<when test='CommunityOutPatinfo.expcd!=\"\" and CommunityOutPatinfo.expcd!=null ' >   and Code=#{CommunityOutPatinfo.extInfoObj.depCd} </when>" +
//            "<otherwise> and ParCd=#{CommunityOutPatinfo.code} and LevCd=#{CommunityOutPatinfo.level}  </otherwise> "+
//            "</choose>" +
//
//            "</script>")
//    List<Region> initProvince(@Param("CommunityOutPatinfo") QueryCommunityOutPatinfo queryCommunityOutPatinfo);
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
//            " where  ZoneCd like CONCAT(#{QueryCommunityOutPatinfo.code},\'%\')  and IsVillage is null and State='1' " +
//
//            "<choose> " +
//            " <when test='QueryCommunityOutPatinfo.level==\"Province\" '> and LevCd='InsLevel002'  </when>" +
//            " <when test='QueryCommunityOutPatinfo.level==\"City\" '> and LevCd='InsLevel003'  </when>" +
//            "</choose>" +
//
//            "</script>")
//    List<Organ> initOrgan(@Param("QueryCommunityOutPatinfo") QueryCommunityOutPatinfo queryCommunityOutPatinfo);

//    //获取列表
//    @Select("<script>" +
//            " select top (#{CommunityOutPat.limit}) * from (select ROW_NUMBER() OVER ( ORDER BY a.CreTime DESC ) AS rownum ,a.* from("+
//            " select a.Cd,b.PatNam as Name,b.GenderCdNam as Sex,b.BirthDate  as Birth,"+
//            "b.DischgDiagCdNam as CurrDia,a.InZoneNam as InToZoneNam, a.InOrgNam as InToOrgNam, a.OutDate as InToTime,"+
//            "d.Nam as IsMgrTime,b.ManageDate as MgrTime,a.RefuseCause as RefuseCause,a.InZoneCd as InToZone,a.InOrgCd as InToOrg,"+
//            "b.LAddress as CurrAddress,a.MoveOutCause as PatOutReson,"+
//            "b.IDCode  as IDCode, b.GenderCd as SexCd, a.OutZoneCd,a.OutZoneNam,a.OutOrgCd,a.OutOrgNam,"+
//            "b.DischgDiagCd  as DiagCd,a.InOrgCd,a.InZoneCd,"+
//            "a.MoveOutCd,a.State,a.DelStatus,a.MoveStatusCd,"+
//            "b.PatCode as PatCode,a.CreTime,a.IfResponse as IsResponse,"+
//            "a.InDate as RecTime,a.FromSrc,a.PatInfoCd as PatInfoCd"+
//            " from SPM_SPMPatMoveOut a left join V_SPM_SPMPatInfoMana b on a.PatInfoCd=b.Cd  left join SPM_SPMDict d on d.Cd=b.IfManageCd"+
//
//            " where  a.MoveOutCd=#{CommunityOutPat.moveOutCd}"+
//
//            " and a.Cd in (select Cd from (select pm.Cd,pm.CreTime,max(pm.CreTime) over(partition by b.IDCode) as gdtimeMax from SPM_SPMPatMoveOut pm"+
//            " left join SPM_SPMPatInfoMana po on pm.PatInfoCd=po.Cd"+
//
//            " where po.PatCode=b.PatCode  " +
//
//            "<if test='CommunityOutPat.organCd!=\"\" and CommunityOutPat.organCd!=null '> and pm.OutOrgCd=#{CommunityOutPat.organCd} </if>"+
//
//            " and pm.DelStatus='DelLogo001' " +
//
//           "<if test='CommunityOutPat.moveStatusCd==\"FlowState002\" '> and po.OrganCd!=#{CommunityOutPat.organCd} </if>"+
//
//            ")Z where Z.CreTime = gdtimeMax)) a where state='1' and DelStatus='DelLogo001' "+
//
//            "and MoveStatusCd=#{CommunityOutPat.moveStatusCd} " +
//
//           "<if test='CommunityOutPat.organCd!=\"\" and  CommunityOutPat.organCd!=null '> and  OutOrgCd=#{CommunityOutPat.organCd} </if>" +
//
//            "and OutZoneCd like CONCAT(#{CommunityOutPat.zoneCd},\'%\')" +
//
//            "<if test='CommunityOutPat.patNam!=\"\" and  CommunityOutPat.patNam!=null'> and Name like CONCAT(\'%\',#{CommunityOutPat.patNam},\'%\') </if>" +
//            "<if test='CommunityOutPat.moveOutCd!=\"\" and  CommunityOutPat.moveOutCd!=null'> and MoveOutCd=#{CommunityOutPat.moveOutCd} </if>" +
//            "<if test='CommunityOutPat.iDCode!=\"\" and  CommunityOutPat.iDCode!=null'> and IDCode=#{CommunityOutPat.iDCode} </if>" +
//            "<if test='CommunityOutPat.genderCd!=\"\" and  CommunityOutPat.genderCd!=null'> and SexCd=#{CommunityOutPat.genderCd} </if>" +
//            "<if test='CommunityOutPat.inciReportNum!=\"\" and  CommunityOutPat.inciReportNum!=null'> and PatCode=#{CommunityOutPat.inciReportNum} </if>" +
//            "<if test='CommunityOutPat.dischgDiagCd!=\"\" and  CommunityOutPat.dischgDiagCd!=null'> and DiagCd CONCAT(#{CommunityOutPat.dischgDiagCd},\'%\') </if>" +
//            "<if test='CommunityOutPat.birthDateStart!=\"\" and  CommunityOutPat.birthDateStart!=null'> and Birth &gt; #{CommunityOutPat.birthDateStart} </if>" +
//            "<if test='CommunityOutPat.birthDateEnd!=\"\" and  CommunityOutPat.birthDateEnd!=null'> and Birth &lt; #{CommunityOutPat.birthDateEnd} </if>" +
//            "<if test='CommunityOutPat.outDateStart!=\"\" and  CommunityOutPat.outDateStart!=null  and CommunityOutPat.outDateEnd!=\"\" and  CommunityOutPat.outDateEnd!=null '>  and Convert(datetime,InToTime) between #{CommunityOutPat.outDateStart} and #{CommunityOutPat.outDateEnd}  </if>"+
//            " ) g" +
//
//            " where rownum > (#{CommunityOutPat.page}-1)*#{CommunityOutPat.limit}  "+
//
//            "</script>")
//    List<CommunityOutPat> queryByUserList(@Param("CommunityOutPat") QueryCommunityOutPatinfo queryCommunityOutPatinfo);




    @Select("{call QM_PatManageService_QueryPatMoveOutInfoForBJ1(" +
            "#{CommunityOutPat.startNum,mode=IN,jdbcType=VARCHAR}," +
            "#{CommunityOutPat.endNum,mode=IN,jdbcType=VARCHAR}," +
            "1," +
            "#{CommunityOutPat.extInfoObj.psnCd,mode=IN,jdbcType=VARCHAR}," +
            "#{CommunityOutPat.extInfoObj.organCode,mode=IN,jdbcType=VARCHAR}," +
            "#{CommunityOutPat.count,mode=OUT,jdbcType=INTEGER}," +
            "#{CommunityOutPat.errMsg,mode=OUT,jdbcType=VARCHAR}," +

            "#{CommunityOutPat.zoneCd,mode=IN,jdbcType=VARCHAR}," +
            "#{CommunityOutPat.organCd,mode=IN,jdbcType=VARCHAR}," +
            "#{CommunityOutPat.patNam,mode=IN,jdbcType=VARCHAR}," +
            "#{CommunityOutPat.inciReportNum,mode=IN,jdbcType=VARCHAR}," +
            "#{CommunityOutPat.iDCode,mode=IN,jdbcType=VARCHAR}," +
            "#{CommunityOutPat.genderCd,mode=IN,jdbcType=VARCHAR}," +
            "#{CommunityOutPat.dischgDiagCd,mode=IN,jdbcType=VARCHAR}," +
            "#{CommunityOutPat.birthDateStart,mode=IN,jdbcType=VARCHAR}," +
            "#{CommunityOutPat.birthDateEnd,mode=IN,jdbcType=VARCHAR}," +
            "#{CommunityOutPat.outDateStart,mode=IN,jdbcType=VARCHAR}," +
            "#{CommunityOutPat.outDateEnd,mode=IN,jdbcType=VARCHAR}," +
            "#{CommunityOutPat.moveStatusCd,mode=IN,jdbcType=VARCHAR}," +
            "#{CommunityOutPat.moveOutCd,mode=IN,jdbcType=VARCHAR}," +
            "#{CommunityOutPat.other1,mode=IN,jdbcType=VARCHAR}," +
            "#{CommunityOutPat.other2,mode=IN,jdbcType=VARCHAR}" +
            ")}"
    )
    @Options(statementType= StatementType.CALLABLE)
    List<CommunityOutPat> queryByUserList(@Param("CommunityOutPat") QueryCommunityOutPatinfo queryCommunityOutPatinfo);


    @Select("{call PatManageService_ViewPatInfo2(" +
            "#{CommunityOutPat.limit,mode=IN,jdbcType=VARCHAR}," +
            "#{CommunityOutPat.limit,mode=IN,jdbcType=VARCHAR}," +
            "1," +
            "#{CommunityOutPat.extInfoObj.psnCd,mode=IN,jdbcType=VARCHAR}," +
            "#{CommunityOutPat.extInfoObj.organCode,mode=IN,jdbcType=VARCHAR}," +
            "#{CommunityOutPat.count,mode=OUT,jdbcType=INTEGER}," +
            "#{CommunityOutPat.errMsg,mode=OUT,jdbcType=VARCHAR}," +
            "#{CommunityOutPat.code,mode=IN,jdbcType=VARCHAR}" +
            ")}"
    )
    @Options(statementType= StatementType.CALLABLE)
    ViewPat viewOutPat(@Param("CommunityOutPat") QueryCommunityOutPatinfo queryCommunityOutPatinfo);
}



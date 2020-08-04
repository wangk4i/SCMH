package com.hyd.village.mapper;


import com.hyd.village.info.VillageUserManageinfo;
import com.hyd.village.vo.CityVO;
import com.hyd.village.vo.Organ;
import com.hyd.village.vo.ProvinceVO;
import com.hyd.village.vo.VillageUserManageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface VillageUserManageMapper {

    //获取基本信息列表
    @Select("<script>" +

            "SELECT Nam,ReportOrgNam,ReportZoneNam,Phone,CreTime"+

            " FROM Village_User a(nolock) "  +

            " where  Nam like CONCAT(\'%\',#{VillageUserManageinfo.nam},\'%\')  and   State='1' and UserStatus='正常' " +

            " and  ReportZoneCd like CONCAT(#{VillageUserManageinfo.zoneCd},\'%\') " +

            "<if test='VillageUserManageinfo.phone!=\"\" and VillageUserManageinfo.phone!=null'> and Phone=#{VillageUserManageinfo.phone} </if>"+

            "<if test='VillageUserManageinfo.organCd!=\"\" and VillageUserManageinfo.organCd!=null'> and ReportOrgCd=#{VillageUserManageinfo.organCd} </if>"+


            " order by ReportZoneCd,ReportOrgCd,CreTime desc  offset (#{VillageUserManageinfo.page}-1)*#{VillageUserManageinfo.limit}  rows fetch next #{VillageUserManageinfo.limit} rows only "+

            "</script>")
    List<VillageUserManageVo> villageUserList(@Param("VillageUserManageinfo") VillageUserManageinfo villageUserManageinfo);


   @Select ("<script> " +
           " select count(1) from Village_User " +

            " where  Nam like CONCAT(\'%\',#{VillageUserManageinfo.nam},\'%\')  and   State='1' and UserStatus='正常' " +

            " and  ReportZoneCd like CONCAT(#{VillageUserManageinfo.zoneCd},\'%\') " +

            "<if test='VillageUserManageinfo.phone!=\"\" and VillageUserManageinfo.phone!=null'> and Phone=#{VillageUserManageinfo.phone} </if>"+

            "<if test='VillageUserManageinfo.organCd!=\"\" and VillageUserManageinfo.organCd!=null'> and ReportOrgCd=#{VillageUserManageinfo.organCd} </if> " +
           " </script>")
    Integer villageUserListSize(@Param("VillageUserManageinfo") VillageUserManageinfo villageUserManageinfo);


    //查询县的父级地区
    @Select(" select Cd,Nam from  SPM_SPMZone where Cd=(select ParCd from   SPM_SPMZone where Cd=#{VillageUserManageinfo.pcd}) AND LevCd='RegLevel002'")
    CityVO queryParentZone(@Param("VillageUserManageinfo") VillageUserManageinfo villageUserManageinfo);


    //获取机构
    @Select("<script>" +

            "select Cd,Nam,LevNam,LevCd,IfPerform,IsBJ,OrgCode from SPM_SPMOrgan"  +

            " where  ZoneCd like CONCAT(#{VillageUserManageinfo.zoneCd},\'%\')  and IsVillage is null and State='1' " +

            "<if test='VillageUserManageinfo.levCd!=\"\" and VillageUserManageinfo.levCd!=null'> and LevCd=#{VillageUserManageinfo.levCd} </if>"+

            "</script>")
    List<Organ> initOrganList(@Param("VillageUserManageinfo") VillageUserManageinfo villageUserManageinfo);

    //获取省
    @Select("select Cd,Nam,OrgCd from SPM_SPMZone where Cd=#{Cd} ")
    ProvinceVO initAllProvince(@Param("Cd")String Cd);

    //获取市,县,乡镇
    @Select("select Cd,Nam,OrgCd from SPM_SPMZone where ParCd=#{VillageUserManageinfo.pcd} And LevCd=#{VillageUserManageinfo.cd} And State='1'")
    List<CityVO> initAllCity(@Param("VillageUserManageinfo")VillageUserManageinfo villageUserManageinfo);


}

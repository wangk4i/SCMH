package com.hyd.orgmaintain.mapper;

import com.hyd.orgmaintain.info.QueryOrganinfo;
import com.hyd.orgmaintain.vo.CityVo;
import com.hyd.orgmaintain.vo.OrgUser;
import com.hyd.orgmaintain.vo.Organ;
import com.hyd.orgmaintain.vo.ProvinceVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface OrgMaintainMapper {

     //获取省
   @Select("select Cd,Nam from SPM_SPMZone where Cd=#{Cd} ")
     ProvinceVO  initAllProvince(@Param("Cd")String Cd);


    //获取市 区 乡镇
    @Select("select Cd,Nam from SPM_SPMZone where ParCd=#{QueryOrganinfo.pcd} And State='1'")
    List<CityVo> foundZoneByParCode(@Param("QueryOrganinfo") QueryOrganinfo queryOrganinfo);



    //无生效用户
    @Select({"<script> " +
            " select *,0 as userTotal" +
            " from SPM_SPMOrgan spmOrgan(nolock)" +

             "<choose> " +
                 "<when test='Organ.zoneCd!=\"\"'>  where spmOrgan.ZoneCd=#{Organ.zoneCd}  <if test='Organ.townsCd!=\"\"'>    and spmOrgan.townsCd=#{Organ.townsCd}  </if>  and spmOrgan.IsVillage is null And spmOrgan.State='1'  </when>" +
                 "<when test='Organ.cityCd!=\"\"'>  where spmOrgan.ZoneCd LIKE CONCAT(#{Organ.cityCd},'%') and spmOrgan.IsVillage is null and spmOrgan.State='1' </when>" +
                 "<otherwise> where  spmOrgan.ZoneCd LIKE CONCAT(#{Organ.provinceCd},'%')  and spmOrgan.IsVillage is null and spmOrgan.State='1' </otherwise>" +
             "</choose>" +

            " <if test='Organ.nam!=\"\"'> AND Nam LIKE CONCAT(\'%\',#{Organ.nam},\'%\') </if>" +
            " <if test='Organ.levCd!=\"\"'> AND LevCd=#{Organ.levCd} </if>" +


            "<choose> " +
                  "<when test='Organ.ifPerform!=\"\" and  Organ.isBJ!=\"\" '> AND(IfPerform=#{Organ.ifPerform} OR IsBJ=#{Organ.isBJ})  </when>"+
                 "<when test='Organ.ifPerform!=\"\"'> AND IfPerform=#{Organ.ifPerform} </when>" +
                 "<when test='Organ.isBJ!=\"\"'> AND IsBJ=#{Organ.isBJ} </when>" +
            "</choose>" +

            " and  not exists(" +
            " select 1" +
            " from SPM_SPMUser u(nolock)" +
            " where u.OrganCd=spmOrgan.Cd and  u.UserStatus='未过期' and u.ActiveStatus='可用' and u.State='1' )"

            +"</script>"})
    List<Organ> queryOrganByNoAciveUser(@Param("Organ") Organ organ);


    //有无生效基层直报
    @Select({"<script> " +
            " select *," +

            "(select count(1) "+
            "from SPM_SPMUser u(nolock) "+
            "where u.OrganCd=spmOrgan.Cd and  u.UserStatus='未过期' and u.ActiveStatus='可用' and u.State='1' "+
            ") userTotal"+

            " from SPM_SPMOrgan spmOrgan(nolock)"  +

            " <choose>" +
//            " <when test='Organ.townsCd!=\"\"'> </when>"+
               " <when test='Organ.zoneCd!=\"\"'>  where spmOrgan.ZoneCd=#{Organ.zoneCd}  <if test='Organ.townsCd!=\"\"'>    and spmOrgan.townsCd=#{Organ.townsCd}  </if> and spmOrgan.IsVillage is null and spmOrgan.State='1' </when>" +
               " <when test='Organ.cityCd!=\"\"'> where spmOrgan.ZoneCd LIKE CONCAT(#{Organ.cityCd},'%') and spmOrgan.IsVillage is null and spmOrgan.State='1' </when>" +
            " <otherwise> where  spmOrgan.ZoneCd LIKE CONCAT(#{Organ.provinceCd},'%')  and spmOrgan.IsVillage is null and spmOrgan.State='1' </otherwise>" +
            " </choose>" +

//            " and  exists("+
//            " select 1 "+
//            " from SPM_SPMUser u(nolock) "+
//            " where u.OrganCd=spmOrgan.Cd and  u.UserStatus='未过期' and u.ActiveStatus='可用' and u.State='1') "+


            " <if test='Organ.nam!=\"\"'> AND Nam LIKE CONCAT(\'%\',#{Organ.nam},\'%\') </if>" +
            " <if test='Organ.levCd!=\"\"'> AND LevCd=#{Organ.levCd} </if>" +


            "<choose> " +
            "<when test='Organ.ifPerform!=\"\" and Organ.isBJ!=\"\" '> AND(IfPerform=#{Organ.ifPerform} OR IsBJ=#{Organ.isBJ})  </when>"+
            "<when test='Organ.ifPerform!=\"\"'> AND IfPerform=#{Organ.ifPerform} </when>" +
            "<when test='Organ.isBJ!=\"\"'> AND IsBJ=#{Organ.isBJ} </when>" +
            "</choose>" +

            " <if test='Organ.straight==\"ysx_zb\"'> and exists( </if> "+
            " <if test='Organ.straight==\"wsx_zb\"'> and not exists( </if> "+
            " select 1"+
            " from SPM_SPMUser u(nolock)"+
            " left join SPM_SPMUserRole ur(nolock) on ur.UserCd=u.Cd and ur.RoleCd='Role011'"+
            " where u.OrganCd=spmOrgan.Cd and  u.UserStatus='未过期' and u.ActiveStatus='可用' and u.State='1' and ur.Cd is not null )"


            +"</script>"})
    List<Organ> queryOrganByHasUserForZB1(@Param("Organ") Organ organ);


    //有无生效县本质控
    @Select({"<script> " +
         " select *," +
            "(select count(1) "+
            "from SPM_SPMUser u(nolock) "+
            "where u.OrganCd=spmOrgan.Cd and  u.UserStatus='未过期' and u.ActiveStatus='可用' and u.State='1' "+
            ") userTotal"+

            " from SPM_SPMOrgan spmOrgan(nolock)" +

         " <choose> " +
            "<when test='Organ.zoneCd!=\"\"'>  where spmOrgan.ZoneCd=#{Organ.zoneCd}  <if test='Organ.townsCd!=\"\"'>    and spmOrgan.townsCd=#{Organ.townsCd}  </if> and spmOrgan.IsVillage is null and spmOrgan.State='1' </when>" +
         " <when test='Organ.cityCd!=\"\"'> where spmOrgan.ZoneCd LIKE CONCAT(#{Organ.cityCd},'%') and spmOrgan.IsVillage is null and spmOrgan.State='1' </when>" +
         " <otherwise> where  spmOrgan.ZoneCd LIKE CONCAT(#{Organ.provinceCd},'%')  and spmOrgan.IsVillage is null and spmOrgan.State='1' </otherwise>" +
         " </choose>" +



//         " and  exists("+
//         " select 1 "+
//         " from SPM_SPMUser u(nolock) "+
//         " where u.OrganCd=spmOrgan.Cd and  u.UserStatus='未过期' and u.ActiveStatus='可用' and u.State='1' ) "+


            " <if test='Organ.nam!=\"\"'> AND Nam LIKE CONCAT(\'%\',#{Organ.nam},\'%\') </if>" +
            " <if test='Organ.levCd!=\"\"'> AND LevCd=#{Organ.levCd} </if>" +

            "<choose> " +
            "<when test='Organ.ifPerform!=\"\" and Organ.isBJ!=\"\" '> AND(IfPerform=#{Organ.ifPerform} OR IsBJ=#{Organ.isBJ})  </when>"+
            "<when test='Organ.ifPerform!=\"\"'> AND IfPerform=#{Organ.ifPerform} </when>" +
            "<when test='Organ.isBJ!=\"\"'> AND IsBJ=#{Organ.isBJ} </when>" +
            "</choose>" +

         " <if test='Organ.countyControl==\"ysx_zk\"'> and exists( </if>"+
         " <if test='Organ.countyControl==\"wsx_zk\"'> and not exists( </if>"+
         " select 1"+
         " from SPM_SPMUser u(nolock)"+
         " left join SPM_SPMUserRole ur(nolock) on ur.UserCd=u.Cd and ur.RoleCd='Role007'"+
         " where u.OrganCd=spmOrgan.Cd and  u.UserStatus='未过期' and u.ActiveStatus='可用' and u.State='1' and ur.Cd is not null )"


         +"</script>"})
    List<Organ> queryOrganByHasUserForBJ1(@Param("Organ") Organ organ);

    //所有用户情况
    @Select({"<script> " +
            " select *," +

            "(select count(1) "+
            "from SPM_SPMUser u(nolock) "+
            "where u.OrganCd=spmOrgan.Cd and  u.UserStatus='未过期' and u.ActiveStatus='可用' and u.State='1'"+
            ") userTotal"+

            " from SPM_SPMOrgan spmOrgan(nolock)" +

            " <choose> " +
            " <when test='Organ.zoneCd!=\"\"'>  where spmOrgan.ZoneCd=#{Organ.zoneCd}  <if test='Organ.townsCd!=\"\"'>    and spmOrgan.townsCd=#{Organ.townsCd}  </if>  and spmOrgan.IsVillage is null and spmOrgan.State='1' </when>" +
            " <when test='Organ.cityCd!=\"\"'> where spmOrgan.ZoneCd LIKE CONCAT(#{Organ.cityCd},'%') and spmOrgan.IsVillage is null and spmOrgan.State='1' </when>" +
            " <otherwise> where  spmOrgan.ZoneCd LIKE CONCAT(#{Organ.provinceCd},'%')  and spmOrgan.IsVillage is null and spmOrgan.State='1' </otherwise>" +
            " </choose>" +

            " <if test='Organ.nam!=\"\"'> AND Nam LIKE CONCAT(\'%\',#{Organ.nam},\'%\') </if>" +
            " <if test='Organ.levCd!=\"\"'> AND LevCd=#{Organ.levCd} </if>" +

            "<choose> " +
            "<when test='Organ.ifPerform!=\"\" and  Organ.isBJ!=\"\" '> AND(IfPerform=#{Organ.ifPerform} OR IsBJ=#{Organ.isBJ})  </when>"+
            "<when test='Organ.ifPerform!=\"\"'> AND IfPerform=#{Organ.ifPerform} </when>" +
            "<when test='Organ.isBJ!=\"\"'> AND IsBJ=#{Organ.isBJ} </when>" +
            "</choose>" +



            "</script>"})
    List<Organ> queryOrganByHasUser(@Param("Organ") Organ organ);


   //获取机构用户列表
    @Select("SELECT * FROM SPM_SPMUser U LEFT join  SPM_SPMOrgan O on O.Cd=U.OrganCd  where O.Cd=#{QueryOrganinfo.pcd} and U.State='1' ")
    List<OrgUser> queryOrganUser(@Param("QueryOrganinfo") QueryOrganinfo queryOrganinfo);

    //根据Cd获取机构
    @Select("SELECT Z.ParNam,Z.Cd cityCd,O.* FROM SPM_SPMOrgan O Left JOIN SPM_SPMZone Z  on Z.Cd=O.ZoneCd where O.Cd=#{QueryOrganinfo.pcd}")
     Organ queryOrganByCd(@Param("QueryOrganinfo")QueryOrganinfo queryOrganinfo);

  //判读机构编码重复
    @Select("SELECT count(1) FROM SPM_SPMOrgan WHERE OrgCode=#{QueryOrganinfo.pcd}")
    Long iSCodeRepetition(@Param("QueryOrganinfo") QueryOrganinfo queryOrganinfo);

 //添加机构
    @Select("  insert into SPM_SPMOrgan (" +
            "Cd,ZoneCd,ZoneCode,ZoneNam," +
            "TownsCd,Towns," +
            "Nam," +
            "LevCd,LevNam," +
            "IfPerform,IsBJ,IfDirect," +
            "OrgCode," +
            "OrgTpCd,OrgTpNam," +
            "HostCd,HostNam," +
            "EcoTpCd,EcoTpNam," +
            "ManaTpCd,ManaTpNam," +
            "Address,Phone,State,IsCenter" +
            ") VALUES(" +
             "#{Organ.cd},#{Organ.zoneCd},#{Organ.zoneCode},#{Organ.zoneNam},#{Organ.townsCd},#{Organ.towns},#{Organ.nam},#{Organ.levCd},"+
             "#{Organ.levNam},#{Organ.ifPerform},#{Organ.isBJ},#{Organ.ifPerform},#{Organ.orgCode},#{Organ.orgTpCd},#{Organ.orgTpNam},#{Organ.hostCd},#{Organ.hostNam},"+
             "#{Organ.ecoTpCd},#{Organ.ecoTpNam},#{Organ.manaTpCd},#{Organ.manaTpNam},#{Organ.address},#{Organ.phone},1,#{Organ.isBJ}"+
            ")")
    void addSPMOrgan(@Param("Organ")Organ organ);

 //修改机构
    @Select("update SPM_SPMOrgan set ZoneCd=#{Organ.zoneCd},ZoneCode=#{Organ.zoneCode},ZoneNam=#{Organ.zoneNam},TownsCd=#{Organ.townsCd},Towns=#{Organ.towns},Nam=#{Organ.nam}," +
            "LevCd=#{Organ.levCd},LevNam=#{Organ.levNam},OrgCode=#{Organ.orgCode},OrgTpCd=#{Organ.orgTpCd}," +
            "OrgTpNam=#{Organ.orgTpNam},HostCd=#{Organ.hostCd},HostNam=#{Organ.hostNam},EcoTpCd=#{Organ.ecoTpCd},EcoTpNam=#{Organ.ecoTpNam},ManaTpCd=#{Organ.manaTpCd},ManaTpNam=#{Organ.manaTpNam}," +
            "Address=#{Organ.address},Phone=#{Organ.phone}  where Cd=#{Organ.cd}")
    void updateSPMOrgan(@Param("Organ")Organ organ);
 //修改机构状态
    @Select("update SPM_SPMOrgan set State=#{QueryOrganinfo.pcd} where Cd=#{QueryOrganinfo.cd}")
    void  updateState(@Param("QueryOrganinfo") QueryOrganinfo queryOrganinfo);

    //判读县本级机构重复
    @Select("<script> select count(1) from SPM_SPMOrgan where IsBJ='1' AND ZoneCd=#{QueryOrganinfo.pcd}" +
            " <if test='QueryOrganinfo.cd!=\"\" and QueryOrganinfo.cd!=null '> AND not Cd=#{QueryOrganinfo.cd} </if>" +
            "</script>")
    Long isOrganRepetition(@Param("QueryOrganinfo")QueryOrganinfo queryOrganinfo);

    //判读县本级机构重复
    @Select(" select count(1) from SPM_SPMUser U left join SPM_SPMUserRole R on U.Cd=R.UserCd where  OrganCd=#{QueryOrganinfo.pcd} and R.RoleCd='Role007' and U.State='1' ")
    Long isOrganUserBJ(@Param("QueryOrganinfo")QueryOrganinfo queryOrganinfo);


    //判读县本级机构重复
    @Select(" select count(1) from SPM_SPMUser U left join SPM_SPMUserRole R on U.Cd=R.UserCd where  OrganCd=#{QueryOrganinfo.pcd} and R.RoleCd='Role011' and U.State='1' ")
    Long isOrganUserZB(@Param("QueryOrganinfo")QueryOrganinfo queryOrganinfo);
}

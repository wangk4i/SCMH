package com.hyd.usercontrol.mapper;



import com.hyd.system.factory.ResultFactory;
import com.hyd.usercontrol.info.OperateQueryUserinfo;
import com.hyd.usercontrol.info.QueryUserControlinfo;
import com.hyd.usercontrol.info.QueryUserinfo;
import com.hyd.usercontrol.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;


@Mapper
public interface UserControlMapper {

    //根据Cd查询用户
    @Select("select * from  SPM_SPMUser WHERE Cd=#{QueryUserControlinfo.cd} ")
    User initUserByCd(@Param("QueryUserControlinfo") QueryUserControlinfo queryUserControlinfo);

    //修改用户个人信息
    @Select("update SPM_SPMUser set Phone=#{User.phone},Email=#{User.email},Address=#{User.address} WHERE Cd=#{User.cd} ")
    void modifyinformation(@Param("User") User user);

    //获取省
    @Select("select Cd,Nam,OrgCd from SPM_SPMZone where Cd=#{Cd} ")
    ProvinceVO initAllProvince(@Param("Cd")String Cd);


    //获取机构
    @Select("<script>" +

            "select Cd,Nam,LevNam,LevCd,IfPerform,IsBJ,OrgCode from SPM_SPMOrgan"  +

            " where  ZoneCd like CONCAT(#{QueryUserControlinfo.zoneCd},\'%\')  and IsVillage is null and State='1' " +

            " <if test='QueryUserControlinfo.levCd!=\"\" and  QueryUserControlinfo.levCd!=null'> and LevCd=#{QueryUserControlinfo.levCd}  </if>" +

            "</script>")
    List<Organ> initOrganList(@Param("QueryUserControlinfo") QueryUserControlinfo queryUserControlinfo);

    //获取市,县,乡镇
    @Select("select Cd,Nam,OrgCd from SPM_SPMZone where ParCd=#{QueryUserinfo.pcd} And LevCd=#{QueryUserinfo.cd} And State='1'")
    List<ProvinceVO> foundZoneByParCode(@Param("QueryUserinfo") QueryUserinfo queryUserinfo);


    //根据Cd获取地区
    @Select("select Cd,Nam,OrgCd from SPM_SPMZone where Cd=#{QueryUserinfo.cd}  And State='1'")
    ProvinceVO getZoneByCd(@Param("QueryUserinfo")QueryUserinfo queryUserinfo);


//    //本级1 查询用户名存在account
//    @Select("select count(1) from SPM_SPMUser U left join SPM_SPMUserRole R on R.UserCd=U.Cd where  U.ZoneCode=#{Que ryUserinfo.zoneCode} and (R.RoleCd='Role001' or R.RoleCd='Role004' or R.RoleCd='Role007') and U.UserStatus='未过期' and U.ActiveStatus='可用' and U.State='1'")
//    Long isAccountbj(@Param("QueryUserinfo")QueryUserinfo queryUserinfo);

    //角色和身份证查询用户
    @Select("select count(1) from SPM_SPMUser U left join SPM_SPMUserRole  R on R.UserCd=U.Cd  where U.IDCode=#{QueryUserinfo.identity}  ANd  R.RoleCd=#{QueryUserinfo.roleCd} and U.State=1 and U.UserStatus='未过期'   and U.ActiveStatus='可用' ")
    Long isidentity(@Param("QueryUserinfo")QueryUserinfo queryUserinfo);


    //修改账号时 角色和身份证查询用户
    @Select("select count(1) from SPM_SPMUser U left join SPM_SPMUserRole  R on R.UserCd=U.Cd  where U.IDCode=#{QueryUserinfo.identity}  ANd  R.RoleCd=#{QueryUserinfo.roleCd} and U.State=1 and U.UserStatus='未过期'   and U.ActiveStatus='可用' and U.Cd!=#{QueryUserinfo.cd} ")
    Long isidentitys(@Param("QueryUserinfo")QueryUserinfo queryUserinfo);

    //查询用户名存在
    @Select("select count(1) from SPM_SPMUser where LoginAccount=#{QueryUserinfo.account} and State=1  and UserStatus='未过期'  and   ActiveStatus='可用' ")
    Long isAccount(@Param("QueryUserinfo")QueryUserinfo queryUserinfo);

    //判断县本级用户是否存在
    @Select(" select count(1) from SPM_SPMUser U left join SPM_SPMUserRole UR on U.Cd=UR.UserCd  where UR.RoleCd='Role007' and U.ZoneCd=#{QueryUserinfo.pcd} and  U.State='1'  and U.UserStatus='未过期'  and  U.ActiveStatus='可用' ")
    Long isUserRepetition(@Param("QueryUserinfo")QueryUserinfo queryUserinfo);

//    //判断该账号是否启用
//    @Select(" select count(1) from  SPM_SPMUser where Cd=#{QueryUserinfo.cd} and ActiveStatus='可用' ")
//    Long isstartusing(@Param("QueryUserinfo")QueryUserinfo queryUserinfo);


    //添加用户
    @Select("insert into SPM_SPMUser(Cd,LoginAccount,Nam,Pwd,ZoneCd,ZoneCode,ZoneNam,Duty,Phone,Email,DepartNam,Address,IDCode,Department,Userattr," +
            "StartDate,EndDate,UserStatus,ActiveStatus,CreUser,CreOrg,CreaCd,CreTime,State,OrganCd,OrgNam)" +

            "  select " +

            "  #{User.cd},#{User.loginAccount},#{User.nam},123456,#{User.zoneCd},#{User.zoneCd}," +

            "Z.AllNam," +

            "(case when CHARINDEX(\'gly\',#{User.duty})>0 then '业务管理员' when CHARINDEX(\'bj\',#{User.duty})>0  then '本级用户' when CHARINDEX(\'zb\',#{User.duty})>0  then '直报用户' else null end)," +
            "  #{User.phone},#{User.email},#{User.departNam},#{User.address},#{User.iDCode},#{User.departNam},#{User.userattr},#{User.startDate},#{User.endDate},'未过期','可用',#{User.creUser},#{User.creOrg},#{User.creUser}," +

            "  convert(varchar,getdate(),120),'1',#{User.organCd},#{User.orgNam}"+

            "  from  SPM_SPMZone Z where Z.Cd=#{User.zoneCd} ")
    void foundUser(@Param("User")User user);

    //添加权限
    @Select(" insert into SPM_SPMUserRole(Cd,UserCd,RoleCd,CreaCd,CreTime,State)" +
            "  values(#{User.userroleCd},#{User.cd},#{User.roleCd},#{User.creUser},convert(varchar,getdate(),120),'1')")
    void foundUserRole(@Param("User")User user);


    //查询用户列表
    @Select("<script>" +
            "select * from SPM_SPMUser WHERE  Nam LIKE CONCAT(\'%\',#{QueryUserinfo.nam},\'%\') and State=1  " +

            " <if test='QueryUserinfo.identity!=\"\" and  QueryUserinfo.identity!=null'> and IDCode=#{QueryUserinfo.identity}  </if>" +
            " <if test='QueryUserinfo.phone!=\"\" and  QueryUserinfo.phone!=null'> and Phone=#{QueryUserinfo.phone}  </if>" +

            "</script> ")
    List<AddUser> queryByUserList(@Param("QueryUserinfo") QueryUserinfo queryUserinfo);

    //查询县的父级地区
    @Select(" select Cd,Nam from  SPM_SPMZone where Cd=(select ParCd from   SPM_SPMZone where Cd=#{QueryUserinfo.pcd}) AND LevCd='RegLevel002'")
    ProvinceVO queryParentZone(@Param("QueryUserinfo") QueryUserinfo queryUserinfo);


    //查询操作用户列表
    @Select("<script> " +
            "Select U.UserStatus,U.IDCode,U.ZoneCd,R.Cd RoleCd,U.DeviceID,U.AppAuth,U.Cd Cd,U.ActiveStatus ActiveStatus,U.CommunityPowr CommunityPowr,R.Nam Duty,U.LoginAccount LoginAccount,U.Nam Nam,U.ZoneNam ZoneNam,U.OrgNam OrganCdNam,U.Phone Phone,U.EndDate EndDate" +
            ",R.Cd FROM SPM_SPMUser(nolock) U join SPM_SPMRole(nolock) R on R.Cd = (select UR.RoleCd from SPM_SPMUserRole(nolock) UR where UR.UserCd=U.Cd)  where " +
            " U.State='1'  and U.Nam like CONCAT(\'%\',#{OperateQueryUserinfo.nam},\'%\')  "+

            " and  U.ZoneCd like CONCAT(#{OperateQueryUserinfo.zoneCd},\'%\') " +

            "<if test='OperateQueryUserinfo.iDCode!=\"\" and  OperateQueryUserinfo.iDCode!=null'> and U.IDCode=#{OperateQueryUserinfo.iDCode}  </if>"+

            "<choose> " +
            "<when test='OperateQueryUserinfo.startDate!=\"\" and OperateQueryUserinfo.startDate!=null and OperateQueryUserinfo.endDate!=\"\" and OperateQueryUserinfo.endDate!=null'> and U.CreTime between #{OperateQueryUserinfo.startDate} and #{OperateQueryUserinfo.endDate} </when>" +
            "<when test='OperateQueryUserinfo.startDate!=\"\" and OperateQueryUserinfo.startDate!=null'> and U.CreTime &gt; #{OperateQueryUserinfo.startDate} </when>" +
            "<when test='OperateQueryUserinfo.endDate!=\"\" and OperateQueryUserinfo.endDate!=null'> and U.CreTime &lt; #{OperateQueryUserinfo.endDate} </when>" +
            "</choose>" +

            "<if test='OperateQueryUserinfo.creTime!=\"\" and  OperateQueryUserinfo.creTime!=null'> and DATEDIFF(DAY,CONVERT(varchar(50),getdate(),23),U.EndDate)=#{OperateQueryUserinfo.creTime}   </if>"+


            "<if test='OperateQueryUserinfo.activeStatus!=\"\" and  OperateQueryUserinfo.activeStatus!=null'> and U.ActiveStatus=#{OperateQueryUserinfo.activeStatus}  </if>"+
            "<if test='OperateQueryUserinfo.loginAccount!=\"\" and  OperateQueryUserinfo.loginAccount!=null'> and U.LoginAccount=#{OperateQueryUserinfo.loginAccount} </if>"+
            "<if test='OperateQueryUserinfo.organCd!=\"\" and  OperateQueryUserinfo.organCd!=null'> and U.OrganCd=#{OperateQueryUserinfo.organCd} </if>"+
            "<if test='OperateQueryUserinfo.userStatus!=\"\" and  OperateQueryUserinfo.userStatus!=null'> and U.UserStatus=#{OperateQueryUserinfo.userStatus} </if>"+

            "<if test='OperateQueryUserinfo.duty!=\"\" and  OperateQueryUserinfo.duty!=null'> and R.Cd=#{OperateQueryUserinfo.duty} </if>"+

            "<if test='OperateQueryUserinfo.psnZoneCd==OperateQueryUserinfo.zoneCd and OperateQueryUserinfo.extInfoObj.operatorCd!=\"spiritadmin\" '>   and  U.Cd not in (select e.Cd from SPM_SPMUser(nolock) e where e.ZoneCd=#{OperateQueryUserinfo.extInfoObj.depCd} and e.Duty='业务管理员') </if>"+
            "</script>")
    List<OperateUser> queryByUserOperateList(@Param("OperateQueryUserinfo")OperateQueryUserinfo operateQueryUserinfo);



    //删除用户
    @Select("<script> delete FROM  SPM_SPMUser  where Cd in" +
            " <foreach item='item'  collection='QueryUserinfo.cds' open='(' separator=','  close=')'> "+
            " #{item} "+
            " </foreach> "+
            "</script>")
    void delOperate(@Param("QueryUserinfo")QueryUserinfo queryUserinfo);

    //删除无活动用户权限
    @Select("<script> delete FROM  SPM_SPMUserRole  where UserCd in" +
            " <foreach item='item'  collection='QueryUserinfo.cds' open='(' separator=','  close=')'> "+
            " #{item} "+
            " </foreach> "+
            "</script>")
    void delOperateRole(@Param("QueryUserinfo")QueryUserinfo queryUserinfo);


    //获取用户信息
    @Select(" select U.*,D.Nam userattrNam,R.RoleCd FROM  SPM_SPMUser U left join  SPM_SPMDict D ON U.Userattr=D.Cd  left join SPM_SPMUserRole R on U.Cd=R.UserCd  where U.Cd=#{QueryUserinfo.cd} ")
    OperateUser queryByOperateById(@Param("QueryUserinfo")QueryUserinfo queryUserinfo);


    //修改用户信息
    @Select(" UPDATE SPM_SPMUser SET " +
            "DepartNam = #{OperateQueryUserinfo.departNam}," +
            "Phone = #{OperateQueryUserinfo.phone}," +
            "Email = #{OperateQueryUserinfo.email}," +
            "Address = #{OperateQueryUserinfo.address}," +
            "StartDate = #{OperateQueryUserinfo.startDate}," +
            "EndDate = #{OperateQueryUserinfo.endDate}," +
            "LastModiCd = #{OperateQueryUserinfo.extInfoObj.psnCd}," +
            "LastModTime = CONVERT(varchar, getDate(), 120)," +
            "UserStatus = (case when #{OperateQueryUserinfo.endDate}<CONVERT(varchar(50),getdate(),23) then  '过期' else '未过期' end ),"+
            "IDCode=#{OperateQueryUserinfo.iDCode}," +
            "Department=#{OperateQueryUserinfo.departNam}," +
            "Userattr=#{OperateQueryUserinfo.userattr} " +
            "WHERE Cd=#{OperateQueryUserinfo.cd} ")
    void updateOperateById(@Param("OperateQueryUserinfo")OperateQueryUserinfo operateQueryUserinfo);


    @Select(" UPDATE SPM_SPMUser SET Pwd='123456' where Cd=#{QueryUserinfo.cd}")
    void restUserPwd(@Param("QueryUserinfo")QueryUserinfo queryUserinfo);

    @Select(" UPDATE SPM_SPMUser " +

            "SET ActiveStatus=(case when #{QueryUserinfo.state}='已停用' then  '可用' else '已停用' end )" +

            " where Cd=#{QueryUserinfo.cd}")
    void activeStatusUser(@Param("QueryUserinfo")QueryUserinfo queryUserinfo);


    @Select(" UPDATE SPM_SPMUser " +

            "SET AppAuth=(case when #{QueryUserinfo.appAuth}='1' then  '0' else '1' end )" +

            " where Cd=#{QueryUserinfo.cd}")
    void appAuthUser(@Param("QueryUserinfo")QueryUserinfo queryUserinfo);


    @Select(" UPDATE SPM_SPMUser " +

            "SET DeviceID=#{QueryUserinfo.deviceID}" +

            " where Cd=#{QueryUserinfo.cd}")
    void deviceIDUser(@Param("QueryUserinfo")QueryUserinfo queryUserinfo);


    @Select(" update  SPM_SPMOrgan set IsHos='1' where Cd=#{pcd}  ")
    void updateOrganHospital(String pcd);


    @Select("{call  UserAccount_Userverify(" +
            "#{QueryUserinfo.cd,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryUserinfo.account,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryUserinfo.zoneCode,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryUserinfo.identity,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryUserinfo.roleCd,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryUserinfo.state,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryUserinfo.errMsg,mode=OUT,jdbcType=VARCHAR}," +
            "#{QueryUserinfo.reminder,mode=OUT,jdbcType=VARCHAR}" +
            ")}")
    @Options(statementType= StatementType.CALLABLE)
   void initUserverify(@Param("QueryUserinfo")QueryUserinfo queryUserinfo);



    @Select("{call  UserUpdate_Userverify(" +
            "#{QueryUserinfo.cd,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryUserinfo.account,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryUserinfo.zoneCode,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryUserinfo.identity,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryUserinfo.roleCd,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryUserinfo.endData,mode=IN,jdbcType=VARCHAR}," +
            "#{QueryUserinfo.errMsg,mode=OUT,jdbcType=VARCHAR}," +
            "#{QueryUserinfo.reminder,mode=OUT,jdbcType=VARCHAR}" +
            ")}")
    @Options(statementType= StatementType.CALLABLE)
   void initUpdateUserverify(@Param("QueryUserinfo")QueryUserinfo queryUserinfo);

    @Select("select count(1) from SPM_SPMUser U left join SPM_SPMUserRole R ON U.Cd=R.UserCd where R.RoleCd=#{QueryUserinfo.roleCd} and U.ZoneCd=#{QueryUserinfo.zoneCode} and U.State='1' and U.ActiveStatus='可用' and U.UserStatus='未过期' ")
    Long verificationRole(@Param("QueryUserinfo")QueryUserinfo queryUserinfo);

}

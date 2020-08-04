package com.hyd.usercontrol.mapper;

import com.hyd.system.factory.ResultFactory;
import com.hyd.usercontrol.info.NoActiveQueryUserinfo;
import com.hyd.usercontrol.info.QueryUserControlinfo;
import com.hyd.usercontrol.info.QueryUserinfo;
import com.hyd.usercontrol.vo.NoActiveUser;
import com.hyd.usercontrol.vo.Organ;
import com.hyd.usercontrol.vo.ProvinceVO;
import com.hyd.usercontrol.vo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NoActiveUserControllMapper {



    //获取机构
    @Select("<script> " +
            "select Cd,Nam from SPM_SPMOrgan spmOrgan" +

            " where  spmOrgan.ZoneCd like CONCAT(#{QueryUserControlinfo.zoneCd},\'%\')  and spmOrgan.IsVillage is null and spmOrgan.State='1' " +

            " <if test='QueryUserControlinfo.levCd!=\"\" and  QueryUserControlinfo.levCd!=null'> and LevCd=#{QueryUserControlinfo.levCd}  </if>" +
            "</script>")
    List<Organ> initOrganList(@Param("QueryUserControlinfo") QueryUserControlinfo queryUserControlinfo);



    //获取无活动用户列表
    @Select("<script> " +
            "Select U.UserStatus,U.Cd Cd,U.ActiveStatus ActiveStatus,U.CommunityPowr CommunityPowr,R.Nam Duty,U.LoginAccount LoginAccount,U.Nam Nam,U.ZoneNam ZoneNam,U.OrgNam OrganCdNam,U.Phone Phone,U.EndDate EndDate" +
            ",R.Cd FROM SPM_SPMUser(nolock) U join SPM_SPMRole(nolock) R on R.Cd = (select UR.RoleCd from SPM_SPMUserRole(nolock) UR where UR.UserCd=U.Cd)  where " +
            " U.State='1'  and U.Nam like CONCAT(\'%\',#{NoActiveQueryUserinfo.nam},\'%\')  "+

            " and  U.ZoneCd like CONCAT(#{NoActiveQueryUserinfo.zoneCd},\'%\') " +

            "<if test='NoActiveQueryUserinfo.startDate!=\"\" and  NoActiveQueryUserinfo.startDate!=null'> and isnull(U.LoginTime,'1900-01-01') &lt; #{NoActiveQueryUserinfo.startDate}  </if>"+
            "<if test='NoActiveQueryUserinfo.startDate==\"\" or  NoActiveQueryUserinfo.startDate==null'> and isnull(U.LoginTime,'') = ''  </if>"+

           "<if test='NoActiveQueryUserinfo.activeStatus!=\"\" and  NoActiveQueryUserinfo.activeStatus!=null'> and U.ActiveStatus=#{NoActiveQueryUserinfo.activeStatus}  </if>"+
           "<if test='NoActiveQueryUserinfo.loginAccount!=\"\" and  NoActiveQueryUserinfo.loginAccount!=null'> and U.LoginAccount=#{NoActiveQueryUserinfo.loginAccount} </if>"+
           "<if test='NoActiveQueryUserinfo.organCd!=\"\" and  NoActiveQueryUserinfo.organCd!=null'> and U.OrganCd=#{NoActiveQueryUserinfo.organCd} </if>"+
           "<if test='NoActiveQueryUserinfo.userStatus!=\"\" and  NoActiveQueryUserinfo.userStatus!=null'> and U.UserStatus=#{NoActiveQueryUserinfo.userStatus} </if>"+

            "<if test='NoActiveQueryUserinfo.duty!=\"\" and  NoActiveQueryUserinfo.duty!=null'> and R.Cd=#{NoActiveQueryUserinfo.duty} </if>"+

            "<if test='NoActiveQueryUserinfo.psnZoneCd==NoActiveQueryUserinfo.zoneCd and NoActiveQueryUserinfo.extInfoObj.operatorCd!=\"spiritadmin\" '>   and  U.Cd not in (select e.Cd from SPM_SPMUser(nolock) e where e.ZoneCd=#{NoActiveQueryUserinfo.extInfoObj.depCd} and e.Duty='业务管理员') </if>"+
            "</script>")
    List<NoActiveUser> queryByNoActiveUserList(@Param("NoActiveQueryUserinfo") NoActiveQueryUserinfo noActiveQueryUserinfo);


    //获取无活动用户信息
    @Select(" select * FROM  SPM_SPMUser  where Cd=#{QueryUserinfo.cd} ")
    NoActiveUser queryByNoActiveUserById(@Param("QueryUserinfo") QueryUserinfo queryUserinfo);

    //删除无活动用户
    @Select("<script> delete FROM  SPM_SPMUser  where Cd in" +
           " <foreach item='item'  collection='QueryUserinfo.cds' open='(' separator=','  close=')'> "+
            " #{item} "+
            " </foreach> "+
            "</script>")
    void delNoActive(@Param("QueryUserinfo")QueryUserinfo queryUserinfo);

    //删除无活动用户权限
    @Select("<script> delete FROM  SPM_SPMUserRole  where UserCd in" +
            " <foreach item='item'  collection='QueryUserinfo.cds' open='(' separator=','  close=')'> "+
            " #{item} "+
            " </foreach> "+
            "</script>")
    void delNoActiveRole(@Param("QueryUserinfo")QueryUserinfo queryUserinfo);


    //查询县的父级地区
    @Select(" select Cd,Nam from  SPM_SPMZone where Cd=(select ParCd from   SPM_SPMZone where Cd=#{QueryUserinfo.pcd}) AND LevCd='RegLevel002'")
    ProvinceVO queryParentZone(@Param("QueryUserinfo") QueryUserinfo queryUserinfo);
}

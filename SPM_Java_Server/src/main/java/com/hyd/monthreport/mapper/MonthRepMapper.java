package com.hyd.monthreport.mapper;

import com.hyd.monthreport.vo.StreetVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by xieshuai on 2020/4/7 17:22
 */
@Mapper
public interface MonthRepMapper {




   /**
    * 新增地区授权
    * @param cd 授权表主键
    * @param zoneCd 街道编码
    * @param cityCd 街道所属市编码
    * @param userCd 授权人编码
    * @param date 授权时间
    * @param state 状态 1 已授权 -1 已删除
    * @Parame authZoneCd 授权的当前地区编码(区县级别)
    * @return
    */
   @Insert("  insert into MonthRpt_Street_Auth (Cd, StreetCd, CityCd, CountyCd, UserCd,  AuthDate, State, AuthZoneCd)" +
           " values (#{Cd}, #{StreetCd}, #{CityCd}, #{CountyCd}, #{UserCd}, #{AuthDate}, #{State}, #{AuthZoneCd})")
   Integer addZoneAuth(@Param("Cd") String cd, @Param("StreetCd") String zoneCd, @Param("CityCd") String cityCd,
                       @Param("CountyCd") String CountyId, @Param("UserCd") String userCd,
                       @Param("AuthDate") String date, @Param("State") String state, @Param("AuthZoneCd") String authZoneCd);

   /**
    * 移除一个已授权的街道信息
    */
   @Delete("DELETE MonthRpt_Street_Auth where AuthZoneCd = #{authZoneCd} and StreetCd = #{streetCd}")
   void removeAuth( String authZoneCd, String streetCd);


   @Select("select s.Cd,s.Nam FROM SPM_SPMZone s WHERE s.Nam not like '不详%' and s.LevCd = 'RegLevel004' AND s.ParCd = #{parCd} AND s.Cd not in (select  m.StreetCd FROM MonthRpt_Street_Auth m WHERE m.AuthZoneCd = #{authZoneCd})")
   List<StreetVO> queryStreetInfo(@Param("parCd") String parCd, @Param("authZoneCd") String authZoneCd);


   /**
    * 根据已授权的街道编号查询市级编码
    * @param authZoneCd
    * @return
    */
   @Select("select s.Cd, s.Nam FROM SPM_SPMZone s Left join MonthRpt_Street_Auth a  On s.Cd = a.CityCd  WHERE a.AuthZoneCd = #{authZoneCd}  GROUP BY s.Cd, S.Nam ")
   List<StreetVO> loadAuthorizedCity(String authZoneCd);

   /**
    * 根据已授权的街道编号和市级编码查询区县编号和名称
    * @param cityCd
    * @param authZoneCd
    * @return
    */
   @Select("select s.Cd, s.Nam FROM SPM_SPMZone s Left join MonthRpt_Street_Auth a  On s.Cd = a .CountyCd Where a.CityCd = #{cityCd} and a.authZoneCd = #{authZoneCd} GROUP BY s.Cd, S.Nam ")
   List<StreetVO> loadAuthorizedCounty(String cityCd, String authZoneCd);

   /**
    * 根据已授权的街道编号和区县编码查询所有的街道信息
    * @param countyCd
    * @param authZoneCd
    * @return
    */
   @Select("select s.Cd, s.Nam FROM SPM_SPMZone s Left join MonthRpt_Street_Auth a  On s.Cd = a .StreetCd Where a.CountyCd = #{countyCd} and a.authZoneCd = #{authZoneCd}")
   List<StreetVO> loadAuthorizedStreet(String countyCd, String authZoneCd);



}

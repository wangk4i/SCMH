package com.hyd.monthreport.mapper;

import com.hyd.monthreport.vo.MonthQueAreaVO;
import com.hyd.monthreport.vo.StreetVO;
import com.hyd.monthreport.vo.ZoneVO;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by xieshuai on 2020/4/10 17:09
 *  月报自定义查询mapper
 */

@Mapper
public interface MonthQueMapper {



    /**
     * 根据片区编码 查询城市信息
     * @param areaCd 片区编码
     * @return
     */
    @Select("select DISTINCT s.Cd id, s.Nam title FROM MonthQue_Zone z LEFT JOIN SPM_SPMZone s ON s.Cd = z.CityCd WHERE AreaCd = #{areaCd}")
    List<ZoneVO> queryCityByAreaCode(String areaCd);

    /**
     * 根据片区编码 查询区县信息
     * @param areaCd
     * @return
     */
    @Select("select DISTINCT s.Cd id, s.Nam title FROM MonthQue_Zone z LEFT JOIN SPM_SPMZone s ON s.Cd = z.CountyCd WHERE AreaCd = #{areaCd} and z.CityCd = #{cityCd}")
    List<ZoneVO> queryCountyByAreaCode(String areaCd, String cityCd);

    @Select("select DISTINCT s.Cd id, s.Nam title From MonthQue_Zone z Left join SPM_SPMZone s On s.Cd = z.StreetCd Where AreaCd = #{areaCd} and z.CountyCd = #{countyCd}")
    List<ZoneVO> queryStreetByAreaCd(String areaCd, String countyCd);



    /**
     * 根据当前区县编码查询市级信息
     * @return
     */
    @Select("select s.Cd, s.Nam FROM SPM_SPMZone s WHERE Cd in (select parCd FROM SPM_SPMZone WHERE Cd = #{cd})")
    StreetVO queryThisCity(String cd);

    /**
     * 根据当前区县编码查询区县信息
     * @param cd
     * @return
     */
    @Select("select s.Cd, s.Nam From SPM_SPMZone s where Cd = #{cd}")
    StreetVO queryThisDistricts(String cd);

    /**
     * 根据当前区县编码查询街道信息
     * @param parCd
     * @return
     */
    @Select("select s.Cd, s.Nam From SPM_SpmZone s  where s.ParCd = #{parCd}")
    List<StreetVO> queryThisStreets(String parCd);

    /**
     * 新增指标片区
     * @param cd
     * @param areaNam
     * @param authZoneCd
     * @param userCd
     * @param state
     * @return
     */
    @Insert("insert into MonthQue_Area (Cd,  AreaNam, AuthZoneCd, UserCd, AuthDate, State)" +
            "values(#{cd}, #{areaNam}, #{authZoneCd}, #{userCd}, #{authDate}, #{state})")
    Integer addArea(String cd, String areaNam, String authZoneCd, String userCd, String authDate, String state);

    /**
     * 移除一个指标片区
     * @param areaCd
     * @return
     */
    @Delete("delete from MonthQue_Area where Cd = #{areaCd} and AuthZoneCd = #{authZoneCd}")
    Integer delArea(String areaCd, String authZoneCd);


    /**
     * 根据地区编码查询当前地区下所有片区信息
     * @param authZoneCd
     * @return
     */
    @Select("select * from MonthQue_Area where AuthZoneCd = #{authZoneCd}")
    List<MonthQueAreaVO> loadMonthArea(String authZoneCd);

    /**
     * 新增片区信息中地区信息
     * @param areaCd
     * @param cityCd
     * @param countyCd
     * @param streetCd
     * @param isQuota
     * @return
     */
    @Insert("insert into MonthQue_Zone (AreaCd, CityCd, CountyCd, StreetCd, IsQuota)" +
            "values(#{areaCd}, #{cityCd}, #{countyCd}, #{streetCd}, #{isQuota})")
    Integer addQueZone(String areaCd, String cityCd, String countyCd, String streetCd, String isQuota);


    /**
     * 移除片区信息中的地区信息
     * @param areaCd
     * @return
     */
    @Delete("delete from MonthQue_Zone where AreaCd = #{areaCd}")
    Integer delQueZone(String areaCd);


    @Select("select isQuota from MonthQue_Zone where AreaCd = #{areaCd} and StreetCd = #{streetCd}")
    String selectStreetByIsQuota(String areaCd, String streetCd);

    @Select("select ConfigValue,DescribeValue from MonthQue_Config where ConfigType = 'year' and State = '1'")
    List<Map<String, Object>> queryConfigYears();

    @Select("select ConfigValue monthCode,DescribeValue monthNam from MonthQue_Config where ConfigType = 'month' and ParentValue = #{year} and State = '1'")
    List<Map<String, Object>> queryConfigMonths(String year);

//
//    /**
//     * 根据片区编码 查询街道信息和指标数据
//     * @param areaCd
//     * @return
//     */
//    @Select("SELECT DISTINCT " +
//            " d.StreetCd id, " +
//            " d.StreetNam title, " +
//            " d.Num1 czrk, " +
//            " d.Num2 ljjd, " +
//            " d.Num3 byjd, " +
//            " d.Num4 ljsw, " +
//            " d.Num5 bybg, " +
//            " d.Num6 zchz, " +
//            " d.Num7 zqty, " +
//            " d.Num8 hbl  " +
//            "FROM " +
//            " MonthQue_Zone z  " +
//            " LEFT JOIN  MonthRpt_TB_Organ_Documentation d  ON z.StreetCd = d.StreetCd " +
//            "WHERE " +
//            " z.StreetCd in (SELECT StreetCd FROM MonthQue_Zone WHERE  AreaCd = #{areaCd} AND CountyCd = #{countyCd}) " +
//            " AND d.StatisDate = #{date}")
//    List<ZoneVO> queryStreetByAreaCode(String areaCd, String countyCd, String date);
//
//
//    /**
//     * 建档情况街道数据
//     * @param areaCd
//     * @param countyCd
//     * @param date
//     * @return
//     */
//    @Select("SELECT DISTINCT " +
//            " d.StreetCd id, " +
//            " d.StreetNam title " +
//            "FROM " +
//            " MonthQue_Zone z  " +
//            " LEFT JOIN  MonthRpt_TB_Organ_Documentation d  ON z.StreetCd = d.StreetCd " +
//            "WHERE " +
//            " z.StreetCd in (SELECT StreetCd FROM MonthQue_Zone WHERE  AreaCd = #{areaCd} AND CountyCd = #{countyCd}) " +
//            " AND d.StatisDate = #{date}")
//    List<ZoneVO> queryStreetByAreaCodeTest(String areaCd, String countyCd, String date);
//
//    /**
//     * 基础管理街道数据
//     * @param areaCd
//     * @param countyCd
//     * @param date
//     * @return
//     */
//    @Select("SELECT DISTINCT " +
//            " d.StreetCd id, " +
//            " d.StreetNam title " +
//            "FROM " +
//            " MonthQue_Zone z  " +
//            " LEFT JOIN  MonthRpt_TB_Organ_BasicManagement d  ON z.StreetCd = d.StreetCd " +
//            "WHERE " +
//            " z.StreetCd in (SELECT StreetCd FROM MonthQue_Zone WHERE  AreaCd = #{areaCd} AND CountyCd = #{countyCd}) " +
//            " AND d.StatisDate = #{date}")
//    List<ZoneVO> queryStreetByAreaBasicManagement(String areaCd, String countyCd, String date);
//
//    /**
//     * 个案管理街道数据
//     * @param areaCd
//     * @param countyCd
//     * @param date
//     * @return
//     */
//    @Select("SELECT DISTINCT " +
//            " d.StreetCd id, " +
//            " d.StreetNam title " +
//            "FROM " +
//            " MonthQue_Zone z  " +
//            " LEFT JOIN  MonthRpt_TB_Organ_CaseManagement d  ON z.StreetCd = d.StreetCd " +
//            "WHERE " +
//            " z.StreetCd in (SELECT StreetCd FROM MonthQue_Zone WHERE  AreaCd = #{areaCd} AND CountyCd = #{countyCd}) " +
//            " AND d.StatisDate = #{date}")
//    List<ZoneVO> queryStreetByAreaCaseManagement(String areaCd, String countyCd, String date);
//
//    /**
//     * 一般信息街道数据
//     * @param areaCd
//     * @param countyCd
//     * @param date
//     * @return
//     */
//    @Select("SELECT DISTINCT " +
//            " d.StreetCd id, " +
//            " d.StreetNam title " +
//            "FROM " +
//            " MonthQue_Zone z  " +
//            " LEFT JOIN  MonthRpt_TB_Organ_GeneralInformation d  ON z.StreetCd = d.StreetCd " +
//            "WHERE " +
//            " z.StreetCd in (SELECT StreetCd FROM MonthQue_Zone WHERE  AreaCd = #{areaCd} AND CountyCd = #{countyCd}) " +
//            " AND d.StatisDate = #{date}")
//    List<ZoneVO> queryStreetByAreaGeneralInformation(String areaCd, String countyCd, String date);
//
//    /**
//     * 失访和死亡街道数据
//     * @param areaCd
//     * @param countyCd
//     * @param date
//     * @return
//     */
//    @Select("SELECT DISTINCT " +
//            " d.StreetCd id, " +
//            " d.StreetNam title " +
//            "FROM " +
//            " MonthQue_Zone z  " +
//            " LEFT JOIN  MonthRpt_TB_Organ_LosAndDeath d  ON z.StreetCd = d.StreetCd " +
//            "WHERE " +
//            " z.StreetCd in (SELECT StreetCd FROM MonthQue_Zone WHERE  AreaCd = #{areaCd} AND CountyCd = #{countyCd}) " +
//            " AND d.StatisDate = #{date}")
//    List<ZoneVO> queryStreetByAreaLosAndDeath(String areaCd, String countyCd, String date);
//
//    /**
//     * 管理情况街道数据
//     * @param areaCd
//     * @param countyCd
//     * @param date
//     * @return
//     */
//    @Select("SELECT DISTINCT " +
//            " d.StreetCd id, " +
//            " d.StreetNam title " +
//            "FROM " +
//            " MonthQue_Zone z  " +
//            " LEFT JOIN  MonthRpt_TB_Organ_Management d  ON z.StreetCd = d.StreetCd " +
//            "WHERE " +
//            " z.StreetCd in (SELECT StreetCd FROM MonthQue_Zone WHERE  AreaCd = #{areaCd} AND CountyCd = #{countyCd}) " +
//            " AND d.StatisDate = #{date}")
//    List<ZoneVO> queryStreetByAreaManagement(String areaCd, String countyCd, String date);
//
//
//    /**
//     * 治疗情况街道数据
//     * @param areaCd
//     * @param countyCd
//     * @param date
//     * @return
//     */
//    @Select("SELECT DISTINCT " +
//            " d.StreetCd id, " +
//            " d.StreetNam title " +
//            "FROM " +
//            " MonthQue_Zone z  " +
//            " LEFT JOIN  MonthRpt_TB_Organ_TreatmentSituation d  ON z.StreetCd = d.StreetCd " +
//            "WHERE " +
//            " z.StreetCd in (SELECT StreetCd FROM MonthQue_Zone WHERE  AreaCd = #{areaCd} AND CountyCd = #{countyCd}) " +
//            " AND d.StatisDate = #{date}")
//    List<ZoneVO> queryStreetByAreaTreatmentSituation(String areaCd, String countyCd, String date);
//
//
//
//    /**
//     * 根据街道编码 查询机构信息和指标数据
//     * @param areaCd
//     * @param countyCd
//     * @return
//     */
//    @Select("SELECT DISTINCT " +
//            "        d.OrganCd id, " +
//            "         d.OrganNam title,  "+
//            "       d.Num1 czrk,  "+
//            "       d.Num2 ljjd,  "+
//            "       d.Num3 byjd,  "+
//            "       d.Num4 ljsw,  "+
//            "       d.Num5 bybg,  "+
//            "       d.Num6 zchz,  "+
//            "       d.Num7 zqty,  "+
//            "       d.Num8 hbl   "+
//            "            FROM  "+
//            "       MonthQue_Zone z   "+
//            "       LEFT JOIN  MonthRpt_TB_Organ_Documentation d  ON z.StreetCd = d.StreetCd " +
//            "            WHERE  "+
//            "       z.StreetCd in (SELECT StreetCd FROM MonthQue_Zone WHERE  AreaCd = #{areaCd} AND CountyCd = #{countyCd})" +
//            "       AND d.StatisDate = #{date}")
//    List<ZoneVO> queryOrganByAreaCode(String areaCd, String countyCd, String date);
//
//    @Select("SELECT DISTINCT" +
//            "            d.OrganCd id," +
//            "              d.OrganNam title," +
//            "              d.Num1 czrk, " +
//            "             d.Num2 ljjd,  " +
//            "             d.Num3 byjd,  " +
//            "              d.Num4 ljsw,  " +
//            "           d.Num5 bybg,  " +
//            "             d.Num6 zchz, " +
//            "             d.Num7 zqty, " +
//            "              d.Num8 hbl " +
//            "                   FROM " +
//            "             MonthQue_Zone z  " +
//            "             LEFT JOIN  MonthRpt_TB_Organ_Documentation d  ON z.StreetCd = d.StreetCd " +
//            "                    WHERE  " +
//            "              z.StreetCd  = #{streetCd}" +
//            "              AND d.StatisDate = #{date}")
//    List<ZoneVO> queryOrganByAreaCodeTest(String streetCd, String date);
//
//
//    /**
//     * 查询建档情况的基础数据
//     * @param streetCd
//     * @param date
//     * @return
//     */
//    @Select("SELECT DISTINCT" +
//            "            d.OrganCd id," +
//            "              d.OrganNam title," +
//            "              d.Num1 czrk, " +
//            "             d.Num2 ljjd,  " +
//            "             d.Num3 byjd,  " +
//            "              d.Num4 ljsw,  " +
//            "           d.Num5 bybg,  " +
//            "             d.Num6 zchz, " +
//            "             d.Num7 zqty, " +
//            "              d.Num8 hbl " +
//            "                   FROM " +
//            "             MonthQue_Zone z  " +
//            "             LEFT JOIN  MonthRpt_TB_Organ_Documentation d  ON z.StreetCd = d.StreetCd " +
//            "                    WHERE  " +
//            "              z.StreetCd  = #{streetCd}" +
//            "              AND d.StatisDate = #{date}")
//    List<Map<String, Object>> queryOrganByAreaCodeTest2(String streetCd, String date);
//
//    /**
//     * 月报一般情况基础数据查询
//     * @param streetCd
//     * @param date
//     * @return
//     */
//    @Select("SELECT DISTINCT" +
//            "            d.OrganCd id," +
//            "              d.OrganNam title " +
//            "                   FROM " +
//            "             MonthQue_Zone z  " +
//            "             LEFT JOIN  MonthRpt_TB_Organ_GeneralInformation d  ON z.StreetCd = d.StreetCd " +
//            "                    WHERE  " +
//            "              z.StreetCd  = #{streetCd}" +
//            "              AND d.StatisDate = #{date}")
//    List<ZoneVO> queryMonthDataByGeneralInformation(String streetCd, String date);
//
//    /**
//     * 月报基础管理基础数据查询
//     * @param streetCd
//     * @param date
//     * @return
//     */
//    @Select("SELECT DISTINCT" +
//            "            d.OrganCd id," +
//            "              d.OrganNam title" +
//            "                   FROM " +
//            "             MonthQue_Zone z  " +
//            "             LEFT JOIN  MonthRpt_TB_Organ_BasicManagement d  ON z.StreetCd = d.StreetCd " +
//            "                    WHERE  " +
//            "              z.StreetCd  = #{streetCd}" +
//            "              AND d.StatisDate = #{date}")
//    List<ZoneVO> queryMonthDataByBasicManagement(String streetCd, String date);
//
//    /**
//     * 月报失访和死亡基础数据查询
//     * @param streetCd
//     * @param date
//     * @return
//     */
//    @Select("SELECT DISTINCT" +
//            "            d.OrganCd id," +
//            "              d.OrganNam title" +
//            "                   FROM " +
//            "             MonthQue_Zone z  " +
//            "             LEFT JOIN  MonthRpt_TB_Organ_LosAndDeath d  ON z.StreetCd = d.StreetCd " +
//            "                    WHERE  " +
//            "              z.StreetCd  = #{streetCd}" +
//            "              AND d.StatisDate = #{date}")
//    List<ZoneVO> queryMonthDataByLosAndDeath(String streetCd, String date);
//
//    /**
//     * 月报治疗情况基础数据查询
//     * @param streetCd
//     * @param date
//     * @return
//     */
//    @Select("SELECT DISTINCT" +
//            "            d.OrganCd id," +
//            "              d.OrganNam title," +
//            "              d.Num1 czrk, " +
//            "             d.Num2 ljjd,  " +
//            "             d.Num3 byjd,  " +
//            "              d.Num4 ljsw,  " +
//            "           d.Num5 bybg,  " +
//            "             d.Num6 zchz, " +
//            "             d.Num7 zqty, " +
//            "              d.Num8 hbl " +
//            "                   FROM " +
//            "             MonthQue_Zone z  " +
//            "             LEFT JOIN  MonthRpt_TB_Organ_TreatmentSituation d  ON z.StreetCd = d.StreetCd " +
//            "                    WHERE  " +
//            "              z.StreetCd  = #{streetCd}" +
//            "              AND d.StatisDate = #{date}")
//    List<ZoneVO> queryMonthDataByTreatmentSituation(String streetCd, String date);
//
//    /**
//     * 月报管理情况基础数据查询
//     * @param streetCd
//     * @param date
//     * @return
//     */
//    @Select("SELECT DISTINCT" +
//            "            d.OrganCd id," +
//            "              d.OrganNam title," +
//            "              d.Num1 czrk, " +
//            "             d.Num2 ljjd,  " +
//            "             d.Num3 byjd,  " +
//            "              d.Num4 ljsw,  " +
//            "           d.Num5 bybg,  " +
//            "             d.Num6 zchz, " +
//            "             d.Num7 zqty, " +
//            "              d.Num8 hbl " +
//            "                   FROM " +
//            "             MonthQue_Zone z  " +
//            "             LEFT JOIN  MonthRpt_TB_Organ_Management d  ON z.StreetCd = d.StreetCd " +
//            "                    WHERE  " +
//            "              z.StreetCd  = #{streetCd}" +
//            "              AND d.StatisDate = #{date}")
//    List<ZoneVO> queryMonthDataByManagement(String streetCd, String date);
//
//    /**
//     * 月报个案管理基础数据查询
//     * @param streetCd
//     * @param date
//     * @return
//     */
//    @Select("SELECT DISTINCT" +
//            "            d.OrganCd id," +
//            "              d.OrganNam title" +
//            "                   FROM " +
//            "             MonthQue_Zone z  " +
//            "             LEFT JOIN  MonthRpt_TB_Organ_GeneralInformation d  ON z.StreetCd = d.StreetCd " +
//            "                    WHERE  " +
//            "              z.StreetCd  = #{streetCd}" +
//            "              AND d.StatisDate = #{date}")
//    List<ZoneVO> queryMonthDataByCaseManagement(String streetCd, String date);
//
//
//
//
//    @Select("SELECT DISTINCT  d.* " +
//            "FROM  " +
//            "  MonthRpt_TB_Organ_Documentation d  " +
//            "WHERE  " +
//            "  d.CountyCd = #{countyCd}  " +
//            "  AND d.StatisDate = #{ statisDate}")
//    List<Map<String, Object>> monthQueDocumentationByOrgan(String countyCd, String statisDate);
//
//    @Select("SELECT DISTINCT d.* " +
//            "FROM " +
//            " MonthQue_Zone z  " +
//            " LEFT JOIN  MonthRpt_TB_Organ_Documentation d  ON z.StreetCd = d.StreetCd " +
//            "WHERE " +
//            " z.StreetCd in (SELECT StreetCd FROM MonthQue_Zone WHERE  AreaCd = #{areaCd} AND CountyCd = #{countyCd}) " +
//            " AND d.StatisDate = #{statisDate}")
//    List<Map<String, Object>> monthQueDocumentationByStreet(String areaCd, String countyCd, String statisDate);
//
//
//    @Select("SELECT DISTINCT  d.* " +
//            "FROM  " +
//            "  MonthRpt_TB_Organ_GeneralInformation d  " +
//            "WHERE  " +
//            "  d.CountyCd = #{countyCd}  " +
//            "  AND d.StatisDate = #{ statisDate}")
//    List<Map<String, Object>> monthQueGenerallyByOrgan(String countyCd, String statisDate);
//
//    @Select("SELECT DISTINCT d.* " +
//            "FROM " +
//            " MonthQue_Zone z  " +
//            " LEFT JOIN  MonthRpt_TB_Organ_GeneralInformation d  ON z.StreetCd = d.ZoneCd " +
//            "WHERE " +
//            " z.StreetCd in (SELECT StreetCd FROM MonthQue_Zone WHERE  AreaCd = #{areaCd} AND CountyCd = #{countyCd}) " +
//            " AND d.StatisDate = #{statisDate}")
//    List<Map<String, Object>> monthQueGenerallyByStreet(String areaCd, String countyCd, String statisDate);
//
//    @Select("SELECT DISTINCT  d.* " +
//            "FROM  " +
//            "  MonthRpt_TB_Organ_TreatmentSituation d  " +
//            "WHERE  " +
//            "  d.CountyCd = #{countyCd}  " +
//            "  AND d.StatisDate = #{ statisDate}")
//    List<Map<String, Object>> monthQueTreatmentByOrgan(String countyCd, String statisDate);
//
//    @Select("SELECT DISTINCT d.* " +
//            "FROM " +
//            " MonthQue_Zone z  " +
//            " LEFT JOIN  MonthRpt_TB_Organ_TreatmentSituation d  ON z.StreetCd = d.ZoneCd " +
//            "WHERE " +
//            " z.StreetCd in (SELECT StreetCd FROM MonthQue_Zone WHERE  AreaCd = #{areaCd} AND CountyCd = #{countyCd}) " +
//            " AND d.StatisDate = #{statisDate}")
//    List<Map<String, Object>> monthQueTreatmentByStreet(String areaCd, String countyCd, String statisDate);
//
//    @Select("SELECT DISTINCT  d.* " +
//            "FROM  " +
//            "  MonthRpt_TB_Organ_Management d  " +
//            "WHERE  " +
//            "  d.CountyCd = #{countyCd}  " +
//            "  AND d.StatisDate = #{ statisDate}")
//    List<Map<String, Object>> monthQueManagerByOrgan(String countyCd, String statisDate);
//
//    @Select("SELECT DISTINCT d.* " +
//            "FROM " +
//            " MonthQue_Zone z  " +
//            " LEFT JOIN  MonthRpt_TB_Organ_Management d  ON z.StreetCd = d.ZoneCd " +
//            "WHERE " +
//            " z.StreetCd in (SELECT StreetCd FROM MonthQue_Zone WHERE  AreaCd = #{areaCd} AND CountyCd = #{countyCd}) " +
//            " AND d.StatisDate = #{statisDate}")
//    List<Map<String, Object>> monthQueManagerByStreet(String areaCd, String countyCd, String statisDate);
//
//    @Select("SELECT DISTINCT  d.* " +
//            "FROM  " +
//            "  MonthRpt_TB_Organ_BasicManagement d  " +
//            "WHERE  " +
//            "  d.CountyCd = #{countyCd}  " +
//            "  AND d.StatisDate = #{ statisDate}")
//    List<Map<String, Object>> monthQueBasisByOrgan(String countyCd, String statisDate);
//
//    @Select("SELECT DISTINCT d.* " +
//            "FROM " +
//            " MonthQue_Zone z  " +
//            " LEFT JOIN  MonthRpt_TB_Organ_BasicManagement d  ON z.StreetCd = d.ZoneCd " +
//            "WHERE " +
//            " z.StreetCd in (SELECT StreetCd FROM MonthQue_Zone WHERE  AreaCd = #{areaCd} AND CountyCd = #{countyCd}) " +
//            " AND d.StatisDate = #{statisDate}")
//    List<Map<String, Object>> monthQueBasisByStreet(String areaCd, String countyCd, String statisDate);
//
//    @Select("SELECT DISTINCT  d.* " +
//            "FROM  " +
//            "  MonthRpt_TB_Organ_CaseManagement d  " +
//            "WHERE  " +
//            "  d.CountyCd = #{countyCd}  " +
//            "  AND d.StatisDate = #{ statisDate}")
//    List<Map<String, Object>> monthQueCaseByOrgan(String countyCd, String statisDate);
//
//    @Select("SELECT DISTINCT d.* " +
//            "FROM " +
//            " MonthQue_Zone z  " +
//            " LEFT JOIN  MonthRpt_TB_Organ_CaseManagement d  ON z.StreetCd = d.ZoneCd " +
//            "WHERE " +
//            " z.StreetCd in (SELECT StreetCd FROM MonthQue_Zone WHERE  AreaCd = #{areaCd} AND CountyCd = #{countyCd}) " +
//            " AND d.StatisDate = #{statisDate}")
//    List<Map<String, Object>> monthQueCaseByStreet(String areaCd, String countyCd, String statisDate);
//
//    @Select("SELECT DISTINCT  d.* " +
//            "FROM  " +
//            "  MonthRpt_TB_Organ_LosAndDeath d  " +
//            "WHERE  " +
//            "  d.CountyCd = #{countyCd}  " +
//            "  AND d.StatisDate = #{ statisDate}")
//    List<Map<String, Object>> monthQueDeathByOrgan(String countyCd, String statisDate);
//
//    @Select("SELECT DISTINCT d.* " +
//            "FROM " +
//            " MonthQue_Zone z  " +
//            " LEFT JOIN  MonthRpt_TB_Organ_LosAndDeath d  ON z.StreetCd = d.ZoneCd " +
//            "WHERE " +
//            " z.StreetCd in (SELECT StreetCd FROM MonthQue_Zone WHERE  AreaCd = #{areaCd} AND CountyCd = #{countyCd}) " +
//            " AND d.StatisDate = #{statisDate}")
//    List<Map<String, Object>> monthQueDeathByStreet(String areaCd, String countyCd, String statisDate);
//


}

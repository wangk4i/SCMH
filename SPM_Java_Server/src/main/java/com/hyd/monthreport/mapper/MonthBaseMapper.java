package com.hyd.monthreport.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * Created by xieshuai on 2020/5/7 9:58
 */
@Mapper
public interface MonthBaseMapper {

    /**
     * 基础管理
     * 根据街道和日期查询机构基础数据
     * @param streetCd
     * @param date
     * @return
     */
    @Select("SELECT DISTINCT d.*, d.OrganCd as id, d.OrganNam as title, '5' as pid " +
            "                   FROM " +
            "             MonthQue_Zone z  " +
            "             LEFT JOIN  MonthRpt_TB_Organ_BasicManagement d  ON z.StreetCd = d.StreetCd " +
            "                    WHERE  " +
            "              z.StreetCd  = #{streetCd}" +
            "              AND d.StatisDate = #{date}")
    List<Map<String, Object>> queryBasicManageBaseDate(String streetCd, String date);

    /**
     * 个案管理
     * 根据街道和日期查询机构基础数据
     * @param streetCd
     * @param date
     * @return
     */
    @Select("SELECT DISTINCT d.*, d.OrganCd as id, d.OrganNam as title, '5' as pid " +
            "                   FROM " +
            "             MonthQue_Zone z  " +
            "             LEFT JOIN  MonthRpt_TB_Organ_CaseManagement d  ON z.StreetCd = d.StreetCd " +
            "                    WHERE  " +
            "              z.StreetCd  = #{streetCd}" +
            "              AND d.StatisDate = #{date}")
    List<Map<String, Object>> queryCaseManageBaseDate(String streetCd, String date);

    /**
     * 建档情况
     * 根据街道和日期查询机构基础数据
     * @param streetCd
     * @param date
     * @return
     */
    @Select("SELECT DISTINCT d.*, d.OrganCd as id, d.OrganNam as title ,'5' as pid " +
            "                   FROM " +
            "             MonthQue_Zone z  " +
            "             LEFT JOIN  MonthRpt_TB_Organ_Documentation d  ON z.StreetCd = d.StreetCd " +
            "                    WHERE  " +
            "              z.StreetCd  = #{streetCd}" +
            "              AND d.StatisDate = #{date}")
    List<Map<String, Object>> queryDocumentBaseDate(String streetCd, String date);

    /**
     * 一般信息
     * 根据街道和日期查询机构基础数据
     * @param streetCd
     * @param date
     * @return
     */
    @Select("SELECT DISTINCT d.*, d.OrganCd as id, d.OrganNam as title, '5' as pid " +
            "                   FROM " +
            "             MonthQue_Zone z  " +
            "             LEFT JOIN  MonthRpt_TB_Organ_GeneralInformation d  ON z.StreetCd = d.StreetCd " +
            "                    WHERE  " +
            "              z.StreetCd  = #{streetCd}" +
            "              AND d.StatisDate = #{date}")
    List<Map<String, Object>> queryGenInfoBaseDate(String streetCd, String date);

    /**
     * 失访和死亡
     * 根据街道和日期查询机构基础数据
     * @param streetCd
     * @param date
     * @return
     */
    @Select("SELECT DISTINCT d.*, d.OrganCd as id, d.OrganNam as title, '5' as pid " +
            "                   FROM " +
            "             MonthQue_Zone z  " +
            "             LEFT JOIN  MonthRpt_TB_Organ_LosAndDeath d  ON z.StreetCd = d.StreetCd " +
            "                    WHERE  " +
            "              z.StreetCd  = #{streetCd}" +
            "              AND d.StatisDate = #{date}")
    List<Map<String, Object>> queryLosAndDeathBaseDate(String streetCd, String date);

    /**
     * 管理情况
     * 根据街道和日期查询机构基础数据
     * @param streetCd
     * @param date
     * @return
     */
    @Select("SELECT DISTINCT d.*, d.OrganCd as id, d.OrganNam as title, '5' as pid " +
            "                   FROM " +
            "             MonthQue_Zone z  " +
            "             LEFT JOIN  MonthRpt_TB_Organ_Management d  ON z.StreetCd = d.StreetCd " +
            "                    WHERE  " +
            "              z.StreetCd  = #{streetCd}" +
            "              AND d.StatisDate = #{date}")
    List<Map<String, Object>> queryManageBaseDate(String streetCd, String date);

    /**
     * 治疗情况
     * 根据街道和日期查询机构基础数据
     * @param streetCd
     * @param date
     * @return
     */
    @Select("SELECT DISTINCT d.*, d.OrganCd as id, d.OrganNam as title, '5' as pid " +
            "                   FROM " +
            "             MonthQue_Zone z  " +
            "             LEFT JOIN  MonthRpt_TB_Organ_TreatmentSituation d  ON z.StreetCd = d.StreetCd " +
            "                    WHERE  " +
            "              z.StreetCd  = #{streetCd}" +
            "              AND d.StatisDate = #{date}")
    List<Map<String, Object>> queryTreatSitBaseDate(String streetCd, String date);


}

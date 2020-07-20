package com.hyd.subordtest.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface CaseReportInfoMapper {
    @Select("select * from V_Center2020_CaseReport_Test where ID= #{cd} and NewCaseReportId is null ")
    Map<String, Object> queryReportInfoViewOfInsert(String cd);

    @Select("select * from V_Center2020_CaseReport_Test where ID= #{cd} and NewCaseReportId is not null ")
    Map<String, Object> queryReportInfoViewOfUpdate(String cd);

    @Select("select * from V_Center2020_CaseReport_Test where ID= #{cd} and DelStatus!= 'DelLogo001'")
    Map<String, Object> queryReportInfoViewOfDelete(String cd);
}

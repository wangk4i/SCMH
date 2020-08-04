package com.hyd.subordtest.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface CaseReportInfoMapper {

    @Select("select * from V_Center2020_CaseReport where LocalReportID= #{cd} and State=1 ")
    Map<String, Object> queryReportInfoViewByCd(String cd);

    @Select("select * from V_Center2020_CaseReport where LocalReportID= #{cd} and DelStatus= 'DelLogo002'")
    Map<String, Object> queryReportInfoViewOfDelete(String cd);

    @Select("select * from V_Center2020_CaseReport where NewCaseReportId= #{cd}")
    List<Map<String,Object>> queryMedicationByNewCaseReportId(String cd);



}
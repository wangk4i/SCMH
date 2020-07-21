package com.hyd.subordtest.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface CaseReportInfoMapper {
    @Select("select * from V_Center2020_CaseReport_Test where ID= #{cd}") // and BasicInformationId is null
    Map<String, Object> queryReportInfoViewOfInsert(String cd);

    @Select("select * from V_Center2020_CaseReport_Test where ID= #{cd}") // and BasicInformationId is not null
    Map<String, Object> queryReportInfoViewOfUpdate(String cd);

    @Select("select * from V_Center2020_CaseReport_Test where ID= #{cd} and DelStatus!= 'DelLogo001'")
    Map<String, Object> queryReportInfoViewOfDelete(String cd);

    @Select("select * from V_Center2020_MedicationByReportCard_Test where NewCaseReportId= #{cd}")
    List<Map<String,Object>> queryMedicationByNewCaseReportId(String cd);



}
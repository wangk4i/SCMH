package com.hyd.subordtest.mapper;

import io.lettuce.core.MapScanCursor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.velocity.VelocityContext;

import java.util.List;
import java.util.Map;

@Mapper
public interface CaseReportInfoMapper {
    @Select("select * from V_Center2020_CaseReport_Test where ID= #{cd} and BasicInformationId is null ")
    Map<String, Object> queryReportInfoViewOfInsert(String cd);

    @Select("select * from V_Center2020_CaseReport_Test where ID= #{cd} and BasicInformationId is not null ")
    Map<String, Object> queryReportInfoViewOfUpdate(String cd);

    @Select("select * from V_Center2020_CaseReport_Test where ID= #{cd} and DelStatus!= 'DelLogo001'")
    Map<String, Object> queryReportInfoViewOfDelete(String cd);

    @Select("select * from V_Center2020_MedicationByReportCard_Test where InciRptCd= #{cd}")
    List<Map<String,Object>> queryMedicationbyRptCd(String cd);



}
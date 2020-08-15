package com.hyd.subordmq.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface CaseReportInfoMapper {

    @Select("select * from V_Center2020_CaseReport where LocalReportID= #{cd} and State=1 ")
    Map<String, Object> buildReportCard(String cd);

    @Select("select * from V_Center2020_CaseReport where LocalReportID= #{cd} and DelStatus= 'DelLogo002'")
    Map<String, Object> deleteReportCard(String cd);

    @Select("select * from V_Center2020_CaseReport_Med where NewCaseReportId= #{cd}")
    List<Map<String,Object>> queryMedication(String cd);

    @Update("update SPM_SPMPatInciRpt set SyncStatus=0 where Cd=#{cd}")
    void lockSyncFlag(String cd);

}
package com.hyd.subordmq.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface DischargeInfoMapper {

    @Select("select * from V_Center2020_DischargeInfo where LocalDischargeID= #{cd} and State=1 ")
    Map<String, Object> buildLeftCard(String cd);

    @Select("select * from V_Center2020_DischargeInfo where LocalDischargeID= #{cd} and DelStatus= 'DelLogo002'")
    Map<String, Object> deleteLeftCard(String cd);

    @Select("select * from V_Center2020_DischargeInfo_Med where DischargeId = #{cd} and Type = '01'")
    List<Map<String, Object>> queryMedication(String cd);

    @Select("select * from V_Center2020_DischargeInfo_Med where DischargeId = #{cd} and Type = '02'")
    List<Map<String, Object>> queryGuideMedication(String cd);

    @Update("update SPM_SPMPatLeftRpt set SyncStatus=0 where Cd=#{cd}")
    void lockSyncFlag(String cd);

}


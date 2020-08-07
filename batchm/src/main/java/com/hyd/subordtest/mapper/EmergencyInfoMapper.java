package com.hyd.subordtest.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

@Mapper
public interface EmergencyInfoMapper {

    @Select("select * from V_Center2020_EmergencyInfo where LocalEmergencyID= #{cd} and State=1")
    Map<String, Object> queryEmergencyInfoViewByCd(String cd);

    @Select("select * from V_Center2020_EmergencyInfo where LocalEmergencyID= #{cd} and DelStatus= 'DelLogo002'")
    Map<String, Object> queryEmergencyInfoViewOfDelete(String cd);

    @Update("update SPM_EmrDeal set SyncStatus=0 where Cd=#{cd}")
    void setSyncStatus(String cd);

}

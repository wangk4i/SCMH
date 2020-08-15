package com.hyd.subordtest.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface EmergencyInfoMapper {

    @Select("select * from V_Center2020_EmergencyInfo_Test where LocalEmergencyID= #{cd} and State=1")
    Map<String, Object> queryEmergencyInfoViewByCd(String cd);

    @Select("select * from V_Center2020_EmergencyInfo where LocalEmergencyID= #{cd} and DelStatus= 'DelLogo002'")
    Map<String, Object> queryEmergencyInfoViewOfDelete(String cd);

}

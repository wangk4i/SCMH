package com.hyd.subordtest.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface EmergencyInfoMapper {
    @Select("select * from V_Center2020_EmergencyInfo_Test where ID= #{cd} ") //and EmerDealInfoId is null
    Map<String, Object> queryEmergencyInfoViewOfInsert(String cd);

    @Select("select * from V_Center2020_EmergencyInfo_Test where ID= #{cd} ") //and EmerDealInfoId is not null
    Map<String, Object> queryEmergencyInfoViewOfUpdate(String cd);

    @Select("select * from V_Center2020_EmergencyInfo_Test where ID= #{cd} and DelStatus!= 'DelLogo001'")
    Map<String, Object> queryEmergencyInfoViewOfDelete(String cd);

}

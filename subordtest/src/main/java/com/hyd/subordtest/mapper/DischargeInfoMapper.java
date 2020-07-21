package com.hyd.subordtest.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface DischargeInfoMapper {
    @Select("select * from V_Center2020_DischargeInfo_Test where ID= #{cd}") // and DischargeInformationId is null
    Map<String, Object> queryDischargeInfoViewOfInsert(String cd);

    @Select("select * from V_Center2020_DischargeInfo_Test where ID= #{cd}") // and DischargeInformationId is not null
    Map<String, Object> queryDischargeInfoViewOfUpdate(String cd);

    @Select("select * from V_Center2020_DischargeInfo_Test where ID= #{cd} and DelStatus!= 'DelLogo001'")
    Map<String, Object> queryDischargeInfoViewOfDelete(String cd);


    @Select("select * from V_Center2020_MedicationByDischargeInfo_Test where DischargeId = #{cd} and Type = '01'")
    List<Map<String, Object>> queryDrugListByDischargeId(String cd);

    @Select("select * from V_Center2020_MedicationByDischargeInfo_Test where DischargeId = #{cd} and Type = '02'")
    List<Map<String, Object>> queryGuideDrugListByDischargeId(String cd);

}


package com.hyd.subordtest.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface FollowupInfoMapper {
    @Select("select * from V_Center2020_FollowupInfo_Test where ID= #{cd} and FollowUpInformationId is null")
    Map<String, Object> queryFollowupInfoViewOfInsert(String cd);

    @Select("select * from V_Center2020_FollowupInfo_Test where ID= #{cd} and FollowUpInformationId is not null")
    Map<String, Object> queryFollowupInfoViewOfUpdate(String cd);

    @Select("select * from V_Center2020_FollowupInfo_Test where ID= #{cd} and DelStatus!= 'DelLogo001'")
    Map<String, Object> queryFollowupInfoViewOfDelete(String cd);
}

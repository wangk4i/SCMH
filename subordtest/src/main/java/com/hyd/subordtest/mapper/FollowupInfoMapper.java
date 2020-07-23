package com.hyd.subordtest.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface FollowupInfoMapper {
    @Select("select * from V_Center2020_FollowupInfo where ID= #{cd} and FollowUpInformationId is null")
    Map<String, Object> queryFollowupInfoViewOfInsert(String cd);

    @Select("select * from V_Center2020_FollowupInfo where ID= #{cd} and FollowUpInformationId is not null")
    Map<String, Object> queryFollowupInfoViewOfUpdate(String cd);

    @Select("select * from V_Center2020_FollowupInfo where ID= #{cd} and DelStatus!= 'DelLogo001'")
    Map<String, Object> queryFollowupInfoViewOfDelete(String cd);

    @Select("select * from V_Center2020_FollowupInfo_Med where FollowupId = #{cd} and Type = '01'")
    List<Map<String, Object>> queryDrugListByFollowupId(String cd);

    @Select("select * from V_Center2020_FollowupInfo_Med where FollowupId = #{cd} and Type = '02'")
    List<Map<String, Object>> queryGuideDrugListByFollowupId(String cd);

}

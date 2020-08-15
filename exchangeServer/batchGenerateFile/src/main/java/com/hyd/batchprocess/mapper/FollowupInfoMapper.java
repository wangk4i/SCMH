package com.hyd.batchprocess.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface FollowupInfoMapper {

    @Select("select * from V_Center2020_FollowupInfo where LocalFollowupID= #{cd} and State=1 ")
    Map<String, Object> queryFollowupInfoViewByCd(String cd);

    @Select("select * from V_Center2020_FollowupInfo where LocalFollowupID= #{cd} and DelStatus!= 'DelLogo001'")
    Map<String, Object> queryFollowupInfoViewOfDelete(String cd);

    @Select("select * from V_Center2020_FollowupInfo_Med where FollowupId = #{cd} and Type = '01'")
    List<Map<String, Object>> queryDrugListByFollowupId(String cd);

    @Select("select * from V_Center2020_FollowupInfo_Med where FollowupId = #{cd} and Type = '02'")
    List<Map<String, Object>> queryGuideDrugListByFollowupId(String cd);

    @Update("update SPM_SPMPatFollowup set SyncStatus=0 where Cd=#{cd}")
    void setSyncStatus(String cd);

}

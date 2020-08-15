package com.hyd.subordmq.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface FollowupInfoMapper {

    @Select("select * from V_Center2020_FollowupInfo where LocalFollowupID= #{cd} and State=1 ")
    Map<String, Object> buildFollowupInfo(String cd);

    @Select("select * from V_Center2020_FollowupInfo where LocalFollowupID= #{cd} and DelStatus!= 'DelLogo001'")
    Map<String, Object> deleteFollowupInfo(String cd);

    @Select("select * from V_Center2020_FollowupInfo_Med where FollowupId = #{cd} and Type = '01'")
    List<Map<String, Object>> queryMedication(String cd);

    @Select("select * from V_Center2020_FollowupInfo_Med where FollowupId = #{cd} and Type = '02'")
    List<Map<String, Object>> queryGuideMedication(String cd);

    @Update("update SPM_SPMPatFollowup set SyncStatus=0 where Cd=#{cd}")
    void lockSyncFlag(String cd);

}

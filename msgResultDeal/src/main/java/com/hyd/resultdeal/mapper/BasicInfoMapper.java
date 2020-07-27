package com.hyd.resultdeal.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface BasicInfoMapper {

    @Update("update SPM_SPMPatInfoMana set SyncTime= #{syncTime} and SyncError= #{syncErr} and SyncState='0' where Cd=#{cd}")
    void syncDocErrInfo(String cd, String syncTime, String syncErr);

    @Update("update SPM_SPMPatInfoMana set FIELDPK =#{id} and SyncTime= #{syncTime} where Cd=#{cd} ")
    void syncDocSuccInfo(String cd, String id, String syncTime);

    @Update("update SPM_SPMPatInfoMana set SyncTime= #{syncTime} where Cd=#{cd} ")
    void updateDocSuccInfo(String cd, String syncTime);


    @Update("update SPM_SPMPatInciRpt set SyncTime= #{syncTime} and SyncError= #{syncErr} and SyncState='0' where Cd=#{cd}")
    void syncReportErrInfo(String cd, String syncTime, String syncErr);

    @Update("update SPM_SPMPatInciRpt set FIELDPK =#{id} and SyncTime= #{syncTime} where Cd=#{cd} ")
    void syncReportSuccInfo(String cd, String id, String syncTime);

    @Update("update SPM_SPMPatInciRpt set SyncTime= #{syncTime} where Cd=#{cd} ")
    void updateReportSuccInfo(String cd, String syncTime);


    @Update("update SPM_SPMPatLeftRpt set SyncTime= #{syncTime} and SyncError= #{syncErr} and SyncState='0' where Cd=#{cd}")
    void syncDischargeErrInfo(String cd, String syncTime, String syncErr);

    @Update("update SPM_SPMPatLeftRpt set FIELDPK =#{id} and SyncTime= #{syncTime} where Cd=#{cd} ")
    void syncDischargeSuccInfo(String cd, String id, String syncTime);

    @Update("update SPM_SPMPatLeftRpt set SyncTime= #{syncTime} where Cd=#{cd} ")
    void updateDischargeSuccInfo(String cd, String syncTime);


    @Update("update SPM_SPMPatMoveOut set SyncTime= #{syncTime} and SyncError= #{syncErr} and SyncState='0' where Cd=#{cd}")
    void syncFollowupErrInfo(String cd, String syncTime, String syncErr);

    @Update("update SPM_SPMPatMoveOut set FIELDPK =#{id} and SyncTime= #{syncTime} where Cd=#{cd} ")
    void syncFollowupSuccInfo(String cd, String id, String syncTime);

    @Update("update SPM_SPMPatMoveOut set SyncTime= #{syncTime} where Cd=#{cd} ")
    void updateFollowupSuccInfo(String cd, String syncTime);


    @Update("update SPM_EmrDeal set SyncTime= #{syncTime} and SyncError= #{syncErr} and SyncState='0' where Cd=#{cd}")
    void syncEmergencyErrInfo(String cd, String syncTime, String syncErr);

    @Update("update SPM_EmrDeal set FIELDPK =#{id} and SyncTime= #{syncTime} where Cd=#{cd} ")
    void syncEmergencySuccInfo(String cd, String id, String syncTime);

    @Update("update SPM_EmrDeal set SyncTime= #{syncTime} where Cd=#{cd} ")
    void updateEmergencySuccInfo(String cd, String syncTime);
}

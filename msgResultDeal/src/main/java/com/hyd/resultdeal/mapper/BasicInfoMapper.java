package com.hyd.resultdeal.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface BasicInfoMapper {

    @Update("update SPM_SPMPatInfoMana set SyncTime= #{syncTime}, SyncError= #{syncErr}, SyncState='0' where Cd=#{cd}")
    void syncDocErrInfo(String cd, String syncTime, String syncErr);

    @Update("update SPM_SPMPatInfoMana set FIELDPK= #{id}, SyncTime= #{syncTime}, SyncStatus='1' where Cd=#{cd} ")
    void addDocSuccInfo(String cd, String id, String syncTime);

    @Update("update SPM_SPMPatInfoMana set SyncTime= #{syncTime}, SyncStatus='1' where Cd=#{cd} ")
    void syncDocSuccInfo(String cd, String syncTime);

    @Update("update SPM_SPMPatInfoMana set SyncTime= #{syncTime}, DelStatus='DelLogo001', SyncStatus='1' where Cd=#{cd}")
    void undeleteDocSuccInfo(String cd, String syncTime);

    @Update("update SPM_SPMPatInfoMana set SynTurnDeath='1', SyncStatus='1', SyncTime=#{syncTime} where Cd=#{cd}")
    void turnDeathSuccInfo(String cd, String syncTime);


    @Update("update SPM_SPMPatInciRpt set SyncTime= #{syncTime}, SyncError= #{syncErr}, SyncState='0' where Cd=#{cd}")
    void syncReportErrInfo(String cd, String syncTime, String syncErr);

    @Update("update SPM_SPMPatInciRpt set FIELDPK= #{id}, SyncTime= #{syncTime}, SyncStatus='1' where Cd=#{cd} ")
    void addReportSuccInfo(String cd, String id, String syncTime);

    @Update("update SPM_SPMPatInciRpt set SyncTime= #{syncTime}, SyncStatus='1' where Cd=#{cd} ")
    void syncReportSuccInfo(String cd, String syncTime);


    @Update("update SPM_SPMPatLeftRpt set SyncError= #{syncErr}, SyncTime= #{syncTime}, SyncStatus='0' where Cd=#{cd}")
    void syncDischargeErrInfo(String cd, String syncTime, String syncErr);

    @Update("update SPM_SPMPatLeftRpt set FIELDPK= #{id} , SyncTime= #{syncTime}, SyncStatus='1' where Cd=#{cd} ")
    void addDischargeSuccInfo(String cd, String id, String syncTime);

    @Update("update SPM_SPMPatLeftRpt set SyncTime= #{syncTime}, SyncStatus='1' where Cd=#{cd} ")
    void syncDischargeSuccInfo(String cd, String syncTime);


    @Update("update SPM_SPMPatMoveOut set SyncTime= #{syncTime} , SyncError= #{syncErr} , SyncState='0' where Cd=#{cd}")
    void syncFollowupErrInfo(String cd, String syncTime, String syncErr);

    @Update("update SPM_SPMPatMoveOut set FIELDPK= #{id} , SyncTime= #{syncTime}, SyncStatus='1' where Cd=#{cd} ")
    void addFollowupSuccInfo(String cd, String id, String syncTime);

    @Update("update SPM_SPMPatMoveOut set SyncTime= #{syncTime}, SyncStatus='1' where Cd=#{cd} ")
    void syncFollowupSuccInfo(String cd, String syncTime);


    @Update("update SPM_EmrDeal set SyncTime= #{syncTime} , SyncError= #{syncErr} , SyncState='0' where Cd=#{cd}")
    void syncEmergencyErrInfo(String cd, String syncTime, String syncErr);

    @Update("update SPM_EmrDeal set FIELDPK= #{id} , SyncTime= #{syncTime}, SyncStatus='1' where Cd=#{cd} ")
    void addEmergencySuccInfo(String cd, String id, String syncTime);

    @Update("update SPM_EmrDeal set SyncTime= #{syncTime}, SyncStatus='1' where Cd=#{cd} ")
    void syncEmergencySuccInfo(String cd, String syncTime);
}

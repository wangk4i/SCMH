package com.hyd.endurance.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface PatInfoMapper {

    @Update("update SPM_SPMPatInfoMana set SyncStatus='1', SyncTime= convert(varchar(20), getdate(), 120), SyncError= #{syncErr} where Cd=#{cd}")
    void syncErrInfo(String cd, String syncErr);

    @Update("update SPM_SPMPatInfoMana set SyncStatus='1', SyncTime= convert(varchar(20), getdate(), 120) where Cd=#{cd} ")
    void syncSuccInfo(String cd);

    @Update("update SPM_SPMPatInfoMana set SyncStatus='1', SyncTime= convert(varchar(20), getdate(), 120), FIELDPK= #{id} where Cd=#{cd} ")
    void addSuccInfo(String cd, String id);

    @Update("update SPM_SPMPatInfoMana set SyncStatus='1', SyncTime= convert(varchar(20), getdate(), 120), DelStatus='DelLogo001' where Cd=#{cd}")
    void restoreSuccInfo(String cd);

    @Update("update SPM_SPMPatInfoMana set SyncStatus='1', SyncTime= convert(varchar(20), getdate(), 120), SynTurnDeath='1' where Cd=#{cd}")
    void turnDeathSuccInfo(String cd);

    @Update("update SPM_SPMPatInfoMana set SyncStatus='1', SyncTime= convert(varchar(20), getdate(), 120), InspectYear= year(getdate()) where Cd=#{cd}")
    void inspectYearSuccInfo(String cd);


}

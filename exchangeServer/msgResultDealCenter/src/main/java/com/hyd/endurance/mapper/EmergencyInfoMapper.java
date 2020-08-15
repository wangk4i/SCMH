package com.hyd.endurance.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/10 17:46
 */
@Mapper
public interface EmergencyInfoMapper {
    @Update("update SPM_EmrDeal set SyncStatus='1', SyncTime= convert(varchar(20), getdate(), 120), SyncError= #{syncErr} ,  where Cd=#{cd}")
    void syncEmergencyErrInfo(String cd, String syncErr);

    @Update("update SPM_EmrDeal set SyncStatus='1', SyncTime= convert(varchar(20), getdate(), 120) where Cd=#{cd} ")
    void syncEmergencySuccInfo(String cd);

    @Update("update SPM_EmrDeal set SyncStatus='1', SyncTime= convert(varchar(20), getdate(), 120), FIELDPK= #{id} where Cd=#{cd} ")
    void addEmergencySuccInfo(String cd, String id);

}

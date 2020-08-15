package com.hyd.endurance.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/10 17:46
 */
@Mapper
public interface FollowupInfoMapper {
    @Update("update SPM_SPMPatMoveOut set SyncStatus='1', SyncTime= convert(varchar(20), getdate(), 120), SyncError= #{syncErr} where Cd=#{cd}")
    void syncFollowupErrInfo(String cd, String syncErr);

    @Update("update SPM_SPMPatMoveOut set SyncStatus='1', SyncTime= convert(varchar(20), getdate(), 120) where Cd=#{cd} ")
    void syncFollowupSuccInfo(String cd);

    @Update("update SPM_SPMPatMoveOut set SyncStatus='1', SyncTime= convert(varchar(20), getdate(), 120), FIELDPK= #{id} where Cd=#{cd} ")
    void addFollowupSuccInfo(String cd, String id);

}

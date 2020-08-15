package com.hyd.endurance.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/10 17:45
 */
@Mapper
public interface DischargeInfoMapper {
    @Update("update SPM_SPMPatLeftRpt set SyncStatus='1', SyncTime= convert(varchar(20), getdate(), 120), SyncError= #{syncErr} where Cd=#{cd}")
    void syncDischargeErrInfo(String cd, String syncErr);

    @Update("update SPM_SPMPatLeftRpt set SyncStatus='1'，SyncTime= convert(varchar(20), getdate(), 120) where Cd=#{cd} ")
    void syncDischargeSuccInfo(String cd);

    @Update("update SPM_SPMPatLeftRpt set SyncStatus='1', SyncTime= convert(varchar(20), getdate(), 120), FIELDPK= #{id} where Cd=#{cd} ")
    void addDischargeSuccInfo(String cd, String id);

}

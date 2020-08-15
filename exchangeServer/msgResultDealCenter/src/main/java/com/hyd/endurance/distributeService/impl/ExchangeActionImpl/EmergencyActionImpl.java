package com.hyd.endurance.distributeService.impl.ExchangeActionImpl;

import com.hyd.endurance.distributeService.ExchangeAction.EmergencyAction;
import com.hyd.endurance.domain.ActInfoDO;
import com.hyd.endurance.mapper.EmergencyInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/10 18:04
 */
@Service
public class EmergencyActionImpl implements EmergencyAction {

    @Autowired(required = false)
    private EmergencyInfoMapper mapper;

    @Override
    public void EmergencyAdd(ActInfoDO info) {
        if (!info.getBizInfo().getResult()){
            // 数据库记录同步信息
            mapper.syncEmergencyErrInfo(info.getMsgInfo().getId(), info.getBizInfo().getDesc());
            return ;
        }
        String fieldPk = info.getBizInfo().getId();
        mapper.addEmergencySuccInfo(info.getMsgInfo().getId(), fieldPk);
    }

    @Override
    public void EmergencyUpdate(ActInfoDO info) {
        if (!info.getBizInfo().getResult()){
            // 数据库记录同步信息
            mapper.syncEmergencyErrInfo(info.getMsgInfo().getId(), info.getBizInfo().getDesc());
            return ;
        }
        mapper.syncEmergencySuccInfo(info.getMsgInfo().getId());
    }

    @Override
    public void EmergencyDelete(ActInfoDO info) {
        if (!info.getBizInfo().getResult()){
            // 数据库记录同步信息
            mapper.syncEmergencyErrInfo(info.getMsgInfo().getId(), info.getBizInfo().getDesc());
            return ;
        }
        mapper.syncEmergencySuccInfo(info.getMsgInfo().getId());
    }
}

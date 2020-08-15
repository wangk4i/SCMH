package com.hyd.endurance.distributeService.impl.ExchangeActionImpl;

import com.hyd.endurance.distributeService.ExchangeAction.PatInfoAction;
import com.hyd.endurance.domain.ActInfoDO;
import com.hyd.endurance.mapper.PatInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/10 18:02
 */
@Service
public class PatInfoActionImpl implements PatInfoAction {

    @Autowired(required = false)
    private PatInfoMapper mapper;

    @Override
    public void PatInfoAdd(ActInfoDO info) {
        if (!info.getBizInfo().getResult()){
            // 数据库记录同步信息
            mapper.syncErrInfo(info.getMsgInfo().getId(), info.getBizInfo().getDesc());
            return ;
        }
        String fieldPk = info.getBizInfo().getId();
        mapper.addSuccInfo(info.getMsgInfo().getId(), fieldPk);
    }

    @Override
    public void PatInfoUpdate(ActInfoDO info) {
        if (!info.getBizInfo().getResult()){
            // 数据库记录同步信息
            mapper.syncErrInfo(info.getMsgInfo().getId(), info.getBizInfo().getDesc());
            return ;
        }
        mapper.syncSuccInfo(info.getMsgInfo().getId());
    }

    @Override
    public void PatInfoDelete(ActInfoDO info) {
        if (!info.getBizInfo().getResult()){
            // 数据库记录同步信息
            mapper.syncErrInfo(info.getMsgInfo().getId(), info.getBizInfo().getDesc());
            return ;
        }
        mapper.syncSuccInfo(info.getMsgInfo().getId());
    }

    @Override
    public void PatInfoInsepct(ActInfoDO info) {
        if (!info.getBizInfo().getResult()){
            // 数据库记录同步信息
            mapper.syncErrInfo(info.getMsgInfo().getId(), info.getBizInfo().getDesc());
            return ;
        }
        mapper.inspectYearSuccInfo(info.getMsgInfo().getId());
    }

    @Override
    public void PatInfoTurnDeath(ActInfoDO info) {
        if (!info.getBizInfo().getResult()){
            // 数据库记录同步信息
            mapper.syncErrInfo(info.getMsgInfo().getId(), info.getBizInfo().getDesc());
            return ;
        }
        mapper.turnDeathSuccInfo(info.getMsgInfo().getId());
    }

    @Override
    public void PatInfoRestore(ActInfoDO info) {
        if (!info.getBizInfo().getResult()){
            // 数据库记录同步信息
            mapper.syncErrInfo(info.getMsgInfo().getId(), info.getBizInfo().getDesc());
            return ;
        }
        mapper.restoreSuccInfo(info.getMsgInfo().getId());
    }


}

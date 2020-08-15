package com.hyd.endurance.distributeService.impl.ExchangeActionImpl;

import com.hyd.endurance.distributeService.ExchangeAction.LeftCardAction;
import com.hyd.endurance.domain.ActInfoDO;
import com.hyd.endurance.mapper.DischargeInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/10 18:04
 */
@Service
public class LeftCardActionImpl implements LeftCardAction {

    @Autowired(required = false)
    private DischargeInfoMapper mapper;

    @Override
    public void LeftCardAdd(ActInfoDO info) {
        // 返回值为失败时，xml,txt 转移到failed文件夹，返回交换消息体
        if (!info.getBizInfo().getResult()){
            // 数据库记录同步信息
            mapper.syncDischargeErrInfo(info.getMsgInfo().getId(), info.getBizInfo().getDesc());
            return ;
        }
        String fieldPk = info.getBizInfo().getId();
        mapper.addDischargeSuccInfo(info.getMsgInfo().getId(), fieldPk);
    }

    @Override
    public void LeftCardUpdate(ActInfoDO info) {
        // 返回值为失败时，xml,txt 转移到failed文件夹，返回交换消息体
        if (!info.getBizInfo().getResult()){
            // 数据库记录同步信息
            mapper.syncDischargeErrInfo(info.getMsgInfo().getId(), info.getBizInfo().getDesc());
            return ;
        }
        mapper.syncDischargeSuccInfo(info.getMsgInfo().getId());
    }

    @Override
    public void LeftCardDelete(ActInfoDO info) {
        // 返回值为失败时，xml,txt 转移到failed文件夹，返回交换消息体
        if (!info.getBizInfo().getResult()){
            // 数据库记录同步信息
            mapper.syncDischargeErrInfo(info.getMsgInfo().getId(), info.getBizInfo().getDesc());
            return ;
        }
        mapper.syncDischargeSuccInfo(info.getMsgInfo().getId());
    }
}

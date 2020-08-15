package com.hyd.endurance.distributeService.impl.ExchangeActionImpl;

import com.hyd.endurance.distributeService.ExchangeAction.FollowupAction;
import com.hyd.endurance.domain.ActInfoDO;
import com.hyd.endurance.mapper.FollowupInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/10 18:04
 */
@Service
public class FollowupActionImpl implements FollowupAction {

    @Autowired(required = false)
    private FollowupInfoMapper mapper;


    @Override
    public void FollowupAdd(ActInfoDO info) {
        // 返回值为失败时，xml,txt 转移到failed文件夹，返回交换消息体
        if (!info.getBizInfo().getResult()){
            // 数据库记录同步信息
            mapper.syncFollowupErrInfo(info.getMsgInfo().getId(), info.getBizInfo().getDesc());
            return ;
        }
        String fieldPk = info.getBizInfo().getId();
        mapper.addFollowupSuccInfo(info.getMsgInfo().getId(), fieldPk);
    }

    @Override
    public void FollowupUpdate(ActInfoDO info) {
        // 返回值为失败时，xml,txt 转移到failed文件夹，返回交换消息体
        if (!info.getBizInfo().getResult()){
            // 数据库记录同步信息
            mapper.syncFollowupErrInfo(info.getMsgInfo().getId(), info.getBizInfo().getDesc());
            return ;
        }
        mapper.syncFollowupSuccInfo(info.getMsgInfo().getId());
    }

    @Override
    public void FollowupDelete(ActInfoDO info) {
        // 返回值为失败时，xml,txt 转移到failed文件夹，返回交换消息体
        if (!info.getBizInfo().getResult()){
            // 数据库记录同步信息
            mapper.syncFollowupErrInfo(info.getMsgInfo().getId(), info.getBizInfo().getDesc());
            return ;
        }
        mapper.syncFollowupSuccInfo(info.getMsgInfo().getId());
    }
}

package com.hyd.endurance.distributeService.impl.ExchangeActionImpl;

import com.hyd.endurance.distributeService.ExchangeAction.ReportCardAction;
import com.hyd.endurance.domain.ActInfoDO;
import com.hyd.endurance.mapper.ReportInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/10 18:04
 */
@Service
public class ReportCardActionImpl implements ReportCardAction {

    @Autowired(required = false)
    private ReportInfoMapper mapper;

    @Override
    public void ReportAdd(ActInfoDO info) {
        if (!info.getBizInfo().getResult()){
            // 数据库记录同步信息
            mapper.syncReportErrInfo(info.getMsgInfo().getId(), info.getBizInfo().getDesc());
            return ;
        }
        String fieldPk = info.getBizInfo().getId();
        mapper.addReportSuccInfo(info.getMsgInfo().getId(), fieldPk);
    }

    @Override
    public void ReportUpdate(ActInfoDO info) {
        if (!info.getBizInfo().getResult()){
            // 数据库记录同步信息
            mapper.syncReportErrInfo(info.getMsgInfo().getId(), info.getBizInfo().getDesc());
            return ;
        }
        mapper.syncReportSuccInfo(info.getMsgInfo().getId());
    }

    @Override
    public void ReportDelete(ActInfoDO info) {
        if (!info.getBizInfo().getResult()) {
            // 数据库记录同步信息
            mapper.syncReportErrInfo(info.getMsgInfo().getId(), info.getBizInfo().getDesc());
            return;
        }
        mapper.syncReportSuccInfo(info.getMsgInfo().getId());
    }
}

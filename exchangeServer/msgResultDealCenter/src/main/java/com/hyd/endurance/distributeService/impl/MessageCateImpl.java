package com.hyd.endurance.distributeService.impl;

import com.hyd.endurance.distributeService.ExchangeType;
import com.hyd.endurance.distributeService.MessageCate;
import com.hyd.endurance.domain.ActInfoDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageCateImpl implements MessageCate {
    @Autowired
    ExchangeType exchangeType;

    @Override
    public void ExchangeCate(ActInfoDO info) {

        switch (info.getMsgInfo().getMsgtype()){
            case 1:
                exchangeType.PatInfoOperation(info);
                break;
            case 2:
                exchangeType.CaseReoprtOperation(info);
                break;
            case 3:
                exchangeType.LeftCardOperation(info);
                break;
            case 4:
                exchangeType.FollowupOperation(info);
                break;
            case 5:
                exchangeType.EmergencyOperation(info);
                break;
            default:
                break;
        }
    }
}

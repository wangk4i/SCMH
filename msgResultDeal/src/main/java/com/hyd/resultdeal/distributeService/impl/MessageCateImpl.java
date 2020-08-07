package com.hyd.resultdeal.distributeService.impl;

import com.hyd.resultdeal.distributeService.ExchangeType;
import com.hyd.resultdeal.distributeService.MessageCate;
import com.hyd.resultdeal.domain.MessageInfoDO;
import com.hyd.resultdeal.domain.ReturnMsgDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MessageCateImpl implements MessageCate {
    @Autowired
    ExchangeType exchangeType;

    @Override
    public void ExchangeCate(MessageInfoDO info, ReturnMsgDO resultMsg) {

        switch (info.getMsgtype()){
            case 1:
                exchangeType.DocumentOperation(info,resultMsg);
                break;
            case 2:
                exchangeType.CaseReoprtOperation(info,resultMsg);
                break;
            case 3:
                exchangeType.DischargeOperation(info,resultMsg);
                break;
            case 4:
                exchangeType.FollowupOperation(info,resultMsg);
                break;
            case 5:
                exchangeType.EmergencyOperation(info,resultMsg);
                break;
            default:
                break;
        }
    }
}

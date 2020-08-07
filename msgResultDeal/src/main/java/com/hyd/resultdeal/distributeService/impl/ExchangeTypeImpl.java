package com.hyd.resultdeal.distributeService.impl;

import com.hyd.resultdeal.distributeService.ExchangeAction;
import com.hyd.resultdeal.distributeService.ExchangeType;
import com.hyd.resultdeal.domain.MessageInfoDO;
import com.hyd.resultdeal.domain.ReturnMsgDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ExchangeTypeImpl implements ExchangeType {

    @Autowired
    ExchangeAction action;


    @Override
    public void DocumentOperation(MessageInfoDO info, ReturnMsgDO resultMsg) {
        switch (info.getMsgaction()){
            case 1:
                action.DocumentAdd(info, resultMsg);
                break;
            case 2:
                action.DocumentUpdate(info, resultMsg);
                break;
            case 3:
                action.DocumentDelete(info, resultMsg);
                break;
            case 4:
                action.DocumentUndelete(info, resultMsg);
                break;
            case 5:
                action.DocumentDeclaredeath(info, resultMsg);
                break;
            default:
                break;
        }

    }


    @Override
    public void CaseReoprtOperation(MessageInfoDO info, ReturnMsgDO resultMsg) {
        switch (info.getMsgaction()) {
            case 1:
                action.ReportAdd(info, resultMsg);
                break;
            case 2:
                action.ReportUpdate(info, resultMsg);
                break;
            case 3:
                action.ReportDelete(info, resultMsg);
                break;
            default:
                break;
        }

    }

    @Override
    public void DischargeOperation(MessageInfoDO info, ReturnMsgDO resultMsg) {
        switch (info.getMsgaction()) {
            case 1:
                action.DischargeAdd(info, resultMsg);
                break;
            case 2:
                action.DischargeUpdate(info, resultMsg);
                break;
            case 3:
                action.DischargeDelete(info, resultMsg);
                break;
            default:
                break;
        }

    }

    @Override
    public void FollowupOperation(MessageInfoDO info, ReturnMsgDO resultMsg) {
        switch (info.getMsgaction()) {
            case 1:
                action.FollowupAdd(info, resultMsg);
                break;
            case 2:
                action.FollowupUpdate(info, resultMsg);
                break;
            case 3:
                action.FollowupDelete(info, resultMsg);
                break;
            default:
                break;
        }

    }

    @Override
    public void EmergencyOperation(MessageInfoDO info, ReturnMsgDO resultMsg) {
        switch (info.getMsgaction()) {
            case 1:
                action.EmergencyAdd(info, resultMsg);
                break;
            case 2:
                action.EmergencyUpdate(info, resultMsg);
                break;
            case 3:
                action.EmergencyDelete(info, resultMsg);
                break;
            default:
                break;
        }

    }


}

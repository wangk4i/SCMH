package com.hyd.resultdeal.distributeService;

import com.hyd.resultdeal.domain.MessageInfoDO;
import com.hyd.resultdeal.domain.ReturnMsgDO;
import org.springframework.stereotype.Repository;

@Repository
public class ExchangeTypeImpl implements ExchangeType {

    ExchangeAction action = new ExchangeActionImpl();


    @Override
    public void DocumentOperation(MessageInfoDO info, ReturnMsgDO resultMsg) {
        switch (info.getMsgaction()){
            case 1:
                action.DocumentAdd(info, resultMsg);
                break;
            case 2:
                action.DocumentUpdate();
                break;
            case 3:
                action.DocumentDelete();
                break;
            case 4:
                action.DocumentUndelete();
                break;
            case 5:
                action.DocumentDeclaredeath();
                break;
            default:
                break;
        }

    }


    @Override
    public void CaseReoprtOperation(MessageInfoDO info, ReturnMsgDO resultMsg) {

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

    }

    @Override
    public void EmergencyOperation(MessageInfoDO info, ReturnMsgDO resultMsg) {

    }


}

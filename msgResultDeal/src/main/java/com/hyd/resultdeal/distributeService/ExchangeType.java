package com.hyd.resultdeal.distributeService;

import com.hyd.resultdeal.domain.MessageInfoDO;
import com.hyd.resultdeal.domain.ReturnMsgDO;

public interface ExchangeType {

    void DocumentOperation(MessageInfoDO info, ReturnMsgDO resultMsg);

    void CaseReoprtOperation(MessageInfoDO info, ReturnMsgDO resultMsg);

    void DischargeOperation(MessageInfoDO info, ReturnMsgDO resultMsg);

    void FollowupOperation(MessageInfoDO info, ReturnMsgDO resultMsg);

    void EmergencyOperation(MessageInfoDO info, ReturnMsgDO resultMsg);
}

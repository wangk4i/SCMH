package com.hyd.resultdeal.distributeService;

import com.hyd.resultdeal.domain.MessageInfoDO;
import com.hyd.resultdeal.domain.ReturnMsgDO;

public interface ExchangeAction {

    void DocumentAdd(MessageInfoDO info, ReturnMsgDO resultMsg);

    void DocumentUpdate(MessageInfoDO info, ReturnMsgDO resultMsg);

    void DocumentDelete(MessageInfoDO info, ReturnMsgDO resultMsg);

    void DocumentUndelete(MessageInfoDO info, ReturnMsgDO resultMsg);

    void DocumentDeclaredeath(MessageInfoDO info, ReturnMsgDO resultMsg);


    void ReportAdd(MessageInfoDO info, ReturnMsgDO resultMsg);

    void ReportUpdate(MessageInfoDO info, ReturnMsgDO resultMsg);

    void ReportDelete(MessageInfoDO info, ReturnMsgDO resultMsg);


    void DischargeAdd(MessageInfoDO info, ReturnMsgDO resultMsg);

    void DischargeUpdate(MessageInfoDO info, ReturnMsgDO resultMsg);

    void DischargeDelete(MessageInfoDO info, ReturnMsgDO resultMsg);


    void FollowupAdd(MessageInfoDO info, ReturnMsgDO resultMsg);

    void FollowupUpdate(MessageInfoDO info, ReturnMsgDO resultMsg);

    void FollowupDelete(MessageInfoDO info, ReturnMsgDO resultMsg);


    void EmergencyAdd(MessageInfoDO info, ReturnMsgDO resultMsg);

    void EmergencyUpdate(MessageInfoDO info, ReturnMsgDO resultMsg);

    void EmergencyDelete(MessageInfoDO info, ReturnMsgDO resultMsg);
}

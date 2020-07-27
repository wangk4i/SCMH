package com.hyd.resultdeal.distributeService;

import com.hyd.resultdeal.domain.MessageInfoDO;
import com.hyd.resultdeal.domain.ReturnMsgDO;

public interface ExchangeAction {

    String DocumentAdd(MessageInfoDO info, ReturnMsgDO resultMsg);

    void DocumentUpdate();

    void DocumentDelete();

    void DocumentUndelete();

    void DocumentDeclaredeath();


    void ReportAdd();

    void ReportUpdate();

    void ReportDelete();


    String DischargeAdd(MessageInfoDO info, ReturnMsgDO resultMsg);

    void DischargeUpdate(MessageInfoDO info, ReturnMsgDO resultMsg);

    void DischargeDelete(MessageInfoDO info, ReturnMsgDO resultMsg);


    void FollowupAdd();

    void FollowupUpdate();

    void FollowupDelete();


    void EmergencyAdd();

    void EmergencyUpdate();

    void EmergencyDelete();
}

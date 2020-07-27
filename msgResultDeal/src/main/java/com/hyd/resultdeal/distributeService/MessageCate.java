package com.hyd.resultdeal.distributeService;

import com.hyd.resultdeal.domain.MessageInfoDO;
import com.hyd.resultdeal.domain.ReturnMsgDO;

public interface MessageCate {
    void ExchangeCate(MessageInfoDO info, ReturnMsgDO resultMsg);
}

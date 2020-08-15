package com.hyd.endurance.distributeService;

import com.hyd.endurance.domain.ActInfoDO;

public interface ExchangeType {

    void PatInfoOperation(ActInfoDO info);

    void CaseReoprtOperation(ActInfoDO info);

    void LeftCardOperation(ActInfoDO info);

    void FollowupOperation(ActInfoDO info);

    void EmergencyOperation(ActInfoDO info);
}

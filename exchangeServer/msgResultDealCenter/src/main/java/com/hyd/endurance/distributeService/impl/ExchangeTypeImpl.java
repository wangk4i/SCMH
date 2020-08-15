package com.hyd.endurance.distributeService.impl;

import com.hyd.endurance.distributeService.ExchangeAction.*;
import com.hyd.endurance.distributeService.ExchangeType;
import com.hyd.endurance.domain.ActInfoDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ExchangeTypeImpl implements ExchangeType {

    @Autowired
    PatInfoAction patAct;
    @Autowired
    ReportCardAction rptAct;
    @Autowired
    LeftCardAction leftAct;
    @Autowired
    FollowupAction follAct;
    @Autowired
    EmergencyAction emerAct;



    @Override
    public void PatInfoOperation(ActInfoDO info) {
        switch (info.getMsgInfo().getGwaction()){
            case 1:
                patAct.PatInfoAdd(info);
                break;
            case 2:
                patAct.PatInfoUpdate(info);
                break;
            case 3:
                patAct.PatInfoDelete(info);
                break;
            case 4:
                patAct.PatInfoInsepct(info);
                break;
            case 5:
                patAct.PatInfoRestore(info);
                break;
            case 6:
                patAct.PatInfoTurnDeath(info);
                break;
            default:
                break;
        }

    }


    @Override
    public void CaseReoprtOperation(ActInfoDO info) {
        switch (info.getMsgInfo().getGwaction()) {
            case 1:
                rptAct.ReportAdd(info);
                break;
            case 2:
                rptAct.ReportUpdate(info);
                break;
            case 3:
                rptAct.ReportDelete(info);
                break;
            default:
                break;
        }

    }

    @Override
    public void LeftCardOperation(ActInfoDO info) {
        switch (info.getMsgInfo().getGwaction()) {
            case 1:
                leftAct.LeftCardAdd(info);
                break;
            case 2:
                leftAct.LeftCardUpdate(info);
                break;
            case 3:
                leftAct.LeftCardDelete(info);
                break;
            default:
                break;
        }

    }

    @Override
    public void FollowupOperation(ActInfoDO info) {
        switch (info.getMsgInfo().getGwaction()) {
            case 1:
                follAct.FollowupAdd(info);
                break;
            case 2:
                follAct.FollowupUpdate(info);
                break;
            case 3:
                follAct.FollowupDelete(info);
                break;
            default:
                break;
        }

    }

    @Override
    public void EmergencyOperation(ActInfoDO info) {
        switch (info.getMsgInfo().getGwaction()) {
            case 1:
                emerAct.EmergencyAdd(info);
                break;
            case 2:
                emerAct.EmergencyUpdate(info);
                break;
            case 3:
                emerAct.EmergencyDelete(info);
                break;
            default:
                break;
        }

    }


}

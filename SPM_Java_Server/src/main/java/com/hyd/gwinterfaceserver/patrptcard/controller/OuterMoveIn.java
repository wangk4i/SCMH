package com.hyd.gwinterfaceserver.patrptcard.controller;


import com.hyd.gwinterfaceserver.patrptcard.info.PatMoveOut;
import com.hyd.gwinterfaceserver.patrptcard.info.PatReportMoveOutRequest;
import com.hyd.gwinterfaceserver.patrptcard.service.OuterMoveInService;
import com.hyd.gwinterfaceserver.patrptcard.validate.CardBusinessValider;
import com.hyd.gwinterfaceserver.patrptcard.validate.CardInputValid;
import com.hyd.gwinterfaceserver.util.GWUrl;
import com.hyd.gwinterfaceserver.util.HttpUtils;
import com.hyd.gwinterfaceserver.util.ResultInfo;
import com.hyd.system.factory.ResultFactorys;
import com.hyd.system.vo.ResultVO;
import com.hyd.system.vo.ValidateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/OuterMoveIn")
public class OuterMoveIn {
    @Autowired
    CardInputValid valider;

    @Autowired
    CardBusinessValider businessValider;

    @Autowired
    OuterMoveInService outerMoveInService;


    /**
     * 患者报告卡接收不建档(本级1)
     * 1.入参校验
     * 2.。。。。。。。。。
     * 3.判断当前这份报告在我省流转记录中的最初一条是否为外省流转过来，
     * 4.如为外省流转过来报告卡，查询我省的患者信息，调用接收不建档接口，
     * 5.接收失败，返回国网错误消息用户，成功回写国网id到db.
     */
    @PostMapping("/ResponseMoveIn")
    ResultVO ResponseMoveInBj1(@RequestBody PatMoveOut patMoveOut) {
        //入参校验
        ValidateVO validresult = valider.PatMoveOutValid(patMoveOut);
        if (validresult.isError()) {
            return ResultFactorys.error(validresult.getErrMessage());
        }
        //入参业务校验
        ValidateVO validateVO = businessValider.PatMoveOutValid(patMoveOut);
        //业务逻辑
        outerMoveInService.ResponseMoveInBj1(validateVO);
        return ResultFactorys.success();
    }


    /**
     * 患者报告卡外省迁入后分配至社区(本级1)
     */
    @PostMapping("/PatReportDistributionBJ")
    ResultVO PatReportDistributionBJ(@RequestBody PatReportMoveOutRequest input) {
        //入参数据检查
        ValidateVO validresult = valider.PatReportDistributionBJValid(input);
        if (validresult.isError()) {
            return ResultFactorys.error(validresult.getErrMessage());
        }
        //业务逻辑
        outerMoveInService.PatReportDistributionBJ(input);
        return ResultFactorys.success();
    }


    /**
     * 接收建档
     * 患者报告卡迁入社区新建患者基本信息后终结国网流转记录，国网报错就记日志，有问题根据日志来恢复（直报1）
     * 1.入参校验
     * 2.。。。。。。。。。
     * 3.（新增档案，档案合并），判断当前这份报告在我省流转记录中的最初一条是否为外省流转过来，
     * 4.如为外省流转过来报告卡，查询我省的患者信息，构建接收建档国网接口json数据，
     * 5.接收失败，返回国网错误消息用户，成功回写国网id到db.
     */
    @PostMapping("/PatReportAcceptMoveIn")
    ResultVO PatReportAcceptMoveInZb1(@RequestBody PatReportMoveOutRequest input) {
        //入参数据检查
        ValidateVO validresult = valider.PatReportAcceptMoveInValid(input);
        if (validresult.isError()) {
            return ResultFactorys.error(validresult.getErrMessage());
        }
        //入参业务检查
        ValidateVO validateVO = businessValider.PatReportAcceptMoveInValid(input);
        //业务逻辑
        outerMoveInService.PatReportAcceptMoveInZb1(input, validateVO);
        return ResultFactorys.success();
    }


}

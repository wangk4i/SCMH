package com.hyd.gwinterfaceserver.patrptcard.controller;


import com.hyd.gwinterfaceserver.patrptcard.info.CallBackMoveInput;
import com.hyd.gwinterfaceserver.patrptcard.info.PatInfoCallBackMoveRequest;
import com.hyd.gwinterfaceserver.patrptcard.info.PatReportMoveOutRequest;
import com.hyd.gwinterfaceserver.patrptcard.service.OuterMoveOutService;
import com.hyd.gwinterfaceserver.patrptcard.validate.CardBusinessValider;
import com.hyd.gwinterfaceserver.patrptcard.validate.CardInputValid;
import com.hyd.gwinterfaceserver.patrptcard.vo.RetValidr;
import com.hyd.system.aspect.handle.ExceptionHandle;
import com.hyd.system.exceptclass.BusineExceptions;
import com.hyd.system.exceptclass.SqlException;
import com.hyd.system.factory.ResultFactorys;
import com.hyd.system.vo.ResultVO;
import com.hyd.system.vo.ValidateVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/OuterMoveOut")
public class OuterMoveOut {


    @Autowired
    CardInputValid valider;

    @Autowired
    CardBusinessValider businessValider;

    @Autowired
    OuterMoveOutService outerMoveOutService;

    /**
     * 患者报告卡迁出外省(直报2)
     * 1.入参校验（判断地区肯定是区县）
     * 2.判断这个报告卡的状态 是否存在 同步有错未同步，直接提示消息给用户
     * 3.判断这个迁出机构的类型 社区、区县 OrgType 是否为区县，如果选择区县没有选择机构的时候默认赋值9个0；
     * 4.判断选择社区时候的机构下是否有基层直报，无提示消息给用户
     * 5.构建国网迁出接口json数据，调用封装接口发送数据，成功之后执行第6步
     * 6.回写省网（写入流转信息---直接条用存储过程） 如省网回写发生异常，调用报告卡回收接口，回滚操作
     */
    @PostMapping("/PatReportMoveOut")
    ResultVO PatReportMoveOutByZb2(@RequestBody PatReportMoveOutRequest input) {
        try {
            //入参数据检查
            ValidateVO inputValid = valider.PatReportMoveOutRequestValid(input);
            if (inputValid.isError()) {
                return ResultFactorys.error(inputValid.getErrMessage());
            }
            //入参数据业务检查  并返回处理后的数据
            ValidateVO retValidr = businessValider.PatReportMoveOutRequestValid(input);
            //业务逻辑
            outerMoveOutService.PatReportMoveOutByZb2(input);
            return ResultFactorys.success(retValidr.getData());
        } catch (BusineExceptions e) {  //入参数据业务检查错误
            return ResultFactorys.error(e.getMsg());
        } catch (SqlException e) {   //业务逻辑错误
            return ResultFactorys.error(e.getMsg());
        }
    }



    /**
     * 患者报告卡迁出外省(本级1)
     * 1.入参校验
     * 2.根据流转编码获取报告卡信息，判断报告的状态 是否存在 同步有错未同步，直接提示消息给用户
     * 3.判断这个迁出机构的类型 社区、区县 OrgType 是否为区县，如果选择区县没有选择机构的时候默认赋值9个0；
     * 4.构建国网迁出接口json数据，调用封装接口发送数据，成功之后执行第5步
     * 5.回写省网（写入流转信息---直接条用存储过程） 如省网回写发生异常，调用报告卡回收接口，回滚操作
     */
    @PostMapping("/PatReportMoveOutBJ")
    ResultVO PatReportMoveOutBJ(@RequestBody PatReportMoveOutRequest input) {
        try {
            //入参数据检查
            ValidateVO validresult = valider.PatReportDistributionBJValid(input);
            if (validresult.isError()) {
                return ResultFactorys.error(validresult.getErrMessage());
            }
            //入参数据业务检查
            ValidateVO validateVO = businessValider.PatReportMoveOutBJ(input);
            //业务逻辑
            outerMoveOutService.PatReportMoveOutBJ(input);
            return ResultFactorys.success(validateVO.getData());
        } catch (BusineExceptions e) {  //入参数据业务检查错误
            return ResultFactorys.error(e.getMsg());
        } catch (SqlException e) {   //业务逻辑错误
            return ResultFactorys.error(e.getMsg());
        }
    }

    /**
     * 患者报告卡回收(直报2)  患者信息回收（直报1）
     */
    @PostMapping("/PatInfoCallBackMove")
    ResultVO PatInfoCallBackMoveZb2(@RequestBody PatInfoCallBackMoveRequest input) {
        try {
            //入参判断
            ValidateVO validresult = valider.PatInfoCallBackMoveRequestValid(input);
            if (validresult.isError()) {
                return ResultFactorys.error(validresult.getErrMessage());
            }
            //入参业务判断
            businessValider.PatInfoCallBackMoveRequestValid(input);
            //业务逻辑
            outerMoveOutService.PatInfoCallBackMoveZb2(input);
            return ResultFactorys.success();
        } catch (BusineExceptions e) {  //入参数据业务检查错误
            return ResultFactorys.error(e.getMsg());
        } catch (SqlException e) {   //业务逻辑错误
            return ResultFactorys.error(e.getMsg());
        }
    }


    /**
     * 迁出收回方法(本级1) (直报1)
     * 1.入参校验
     * 2.判断报告卡流转记录的国网状态 （未同步国家 直接提示消息给用户），本地迁出记录的数据状态；
     * 3.构建国网json数据，条用回收接口，失败返回国网消息给用户，成功的时候更新流转记录
     *
     * @param
     * @return
     */
    @PostMapping("/callBackMove")
    ResultVO callBackMoveZb1Bj1(@RequestBody CallBackMoveInput input) {
        //判断入参
        ValidateVO validresult = valider.callBackMove(input);
        if (validresult.isError()) {
            return ResultFactorys.error(validresult.getErrMessage());
        }
        //业务逻辑
        outerMoveOutService.callBackMoveZb1Bj1(input);
        return ResultFactorys.success();
    }
}

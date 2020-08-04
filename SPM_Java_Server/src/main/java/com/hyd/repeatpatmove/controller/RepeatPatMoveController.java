package com.hyd.repeatpatmove.controller;

import com.hyd.patInfomanagement.info.ReportcardsQueryinfo;
import com.hyd.repeatpatmove.exceptclass.BizException;
import com.hyd.repeatpatmove.info.RepeatPatApplyInfo;
import com.hyd.repeatpatmove.service.RepeatPatMoveService;
import com.hyd.repeatpatmove.validate.RepeatMoveValid;
import com.hyd.repeatpatmove.vo.ValidateVO;
import com.hyd.selectquery.service.ThreelevelService;
import com.hyd.system.factory.ResultFactory;
import com.hyd.system.info.ExtInfoObj;
import com.hyd.system.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangkai
 * @version 1.0
 * @date 2020/8/3 11:18
 */
@RestController
@RequestMapping("/RepeatPatMove")
public class RepeatPatMoveController {
    @Autowired
    ThreelevelService threelevelService;

    @Autowired
    RepeatMoveValid valid;
    @Autowired
    RepeatPatMoveService server;

    /**
     * 获取下级地区
     * @return
     */
    @PostMapping("/Province")
    ResultVO initProvince(@RequestBody ReportcardsQueryinfo reportcardsQueryinfo){
        return ResultFactory.success(threelevelService.initProvince(reportcardsQueryinfo));
    };

    /**
     * 初始化下拉
     * @return
     */
    @PostMapping("/InitSelect")
    ResultVO initAllSelect(@RequestBody ExtInfoObj extInfoObj){
        return ResultFactory.success(threelevelService.initAllSelect(extInfoObj));
    };

    /**
     * 获取机构
     */
    @PostMapping("/Organ")
    ResultVO initOrgan(@RequestBody ReportcardsQueryinfo reportcardsQueryinfo){
        return ResultFactory.success(threelevelService.initOrgan(reportcardsQueryinfo));
    }


    /**
     * 跨省重复患者迁出申请 （直报1）
     * （申请前，调用国网查重接口查询重复患者信息后才能执行下面的操作）
     * 1.参数校验
     * 2.判断患者信息国网状态，判断是否有未完结的流转记录，
     * 3.构建重复迁移患者数据（判断我省是否存在该患者信息，如无直接发起申请，回写DB），
     * 4.患者基本信息存在，管理地区不等于当前并且管理机构不等于省内，判断国网的主键和本地主键是否相同
     * 5.主键相同发起申请，传入构建数据。回写DB。不同提示用户信息。
     * 6.患者基本信息存在，在我省管理提示用户无法发起重复迁移（和4相反）
     */
    @PostMapping("/RepeatPatMoveOutApply")
    ResultVO RepeatPatMoveOutApply(@RequestBody RepeatPatApplyInfo input){
        ResultVO result = new ResultVO();
        try {
            // 入参校验
            ValidateVO inputValid = valid.RepeatInfoValid(input);
            if (inputValid.isError()){
                return ResultFactory.error(inputValid.getErrMessage());
            }
            result = server.RepeatPatMoveOutApply(input);

        } catch (BizException bex){

            return ResultFactory.error(bex.getErrMsg());
        } catch (Exception unknown){
            return ResultFactory.error(unknown.getMessage());
        }
        return result;
    }





}

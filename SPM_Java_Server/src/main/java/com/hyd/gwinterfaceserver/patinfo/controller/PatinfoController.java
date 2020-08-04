package com.hyd.gwinterfaceserver.patinfo.controller;

import com.hyd.gwinterfaceserver.patinfo.info.PatInfoMoveOutRequest;
import com.hyd.gwinterfaceserver.patinfo.service.PatinfoMoveOutService;
import com.hyd.system.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xieshuai on 2020/8/1 下午 3:31
 *  患者基本信息管理
 */
@RestController
public class PatinfoController {

    @Autowired
    private PatinfoMoveOutService moveOutService;


    /**
     * 患者基本信息迁出
     * @return
     */
    @RequestMapping("/PatInfoMoveOut")
    public ResultVO patInfoMoveOut(@RequestBody PatInfoMoveOutRequest request){
        return moveOutService.patinfoMoveOutByExternal(request);
    }
}

package com.hyd.monthreport.controller;

import com.hyd.monthreport.info.*;
import com.hyd.monthreport.service.MonthRepService;
import com.hyd.system.factory.ResultFactory;
import com.hyd.system.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xieshuai on 2020/4/7 17:02
 * 月报授权controller
 */

@RestController
@RequestMapping("/MonthRep")
public class MonthRepController {

    @Autowired
    private MonthRepService service;


    /**
     * 加载tree的地区信息
     * @return
     */
    @RequestMapping("/loadTreeZone")
    public ResultVO loadTreeZone(@RequestBody LoadZoneInfo info){
        return ResultFactory.success(service.loadTreeZone(info.getParCd()));
    }

    /**
     * 根据当前区县信息查询可授权的 市 县 信息 和 可授权街道信息
     * @param info
     * @return
     */
    @RequestMapping("/queryZone")
    public ResultVO queryZone(@RequestBody QueryZoneInfo info){
        return ResultFactory.success(service.queryZone(info));
    }

    /**
     * 根据当前区县信息查询可授权的 街道 信息
     * @return
     */
    @RequestMapping("/queryStreet")
    public ResultVO queryStreet(@RequestBody QueryStreetInfo info){
        return ResultFactory.success(service.queryStreet(info));
    }


    /**
     * 加载授权情况
     * @return
     */
    @RequestMapping("/loadAuth")
    public ResultVO loadAuth(@RequestBody LoadAuthInfo info){
        return ResultFactory.success(service.loadAuth(info));
    }


    /**
     * 新增授权
     * @return
     */
    @RequestMapping("/addAuth")
    public ResultVO addAuth(@RequestBody AddAuthInfo info){
        return ResultFactory.success(service.addAuth(info));
    }

    /**
     * 移除授权
     * @return
     */
    @RequestMapping("/delAuth")
    public ResultVO delAuth(@RequestBody RemoveAuthInfo info){
        return ResultFactory.success(service.removeAuth(info));
    }


}

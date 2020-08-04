package com.hyd.monthreport.controller;

import com.hyd.monthreport.info.*;
import com.hyd.monthreport.service.MonthQueService;
import com.hyd.system.factory.ResultFactory;
import com.hyd.system.util.StringUtils;
import com.hyd.system.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xieshuai on 2020/4/10 17:06
 *  自定义月报查询Controller
 */

@RestController
@RequestMapping("/QueryMonth")
public class MonthQueController {

    @Autowired
    private MonthQueService monthQueService;


    /**
     * 自定义报表查询
     * @return
     */
    @RequestMapping("/queryMonthArea")
    public ResultVO queryMonthArea(@RequestBody QueryMonthInfo info){
        return ResultFactory.success(monthQueService.monthQuertyTemplate(info));
    }

    /**
     * 根据当前用户的地区编码查询已授权的地区和当前地区街道信息
     * @param info
     * @return
     */
    @RequestMapping("/queryLicensable")
    public ResultVO queryLicensable(@RequestBody LoadAuthInfo info){
        return ResultFactory.success(monthQueService.queryLicensable(info));
    }

    /**
     * 根据当前机构编码查询所有自定义指标片区
     * @return
     */
    @RequestMapping("/loadMonthArea")
    public ResultVO loadMonthArea(@RequestBody LoadAuthInfo info){
        return ResultFactory.success(monthQueService.loadMonthArea(info));
    }

    /**
     * 删除自定义片区
     * @return
     */
    @RequestMapping("/delArea")
    public ResultVO delMonthArea(@RequestBody AreaInfo info){
        return ResultFactory.success(monthQueService.delArea(info));
    }

    /**
     * 修改自定义片区
     * @return
     */
    @RequestMapping("/editArea")
    public ResultVO editArea(@RequestBody AreaInfo info){
        return ResultFactory.success(monthQueService.editArea(info));
    }

    /**
     * 新增自定义片区
     * @return
     */
    @RequestMapping("/addArea")
    public ResultVO addArea(@RequestBody AreaInfo info){
        return ResultFactory.success(monthQueService.addArea(info));
    }

    /**
     * 查询片区查询
     * @param info
     * @return
     */
    @RequestMapping("/queryArea")
    public ResultVO queryArea(@RequestBody QueryAreaInfo info){
        return ResultFactory.success(monthQueService.queryArea(info));
    }


    /**
     * 加载月报自定义查询页面的可配置项
     * @return
     */
    @RequestMapping("/loadViewConfig")
    public ResultVO loadViewConfig(){
        return ResultFactory.success(monthQueService.loadViewConfig());
    }





}

package com.hyd.usercontrol.controller;


import com.hyd.system.factory.ResultFactory;
import com.hyd.system.vo.ResultVO;
import com.hyd.usercontrol.info.HospitalAppinfo;
import com.hyd.usercontrol.service.HospitalAppService;
import com.hyd.usercontrol.vo.HospitalSing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/UserAppService")
public class HospitalAppController {

    @Autowired
    private HospitalAppService hospitalAppService;


    /**
     * 是否开启APP
     * @param hospitalAppinfo
     * @return
     */
    @PostMapping("/IsZb1AppAuthed")
    ResultVO isZb1AppAuthed(@RequestBody HospitalAppinfo hospitalAppinfo){
        return ResultFactory.success(hospitalAppService.isZb1AppAuthed(hospitalAppinfo));
    }

    /**
     * 查询直报列表
     * @param hospitalAppinfo
     * @return
     */
    @PostMapping("/HospitalZbList")
    ResultVO hospitalZbList(@RequestBody HospitalAppinfo hospitalAppinfo){
        return ResultFactory.success(hospitalAppService.hospitalZbList(hospitalAppinfo));
    }

    /**
     * 查询医院列表
     * @param
     * @return
     */
    @PostMapping("/HospitalYYList")
    ResultVO hospitalYYList(){
        return ResultFactory.success(hospitalAppService.hospitalYYList());
    }


    /**
     * 查询医院签约列表
     * @param hospitalAppinfo
     * @return
     */
    @PostMapping("/HospitalSingList")
    ResultVO hospitalSingList(@RequestBody HospitalAppinfo hospitalAppinfo){
        return ResultFactory.success(hospitalAppService.hospitalSingList(hospitalAppinfo));
    }

    /**
     * 查询签约
     * @param hospitalAppinfo
     * @return
     */
    @PostMapping("/HospitalSingByCd")
    ResultVO hospitalSingByCd(@RequestBody HospitalAppinfo hospitalAppinfo){
        return ResultFactory.success(hospitalAppService.hospitalSingByCd(hospitalAppinfo));
    }



    /**
     * 添加签约
     * @param hospitalSing
     * @return
     */
    @PostMapping("/HospitalAdd")
    ResultVO hospitalAdd(@RequestBody HospitalSing hospitalSing){
        return ResultFactory.success(hospitalAppService.hospitalUpdate(hospitalSing));
    }


    /**
     * 修改签约
     * @param hospitalSing
     * @return
     */
    @PostMapping("/HospitalUpdate")
    ResultVO hospitalUpdate(@RequestBody HospitalSing hospitalSing){
        return ResultFactory.success(hospitalAppService.hospitalUpdate(hospitalSing));
    }

    /**
     * 删除签约
     * @param hospitalSing
     * @return
     */
    @PostMapping("/Hospitaldel")
    ResultVO hospitaldel(@RequestBody HospitalSing hospitalSing){
        return ResultFactory.success(hospitalAppService.hospitaldel(hospitalSing));
    }



}

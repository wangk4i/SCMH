package com.hyd.orgmaintain.controller;


import com.hyd.orgmaintain.info.QueryOrganinfo;
import com.hyd.orgmaintain.service.OrgMaintinService;

import com.hyd.orgmaintain.vo.OrgUser;
import com.hyd.orgmaintain.vo.Organ;

import com.hyd.system.factory.ResultFactory;
import com.hyd.system.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/OrgMaintain")
public class OrgMaintainController {

        @Autowired
        OrgMaintinService orgMaintinService;


        /**
         * 获取省
         * @return
         */
        @PostMapping("/Province")
        ResultVO initAllProvince(){
                return ResultFactory.success(orgMaintinService.initAllProvince());
        };

        /**
         * 获取 市 县 社区
         */
        @PostMapping("/City")
        ResultVO foundZoneByParCode(@RequestBody QueryOrganinfo queryOrganinfo){
                return ResultFactory.success(orgMaintinService.foundZoneByParCode(queryOrganinfo));
        };

        /**
         * 机构列表
         * @param organ
         * @return
         */
        @PostMapping("/Organ")
        ResultVO initOrganList(@RequestBody Organ organ){
                return ResultFactory.success(orgMaintinService.initOrganList(organ));
        }

        /**
         * 机构用户
         */
        @PostMapping("/OrganUser")
        ResultVO initOrganUserList(@RequestBody QueryOrganinfo queryOrganinfo){
            Organ organCd= orgMaintinService.queryOrganByCd(queryOrganinfo);
            List<OrgUser> organUser = orgMaintinService.queryOrganUser(queryOrganinfo);
            organCd.setOrgUser(organUser);
            return ResultFactory.success(organCd);
        }


        /**
         * 获取机构信息
         */
        @PostMapping("/OrganInfo")
        ResultVO getOrganMessage(@RequestBody QueryOrganinfo queryOrganinfo){
                return ResultFactory.success(orgMaintinService.queryOrganByCd(queryOrganinfo));
        }

        /**
        判读机构编码是否重复
         */
        @PostMapping("/orgMaintinService")
        ResultVO iSCodeRepetition (@RequestBody QueryOrganinfo queryOrganinfo){
           return ResultFactory.success(orgMaintinService.iSCodeRepetition(queryOrganinfo));
        }

        /**
         * 新增机构
         * @param organ
         * @return
         */
        @PostMapping("/addSPMOrgan")
        ResultVO addSPMOrgan(@RequestBody Organ organ){
           return  ResultFactory.success(orgMaintinService.addSPMOrgan(organ));
        }

        /**
         * 修改机构
         * @param organ
         * @return
         */
        @PostMapping("/updateSPMOrgan")
        ResultVO updateSPMOrgan(@RequestBody Organ organ){
                return  ResultFactory.success(orgMaintinService.updateSPMOrgan(organ));
        }

        /**
         * 修改机构状态
         * @param queryOrganinfo
         * @return
         */
        @PostMapping("/updateState")
        ResultVO updateState(@RequestBody QueryOrganinfo queryOrganinfo){
           return  ResultFactory.success(orgMaintinService.updateState(queryOrganinfo));
        }


        /**
         * 判断机构用户是否有县本级1
         * @param queryOrganinfo
         * @return
         */
        @PostMapping("/isOrganUserBJ")
        ResultVO isOrganUserBJ(@RequestBody QueryOrganinfo queryOrganinfo){
                return  ResultFactory.success(orgMaintinService.isOrganUserBJ(queryOrganinfo));
        }



        /**
         * 判断机构用户是否有直报1
         * @param queryOrganinfo
         * @return
         */
        @PostMapping("/isOrganUserZB")
        ResultVO isOrganUserZB(@RequestBody QueryOrganinfo queryOrganinfo){
                return  ResultFactory.success(orgMaintinService.isOrganUserZB(queryOrganinfo));
        }



        /**
         * 判断机构是否重复
         *
         */
        @PostMapping("/IsOrganRepetition")
        ResultVO isOrganRepetition(@RequestBody QueryOrganinfo queryOrganinfo){
                return  ResultFactory.success(orgMaintinService.isOrganRepetition(queryOrganinfo));
        }






}

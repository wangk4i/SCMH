package com.hyd.system.controller;


import com.hyd.orgmaintain.service.OrgMaintinService;
import com.hyd.system.factory.ResultFactory;
import com.hyd.system.util.StringUtils;
import com.hyd.system.vo.DictVo;
import com.hyd.system.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Dict")
public class DictController {

    @Autowired
    StringUtils stringUtils;

    @PostMapping("/getDictToFrom")
    ResultVO getDictToFrom(){
        return ResultFactory.success(stringUtils.getDictToFrom());
    };


}

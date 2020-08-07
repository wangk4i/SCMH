package com.hyd.resultdeal.controller;

import com.hyd.resultdeal.service.FileDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/fileDeal")
@RestController
public class FileDealController {
    @Autowired
    FileDealService server;

    @RequestMapping("/resultDeal")
    public String resultDeal(){
        server.folderResultDeal();
        return "success";
    }
}

package com.hyd.gwinterfaceserver.util;

import com.hyd.gwinterfaceserver.patinfo.vo.ResultDatainfo;
import lombok.Data;

import java.util.List;

@Data
public class ResultInfos
    {
        public String code ;
        public String message ;
        public List<ResultDatainfo> data ;

    }
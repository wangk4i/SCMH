package com.hyd.gwinterfaceserver.util;

import com.hyd.gwinterfaceserver.patinfo.vo.ResultDatainfo;
import lombok.Data;

@Data
public class ResultInfo
    {
        public Integer code ;
        public String message ;
        public ResultDatainfo data ;

    }
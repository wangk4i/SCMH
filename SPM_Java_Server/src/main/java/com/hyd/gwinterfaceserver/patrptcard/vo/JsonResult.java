package com.hyd.gwinterfaceserver.patrptcard.vo;

import lombok.Data;

@Data
public class JsonResult
    {
        public int code ;
        public String message ;
        public Object data ;
        public int count ;
        public JsonPage pager ;
        
    }
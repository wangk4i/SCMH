package com.hyd.gwinterfaceserver.patinfo.info;

import lombok.Data;

@Data
public class responseMoveInInput
    {
        /// <summary>
        /// 迁入响应的流转信息主键。必须指定
        /// </summary>
        public String moveInAndOutId;
        
    }
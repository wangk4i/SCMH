package com.hyd.gwinterfaceserver.patrptcard.info;

import com.hyd.gwinterfaceserver.patrptcard.vo.ExtInfo;
import lombok.Data;

@Data
public class PatInfoCallBackMoveRequest
    {
        /// <summary>
        /// 流转编号
        /// </summary>
        public String moveCode;
        public ExtInfo extInfoObj;
    }






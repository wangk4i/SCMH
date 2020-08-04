package com.hyd.system.factory;

import com.hyd.system.vo.ResultPageVO;

public class ResultPageFactory {

    public static ResultPageVO success() {
        ResultPageVO resultPageVO = new ResultPageVO();
        resultPageVO.setMessage("请求成功");
        resultPageVO.setCode(0);
        return resultPageVO;
    }

    public static ResultPageVO success(Object data, Integer count) {
        ResultPageVO resultPageVO = new ResultPageVO();
        resultPageVO.setMessage("请求成功");
        resultPageVO.setCode(0);
        resultPageVO.setData(data);
        resultPageVO.setCount(count);
        return resultPageVO;
    }


}

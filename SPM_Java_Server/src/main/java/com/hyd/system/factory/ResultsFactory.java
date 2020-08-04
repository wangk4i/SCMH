package com.hyd.system.factory;

import com.hyd.system.vo.ResultVO;


public class ResultsFactory {

    public static ResultVO success(Object data){
        ResultVO resultVO = new ResultVO();
        resultVO.setMessage("请求成功");
        resultVO.setCode(0);
        resultVO.setData(data);
        return resultVO;
    }

    public static ResultVO success(Object data,String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setMessage(msg);
        resultVO.setCode(0);
        resultVO.setData(data);
        return resultVO;
    }


}

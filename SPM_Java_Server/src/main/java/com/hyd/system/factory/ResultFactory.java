package com.hyd.system.factory;

import com.hyd.system.vo.ResultVO;

/**
 * Created by xieshuai on 2020/4/7 17:58
 */
public class ResultFactory {

    public static ResultVO success(){
        ResultVO resultVO = new ResultVO();
        resultVO.setMessage("请求成功");
        resultVO.setCode(0);
        return resultVO;
    }


    public static ResultVO success(Object data){
        ResultVO resultVO = new ResultVO();
        resultVO.setMessage("请求成功");
        resultVO.setCode(0);
        resultVO.setData(data);
        return resultVO;
    }


    public static ResultVO error(){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(-1);
        return resultVO;
    }

    public static ResultVO error(String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(-1);
        resultVO.setMessage(msg);
        return resultVO;
    }
}

package com.hyd.system.aspect.handle;

import com.hyd.system.exceptclass.*;
import com.hyd.system.factory.ResultFactory;
import com.hyd.system.factory.ResultFactorys;
import com.hyd.system.vo.ResultVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xieshuai on 2019/8/16 11:24
 * 全局异常处理器(所以来自controller层的异常都会被拦截，再通过异常种类进行分发)
 */
@ControllerAdvice
public class ExceptionHandle {

    private static final Logger logger = LogManager.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultVO handle(Exception e) {

        if(e instanceof BusineException){
            BusineException busineException = (BusineException) e;
            return ResultFactory.error(busineException.getMsg());
        }
        if (e instanceof ErrorException) {
            ErrorException errorException = (ErrorException) e;
            return ResultFactory.error(errorException.getMsg());
        }
        if (e instanceof SqlException) {
            SqlException sqlException = (SqlException) e;
            logger.error(StactException.getStackTrace(e));
            logger.error(sqlException.getMsg());
            return ResultFactory.error(sqlException.getMsg());
        }
        if (e instanceof ParameException) {
            ParameException parameException = (ParameException) e;
            return ResultFactory.error(parameException.getMsg());
        }
        if (e instanceof ResultException) {
            ResultException resultException = (ResultException) e;
            return ResultFactory.error(resultException.getMsg());
        }
        if (e instanceof AccessException){
            AccessException exception = (AccessException) e;
            logger.error(StactException.getStackTrace(e));
            logger.error(exception.getMsg());
            return ResultFactory.error(exception.getMsg());
        }
        if (e instanceof SystemException){
            SystemException systemException = (SystemException) e;
            logger.error(StactException.getStackTrace(e));
            logger.error(e.getMessage());
            return ResultFactory.error(systemException.getMessage());
        }
        if (e instanceof ClassCastException){
            logger.error(StactException.getStackTrace(e));
            logger.error(e.getMessage());
            return ResultFactory.error(e.getMessage());
        }
        if (e instanceof ResultExceptions){
            ResultExceptions exception = (ResultExceptions) e;
            return ResultFactorys.error(exception.getMsg(),exception.getCode());
        }
        if (e instanceof BusineExceptions){
            BusineExceptions exception = (BusineExceptions) e;
            return ResultFactorys.error(exception.getMsg(),exception.getCode());
        }
        else {
            logger.error(StactException.getStackTrace(e));
            logger.error(e.getMessage());
            return ResultFactory.error(e.getMessage());
        }
    }


}

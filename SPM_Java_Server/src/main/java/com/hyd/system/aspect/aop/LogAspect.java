package com.hyd.system.aspect.aop;

import com.hyd.system.exceptclass.ResultException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xieshuai on 2019/10/15 16:58
 *
 */
@Aspect
@Component
public class LogAspect {


    /**
     * 全局日志记录
     */
    private static Logger logger = LoggerFactory.getLogger(LogAspect.class);


    /**
     * map1存放方法被调用的次数
     */
    ThreadLocal<Map<String, Long>> map1 = new ThreadLocal<>();

    /**
     * map2存放方法总耗时
     */
    ThreadLocal<Map<String, Long>> map2 = new ThreadLocal<>();

    /**
     * Aop：环绕通知 切整个包下面的所有涉及到调用的方法的信息
     *
     * @return
     * @throws Throwable
     */
    @Around(value = "execution(* com.*.controller.*Controller.*(..))")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {

        //初始化 一次
        if (map1.get() == null) {
            map1.set(new HashMap<>());
        }
        if (map2.get() == null) {
            map2.set(new HashMap<>());
        }
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();

        logger.info("===================");
        String tragetClassName = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();

        Object[] args = joinPoint.getArgs();
        if(args==null){
            throw new ResultException("result为Null");
        }
        int argsSize = args.length;
        String typeStr = joinPoint.getSignature().getDeclaringType().toString().split(" ")[0];
        String returnType = joinPoint.getSignature().toString().split(" ")[0];
        logger.info("类/接口: {}, ({})", tragetClassName, typeStr);
        logger.info("方法:{}", methodName);
        logger.info("参数个数: {}", argsSize);
        logger.info("返回类型: {}", returnType);

        Long total = end - start;
        logger.info("耗时: " + total + " ms!");

        if (map1.get().containsKey(methodName)) {
            Long count = map1.get().get(methodName);
            //先移除，在增加
            map1.get().remove(methodName);
            map1.get().put(methodName, count + 1);

            count = map2.get().get(methodName);
            map2.get().remove(methodName);
            map2.get().put(methodName, count + total);
        } else {
            map1.get().put(methodName, 1L);
            map2.get().put(methodName, total);
        }
        return result;

    }

    /**
     * 前置通知
     * @param jp
     * @throws Throwable
     */
    @Before(value = "execution(* com.*.controller.*Controller.*(..))" )
    public void beforMehhod(JoinPoint jp){
    }

    /**
     * 后置通知
     *
     * @param jp
     * @param result
     * @throws Throwable
     * @return
     */
    @AfterReturning(value = "execution(* com.*.controller.*Controller.*(..))", returning = "result")
    public void afterMehhod(JoinPoint jp, Object result) {
    }

}

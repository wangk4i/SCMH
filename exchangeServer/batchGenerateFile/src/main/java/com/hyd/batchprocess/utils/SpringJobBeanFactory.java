package com.hyd.batchprocess.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 使用Bean工厂 方法 获取bean对象
 *
 */
@Component
public class SpringJobBeanFactory implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringJobBeanFactory.applicationContext=applicationContext;

    }
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) throws BeansException {
        if (applicationContext == null){
            return null;
        }
        return (T)applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T>  name) throws BeansException {
        if (applicationContext == null){
            return null;
        }
        return applicationContext.getBean(name);
    }
}
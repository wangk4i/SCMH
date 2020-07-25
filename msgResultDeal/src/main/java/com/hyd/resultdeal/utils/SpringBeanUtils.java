package com.hyd.resultdeal.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by xieshuai on 2020/7/7 上午 10:29
 */
public class SpringBeanUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public SpringBeanUtils(){}

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringBeanUtils.applicationContext = applicationContext;
    }


    /**
     * 动态管理创建Bean
     * @param beanName  创建Bean名称
     * @param target  类对象
     */
    public static void registerBean(String beanName, Object target){
        if (!applicationContext.containsBean(beanName)) {
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition((Class<?>) target);
            DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
            defaultListableBeanFactory.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        }
    }


    /**
     * 手动获取创建的Bean
     * @param className 类名称
     * @param clazz  Bean类型
     * @param <T>
     * @return
     */
    public static <T>T getBean(String className, Class<T> clazz){
        if(className==null || className.length()<=0) {
            throw new IllegalArgumentException("className为空");
        }
        String beanName;
        if(className.length() > 1) {
            beanName = className.substring(0, 1).toLowerCase() + className.substring(1);
        } else {
            beanName = className.toLowerCase();
        }
        return applicationContext != null ? applicationContext.getBean(beanName, clazz) : null;
    }


}

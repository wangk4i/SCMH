package com.hyd.batchprocess.listener;

import com.hyd.batchprocess.service.BasicInfoService;
import com.hyd.batchprocess.utils.SpringJobBeanFactory;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            // 想要执行的操作
            BasicInfoService basicInfoService = SpringJobBeanFactory.getBean(BasicInfoService.class);
            basicInfoService.batchProcessDocument();
        }
        ServletWebServerApplicationContext context = (ServletWebServerApplicationContext) SpringJobBeanFactory.getApplicationContext();
        context.getWebServer().stop();

    }


}

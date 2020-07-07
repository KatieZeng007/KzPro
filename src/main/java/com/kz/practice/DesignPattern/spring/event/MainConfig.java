package com.kz.practice.DesignPattern.spring.event;

import com.kz.practice.DesignPattern.spring.event.register.base.EventListener;
import com.kz.practice.DesignPattern.spring.event.register.base.EventMulticaster;
import com.kz.practice.DesignPattern.spring.event.register.register.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @Description 利用Spring组装事件
 * @Author KatieZ
 * @Date Created in 17:17  17:17
 */
@Configuration
@ComponentScan
public class MainConfig {
    /**
     *
     * @param eventListeners
     * @return
     */
    @Bean
    @Autowired(required = false)
    public EventMulticaster eventMulticaster(List<EventListener> eventListeners){
        SimpleEventMulticaster simpleEventMulticaster = new SimpleEventMulticaster();
        if(null != eventListeners){
            eventListeners.forEach(simpleEventMulticaster::addEventListener);
        }
        return simpleEventMulticaster;
    }

    /**
     * 用户注册服务
     */
    @Bean
    public UserRegisterService userRegisterService(EventMulticaster eventMulticaster){
        UserRegisterService userRegisterService = new UserRegisterService();
        userRegisterService.setEventMulticaster(eventMulticaster);
        return userRegisterService;
    }



}

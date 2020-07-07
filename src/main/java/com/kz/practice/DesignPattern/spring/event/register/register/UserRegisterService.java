package com.kz.practice.DesignPattern.spring.event.register.register;

import com.kz.practice.DesignPattern.spring.event.register.base.EventMulticaster;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description 用户注册服务
 * @Author KatieZ
 * @Date Created in 17:13  17:13
 */
@Slf4j
public class UserRegisterService {
    // 事件发布者
    private EventMulticaster eventMulticaster;

    /**
     * 注册用户
     * @param userName
     */
    public void registerUser(String userName){
        log.info(String.format("用户【%s】注册成功",userName));
        // 广播事件
        this.eventMulticaster.mutilcastEvent(new UserRegisterSuccessEvent(this,userName));
    }

    public EventMulticaster getEventMulticaster() {
        return eventMulticaster;
    }

    public void setEventMulticaster(EventMulticaster eventMulticaster) {
        this.eventMulticaster = eventMulticaster;
    }
}

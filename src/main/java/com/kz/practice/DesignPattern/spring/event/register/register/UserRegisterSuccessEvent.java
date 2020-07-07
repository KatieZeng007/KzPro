package com.kz.practice.DesignPattern.spring.event.register.register;

import com.kz.practice.DesignPattern.spring.event.register.base.AbstractEvent;

/**
 * @Description 事件源
 * @Author KatieZ
 * @Date Created in 17:11  17:11
 */
public class UserRegisterSuccessEvent extends AbstractEvent {
    /**
     * 用户名
     */
    private String userName;

    public UserRegisterSuccessEvent(Object source,String userName) {
        super(source);
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

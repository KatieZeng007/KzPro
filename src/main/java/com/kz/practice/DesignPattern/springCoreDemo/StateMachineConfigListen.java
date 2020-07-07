package com.kz.practice.DesignPattern.springCoreDemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;

/**
 * @ Author     ：KatieZ
 * @ Date       ：Created in 16:52 2019/12/24
 * @ Description：状态机事件监听器 事件发生时会触发操作
 * @ Modified By：KatieZ
 * @Version: V1.0
 */
@Configuration
@WithStateMachine
@Slf4j
public class StateMachineConfigListen {

    @OnTransition(source = "UNCONNECTED",target = "CONNECTED")
    public void connect(){
        long threadId = Thread.currentThread().getId();
        log.debug("Thread # " + threadId + " is doing this task");
        log.info("开始连接...");
    }

    @OnTransition(source = "CONNECTED",target = "LOGINING")
    public void startLogin(){
        long threadId = Thread.currentThread().getId();
        log.debug("Thread # " + threadId + " is doing this task");
        log.info("开始登录...");
    }

    @OnTransition(source = "LOGINING",target = "UNCONNECTED")
    public void loginFail(){
        long threadId = Thread.currentThread().getId();
        log.debug("Thread # " + threadId + " is doing this task");
        log.info("登录失败...");
    }

    @OnTransition(source = "LOGINING",target = "LOGIN_INTO_SYSTEM")
    public void loginSuccess(){
        long threadId = Thread.currentThread().getId();
        log.debug("Thread # " + threadId + " is doing this task");
        log.info("登录成功...");
    }

    @OnTransition(source = "LOGIN_INTO_SYSTEM",target = "UNCONNECTED")
    public void loginOut(){
        long threadId = Thread.currentThread().getId();
        log.debug("Thread # " + threadId + " is doing this task");
        log.info("登出系统...");
    }


}

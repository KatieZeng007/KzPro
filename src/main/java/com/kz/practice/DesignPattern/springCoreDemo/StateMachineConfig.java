package com.kz.practice.DesignPattern.springCoreDemo;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * @ Author     ：KatieZ
 * @ Date       ：Created in 16:25 2019/12/24
 * @ Description：状配置类一般要继承EnumStateMachineConfigurerAdapter类，并且重写一些configure方法以配置状态机的初始状态以及事件与状态转移的联系
 * @ Modified By：KatieZ
 * @Version: V1.0
 */
@Configuration
// 开启状态机配置
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<LoginStateEnum,LoginEventStateEnum>{

    /**
     * 配置状态机状态
     * @param states
     * @throws Exception
     */
    @Override
    public void configure(StateMachineStateConfigurer<LoginStateEnum, LoginEventStateEnum> states) throws Exception {
        states.withStates()
                // 初始状态
                .initial(LoginStateEnum.UNCONNECTED)
                // 所有可能状态
                .states(EnumSet.allOf(LoginStateEnum.class));
    }

    /**
     * 配置状态机转换
     * @param transitions
     * @throws Exception
     */
    @Override
    public void configure(StateMachineTransitionConfigurer<LoginStateEnum, LoginEventStateEnum> transitions) throws Exception {
        transitions
                // 1. 连接  未连接状态到连接状态
                .withExternal().source(LoginStateEnum.UNCONNECTED).target(LoginStateEnum.CONNECTED).event(LoginEventStateEnum.CONNECT)
                .and()
                // 2. 开始登录 连接状态到开始登录状态
                .withExternal().source(LoginStateEnum.CONNECTED).target(LoginStateEnum.LOGINING).event(LoginEventStateEnum.BEGIN_TO_LOGIN)
                .and()
                // 3. 登录失败 登录中到未连接
                .withExternal().source(LoginStateEnum.LOGINING).target(LoginStateEnum.UNCONNECTED).event(LoginEventStateEnum.LOGIN_FAILURE)
                .and()
                // 4. 登录成功 登录中到连接成功
                .withExternal().source(LoginStateEnum.LOGINING).target(LoginStateEnum.LOGIN_INTO_SYSTEM).event(LoginEventStateEnum.LOGIN_SUCCESS)
                .and()
                // 5. 退出登录 登录成功到未连接
                .withExternal().source(LoginStateEnum.LOGIN_INTO_SYSTEM).target(LoginStateEnum.UNCONNECTED).event(LoginEventStateEnum.LOGOUT);
    }
}

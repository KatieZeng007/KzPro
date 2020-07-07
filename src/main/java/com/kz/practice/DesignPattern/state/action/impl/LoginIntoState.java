package com.kz.practice.DesignPattern.state.action.impl;

import com.kz.practice.DesignPattern.state.StateEnum;
import com.kz.practice.DesignPattern.state.action.AbstractState;
import com.kz.practice.DesignPattern.state.action.IState;

import javax.naming.Context;

/**
 * @ Author     ：KatieZ
 * @ Date       ：Created in 10:40 2019/12/24
 * @ Description：登录系统状态
 * 进行logout操作直接抛出异常
 * @ Modified By：KatieZ
 * @Version: V1.0
 */
public class LoginIntoState extends AbstractState{
    public LoginIntoState(StateEnum stateEnum) {
        super(stateEnum);
    }

    @Override
    public IState connect(com.kz.practice.DesignPattern.state.Context context) {
        return null;
    }

    @Override
    public IState beginLogin(com.kz.practice.DesignPattern.state.Context context) {
        return null;
    }

    @Override
    public IState loginSuccess(com.kz.practice.DesignPattern.state.Context context) {
        return null;
    }

    @Override
    public IState loginFailure(com.kz.practice.DesignPattern.state.Context context) {
        return null;
    }

    @Override
    public IState loginOut(com.kz.practice.DesignPattern.state.Context context) {
        return null;
    }
}

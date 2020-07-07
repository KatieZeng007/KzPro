package com.kz.practice.DesignPattern.state.action.impl;

import com.kz.practice.DesignPattern.state.StateEnum;
import com.kz.practice.DesignPattern.state.action.AbstractState;
import com.kz.practice.DesignPattern.state.action.IState;

import javax.naming.Context;

/**
 * @ Author     ：KatieZ
 * @ Date       ：Created in 10:39 2019/12/24
 * @ Description：正在登录状态
 * @ Modified By：KatieZ
 * @Version: V1.0
 */
public class LoginingState extends AbstractState{
    public LoginingState(StateEnum stateEnum) {
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

package com.kz.practice.DesignPattern.state.action.impl;

import com.kz.practice.DesignPattern.state.StateEnum;
import com.kz.practice.DesignPattern.state.action.AbstractState;
import com.kz.practice.DesignPattern.state.action.IState;

import javax.naming.Context;

/**
 * @ Author     ：KatieZ
 * @ Date       ：Created in 10:28 2019/12/24
 * @ Description：未连接状态
 * @ Modified By：KatieZ
 * @Version: V1.0
 */
public class UnconnectedState extends AbstractState{

    public UnconnectedState(StateEnum stateEnum) {
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

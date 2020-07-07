package com.kz.practice.DesignPattern.state.action;

import com.kz.practice.DesignPattern.state.StateEnum;

import javax.naming.Context;

/**
 * @ Author     ：KatieZ
 * @ Date       ：Created in 10:12 2019/12/24
 * @ Description：抽象类 封装公共方法 以及实例成员
 * @ Modified By：KatieZ
 * @Version: V1.0
 */
public abstract class AbstractState implements IState{

    private StateEnum stateEnum;

    public AbstractState(StateEnum stateEnum){
        this.stateEnum = stateEnum;
    }


    public StateEnum getStateEnum() {
        return stateEnum;
    }

    public void setStateEnum(StateEnum stateEnum) {
        this.stateEnum = stateEnum;
    }
}

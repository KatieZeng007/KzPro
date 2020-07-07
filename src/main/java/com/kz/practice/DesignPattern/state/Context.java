package com.kz.practice.DesignPattern.state;

import com.kz.practice.DesignPattern.state.action.IState;
import com.kz.practice.DesignPattern.state.action.impl.ConnectedState;
import com.kz.practice.DesignPattern.state.action.impl.LoginIntoState;
import com.kz.practice.DesignPattern.state.action.impl.LoginingState;
import com.kz.practice.DesignPattern.state.action.impl.UnconnectedState;

/**
 * @ Author     ：KatieZ
 * @ Date       ：Created in 10:43 2019/12/24
 * @ Description：环境类用来感知当前的状态 状态机
 * 将各种状态定义成Context的类成员变量，保持单例
 * @ Modified By：KatieZ
 * @Version: V1.0
 */
public class Context {

    public static final IState UNCONNECTED_STATE = new UnconnectedState(StateEnum.UNCONNECTED);

    public static final IState CONNECTED_STATE = new ConnectedState(StateEnum.CONNECTED);

    public static final IState LOGINING_STATE = new LoginingState(StateEnum.LOGINING);

    public static final IState LOGIN_INTO_SYSTEM_STATE = new LoginIntoState(StateEnum.LOGIN_INTO_SYSTEM);

    private IState state;

    public Context(IState initState)
    {
        initState(initState);
    }

    public void initState(IState iState){
        this.setState(iState);
    }

    public void connect(){
        setState(this.state.connect(this));
    }

    public void beginLogin()
    {
        setState(this.state.beginLogin(this));
    }

    public void loginFailure()
    {
        setState(this.state.loginFailure(this));
    }

    public void loginSuccess()
    {
        setState(this.state.loginSuccess(this));
    }

    public void loginOut()
    {
        setState(this.state.loginOut(this));
    }


    public IState getiState() {
        return state;
    }

    public void setState(IState state) {
        this.state = state;
    }
}

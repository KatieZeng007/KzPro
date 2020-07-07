package com.kz.practice.DesignPattern.state.action;

import com.kz.practice.DesignPattern.state.Context;

/**
 * @ Author     ：KatieZ
 * @ Date       ：Created in 9:42 2019/12/24
 * @ Description：定义一个接口封闭所有的动作
 * @ Modified By：KatieZ
 * @Version: V1.0
 */
public interface IState {

    /**
     * 连接
     * @param context
     * @return
     */
    public IState connect(Context context);

    /**
     * 开始登录
     * @param context
     * @return
     */
    public IState beginLogin(Context context);

    /**
     * 登录成功
     * @param context
     * @return
     */
    public IState loginSuccess(Context context);

    /**
     * 登录失败
     * @param context
     * @return
     */
    public IState loginFailure(Context context);

    /**
     * 登录失败
     * @param context
     * @return
     */
    public IState loginOut(Context context);

}

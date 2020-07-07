package com.kz.practice.DesignPattern.springCoreDemo;

/**
 * @ Author     ：KatieZ
 * @ Date       ：Created in 16:17 2019/12/24
 * @ Description：登录时间状态枚举类
 * @ Modified By：KatieZ
 * @Version: V1.0
 */
public enum LoginEventStateEnum {

    // 连接
    CONNECT,
    // 开始登录
    BEGIN_TO_LOGIN,
    // 登录成功
    LOGIN_SUCCESS,
    // 登录失败
    LOGIN_FAILURE,
    // 注销登录
    LOGOUT;
}

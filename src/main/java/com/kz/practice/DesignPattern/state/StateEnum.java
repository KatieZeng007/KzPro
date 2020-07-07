package com.kz.practice.DesignPattern.state;

public enum StateEnum {

    UNCONNECTED(0,"未连接"),
    CONNECTED(1, "已连接"),
    LOGINING(2, "登录中"),
    LOGIN_INTO_SYSTEM(3, "已登录");

    private int key;
    private String str;

    StateEnum(int key,String str){
        this.key = key;
        this.str = str;
    }
}

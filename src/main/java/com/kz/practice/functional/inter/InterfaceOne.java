package com.kz.practice.functional.inter;

import java.util.Objects;

/**
 * @Description 函数式编程测试
 * 1. 只能有一个方法
 * 2. 需要在接口上加上@FunctionalInterface注解 (编译器校验)
 * 3. JDK1.8在接口中加默认的方法
 * 4. 可以有多个default
 * @Author KatieZ
 * @Date Created in 11:00  11:00
 */
@FunctionalInterface
public interface InterfaceOne<T> {

    int doubleNum(int num);

    default int sum(int x,int y){
        return x+y;
    }




}

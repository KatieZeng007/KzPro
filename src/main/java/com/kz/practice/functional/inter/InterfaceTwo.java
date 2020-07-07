package com.kz.practice.functional.inter;

/**
 * @Description TODO
 * @Author KatieZ
 * @Date Created in 11:09  11:09
 */
@FunctionalInterface
public interface InterfaceTwo {

    int doubleNum(int num);

    default int sum(int x,int y){
        return x+y;
    }
}

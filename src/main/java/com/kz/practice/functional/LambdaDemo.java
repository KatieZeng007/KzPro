package com.kz.practice.functional;

import com.kz.practice.entity.Person;
import com.kz.practice.functional.inter.InterfaceOne;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 测试函数式编程
 * @Author KatieZ
 * @Date Created in 11:13  11:13
 */
@Slf4j
public class LambdaDemo {

    public static void main(String[] args){
        // 函数式编程方式 传入表达式
        InterfaceOne interfaceOne = x -> x * 2;
        System.out.println(interfaceOne.doubleNum(2));
    }

}

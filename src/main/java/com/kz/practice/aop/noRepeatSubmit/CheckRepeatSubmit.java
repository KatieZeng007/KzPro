package com.kz.practice.aop.noRepeatSubmit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 校验重复提交的注解
 * @author kz
 */
// 作用在方法上
@Target(ElementType.METHOD)
// 运行时有效
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckRepeatSubmit {

    /**
     * 默认重复提交时间 3 秒
     */
    int time() default 3*1000;
}

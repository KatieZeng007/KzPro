package com.kz.practice.excel.drop;

import net.bytebuddy.implementation.bind.annotation.RuntimeType;

import java.lang.annotation.*;

/**
 * 标记导出excel的下拉数据集
 */
@Documented
// 作用在字段上
@Target(ElementType.FIELD)
// 运行时有效
@Retention(RetentionPolicy.RUNTIME)
public @interface DropDownSetField {
    // 固定下拉内容
    String[] source() default {};
    // 动态下拉内容
    Class[] sourceClass() default {};
}

package com.kz.practice.aop.noRepeatSubmit;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description 对重复提交的请求进行拦截处理
 * 使用redis记录请求 key为useId:path value path 设置过期时间为 3s
 * @Author KatieZ
 * @Date Created in 17:49  17:49
 */
@Slf4j
@Aspect
@Component
public class CheckRepeatAop {


}

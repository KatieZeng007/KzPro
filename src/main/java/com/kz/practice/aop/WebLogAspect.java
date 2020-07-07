package com.kz.practice.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @ Author     ：KatieZ
 * @ Date       ：Created in 16:25 2019/12/23
 * @ Description：面向切面测试
 * @ Modified By：KatieZ
 * @Version: V
 */
@Aspect
@Component
@Slf4j
public class WebLogAspect {

    /**
     * Pointcut的定义包括两个部分：Pointcut表示式(expression)和Pointcut签名(signature)
     * execution(* *(..))  匹配所有方法
     * execution(public * com.kz.practice.service.UserService.*(..))  匹配com.kz.practice.service.UserService中所有的公有方法
     * execution(* com.kz.practice.service..*.*(..)) 匹配com.kz.practice.service包及其子包下的所有方法
     */
    // Pointcut表达式
    @Pointcut("execution(public * com.kz.practice.controller..*.*(..))")
    /**
     * Pointcut签名
     */
    public void webLog(){}

    /**
     * 在目标方法调用之前调用通知功能
     * @param joinPoint
     * @throws Exception
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Exception{
        log.info("进入doBefore切面");
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        log.info("URL:"+request.getRequestURL());
        log.info("HTTP_METHOUD:"+request.getMethod());
        log.info("IP:"+request.getRemoteAddr());
        log.info("CLASS_METHOUD:"+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
    }

    /**
     * 在目标方法成功执行之后调用通知
     * returning 要返回的变量的名称
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret",pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable{
        log.info("response:"+ret);
    }
}


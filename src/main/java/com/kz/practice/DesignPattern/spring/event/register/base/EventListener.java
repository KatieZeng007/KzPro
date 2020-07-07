package com.kz.practice.DesignPattern.spring.event.register.base;

import com.kz.practice.DesignPattern.spring.event.register.base.AbstractEvent;

/**
 * @Description 事件监听器
 * @Author KatieZ
 * @Date Created in 16:41  16:41
 * @param <E> 当前监听器感兴趣的事件类型
 */
public interface EventListener<E extends AbstractEvent> {

    /**
     * 此方法负责处理事件
     * @param event
     */
    void onEvent(E event);

}

package com.kz.practice.DesignPattern.spring.event.register.base;

/**
 * @Description 事件广播器
 * 1. 负责事件监听器的管理（注册监听器 & 移除监听器 将事件和监听器关联）
 * 2. 负责事件的广播（将事件广播给所有监听器 对事件感兴趣的监听器进行处理）
 * @Author KatieZ
 * @Date Created in 16:43  16:43
 */
public interface EventMulticaster {
    /**
     * 广播事件给所有的监听器
     * 对该事件感兴趣的监听器会处理该事件
     * @param event
     */
    void mutilcastEvent(AbstractEvent event);

    /**
     * 添加一个事件监听器（监听器中包含监听器中能处理的事件）
     * @param listener
     */
    void addEventListener(EventListener<?> listener);

    /**
     * 移除监听器
     * @param listener
     */
    void removeEventListener(EventListener<?> listener);
}

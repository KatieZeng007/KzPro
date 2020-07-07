package com.kz.practice.DesignPattern.spring.event;

import com.kz.practice.DesignPattern.spring.event.register.base.AbstractEvent;
import com.kz.practice.DesignPattern.spring.event.register.base.EventListener;
import com.kz.practice.DesignPattern.spring.event.register.base.EventMulticaster;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description 事件广播默认实现
 * @Author KatieZ
 * @Date Created in 16:49  16:49
 */
public class SimpleEventMulticaster implements EventMulticaster {
    /**
     * 监听事件 线程安全的
     */
    private Map<Class<?>,List<EventListener>> eventObjetcEventListenerMap = new ConcurrentHashMap<>();

    /**
     * 广播事件给所有的监听器
     * 对该事件感兴趣的监听器会处理该事件
     * @param event
     */
    @Override
    public void mutilcastEvent(AbstractEvent event) {
        List<EventListener> eventListeners = this.eventObjetcEventListenerMap.get(event.getClass());
        if(null != eventListeners){
            for(EventListener eventListener:eventListeners){
                eventListener.onEvent(event);
            }
        }
    }

    /**
     * 添加事件监听器
     * @param listener
     */
    @Override
    public void addEventListener(EventListener<?> listener) {
        Class<?> eventType = this.getEventType(listener);
        List<EventListener> eventListeners = this.eventObjetcEventListenerMap.get(eventType);
        if(null == eventListeners){
            eventListeners = new ArrayList<>();
            this.eventObjetcEventListenerMap.put(eventType, eventListeners);
        }
        eventListeners.add(listener);
    }

    /**
     * 移除事件监听器
     * @param listener
     */
    @Override
    public void removeEventListener(EventListener<?> listener) {
        Class<?> eventType = this.getEventType(listener);
        List<EventListener> eventListeners = this.eventObjetcEventListenerMap.get(eventType);
        if(null != eventListeners){
            eventListeners.remove(listener);
        }
    }

    /**
     * 获取事件监听器监听的事件类型
     * @param listener
     * @return
     */
    protected Class<?> getEventType(EventListener listener){
        // 获取类的泛型类型
        ParameterizedType parameterizedType = (ParameterizedType) listener.getClass().getGenericInterfaces()[0];
        Type typeEvent = parameterizedType.getActualTypeArguments()[0];
        return (Class<?>) typeEvent;
    }
}

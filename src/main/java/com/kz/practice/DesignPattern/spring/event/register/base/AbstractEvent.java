package com.kz.practice.DesignPattern.spring.event.register.base;

/**
 * @Description 事件对象
 * @Author KatieZ
 * @Date Created in 16:38  16:38
 */
public abstract class AbstractEvent {
    /**
     * 事件源
     */
    protected Object source;

    public AbstractEvent(Object source) {
        this.source = source;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }
}

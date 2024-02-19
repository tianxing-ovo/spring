package com.ltx.event;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义事件
 */
public class CustomEvent extends ApplicationEvent {

    /**
     * @param source 事件源
     */
    public CustomEvent(Object source) {
        super(source);
    }
}

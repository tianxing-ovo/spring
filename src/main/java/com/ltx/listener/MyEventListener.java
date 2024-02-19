package com.ltx.listener;

import com.ltx.event.CustomEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 事件监听器
 */
@Component
public class MyEventListener {

    /**
     * 处理自定义事件
     */
    @EventListener
    public void handleCustomEvent(CustomEvent customEvent) {
        System.out.println(customEvent);
    }
}

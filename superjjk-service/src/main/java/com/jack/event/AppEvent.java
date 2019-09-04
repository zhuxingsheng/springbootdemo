package com.jack.event;

import lombok.Data;

/**
 * @description: application event
 *
 * 事件使用继承ApplicationListener的listener，需要事件继承ApplicationEvent
 *
 * @author: Jack
 * @create: 2019-08-04 23:07
 */
@Data
public class AppEvent extends org.springframework.context.ApplicationEvent {


    private String eventData;


    public AppEvent(String source) {
        super(source);

        this.eventData = source;
    }
}

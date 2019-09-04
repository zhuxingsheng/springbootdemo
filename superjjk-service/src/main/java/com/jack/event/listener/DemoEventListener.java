package com.jack.event.listener;

import com.jack.event.DemoEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @description: listener
 * @author: Jack
 * @create: 2019-08-03 13:50
 */
@Component
@Slf4j
public class DemoEventListener {

    @EventListener
    @Async
    @Order(2)
    public void demoEventListener(DemoEvent demoEvent){
        log.info("demoevent listener,eventValue:{},order=2",demoEvent.getEventData());

    }
}

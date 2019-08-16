package com.jack.event.listener;

import com.jack.event.DemoEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @description: order listener
 * @author: Jack
 * @create: 2019-08-05 00:00
 */
@Slf4j
@Component
public class DemoOrderEventListener {

    @EventListener
    @Order(1)
    public void listener(DemoEvent demoEvent) {
        log.info("order=1 listener");
    }
}

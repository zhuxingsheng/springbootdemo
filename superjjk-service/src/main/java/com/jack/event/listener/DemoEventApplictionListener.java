package com.jack.event.listener;

import com.jack.event.AppEvent;
import com.jack.event.DemoEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @description: use applicationListener from event listener
 * @author: Jack
 * @create: 2019-08-03 14:15
 */
@Component
@Slf4j
public class DemoEventApplictionListener implements ApplicationListener<AppEvent> {

    @Override
    //@Async
    @Order(3)
    public void onApplicationEvent(AppEvent demoEvent) {
        log.info("listener value:{},order=3",demoEvent.getEventData());
    }

}

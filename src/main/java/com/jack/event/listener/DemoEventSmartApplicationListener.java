package com.jack.event.listener;

import com.jack.event.AppEvent;
import com.jack.event.DemoEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @description: smart application listener
 * @author: Jack
 * @create: 2019-08-03 14:46
 */
@Component
@Slf4j
public class DemoEventSmartApplicationListener implements SmartApplicationListener {


    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> aClass) {
        return aClass == AppEvent.class;
    }

    @Override
    public boolean supportsSourceType(Class<?> aClass) {
        return true;
    }

    @Override
    public int getOrder() {
        return 1;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        AppEvent event = (AppEvent)applicationEvent;
        log.info("smartapplicationListener,value:{},order=1",event.getEventData());
    }
}

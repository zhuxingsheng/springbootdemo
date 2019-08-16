package com.jack.event.listener;

import com.jack.event.DemoEventWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @description: wrapper listener
 * @author: Jack
 * @create: 2019-08-04 19:39
 */
@Slf4j
@Component
public class DemoEventWrapperListener {

    @EventListener
    //@Async
    public void wrapperListener(DemoEventWrapper demoEventWrapper){

        log.info("wrapper listener");
        demoEventWrapper.getRunnable().run();

    }
}

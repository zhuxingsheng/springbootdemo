package com.jack.event.publish;

import com.jack.event.DemoEventWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @description: publisher
 * @author: Jack
 * @create: 2019-08-04 19:24
 */
@Component
public class EventPublisher {


    @Autowired
    private ApplicationContext applicationContext;

    public void publish(Runnable runnable) {
        applicationContext.publishEvent(new DemoEventWrapper(runnable));
    }
}

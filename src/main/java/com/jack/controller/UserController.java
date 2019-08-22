package com.jack.controller;

import com.jack.config.DefaultConfig;
import com.jack.event.DemoEvent;
import com.jack.event.publish.EventPublisher;
import com.jack.remote.service.RemoteService;
import com.jack.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

/**
 * @author JackChu (jackchu2015@foxmail.com)
 * @date 2017/1/10
 */
@RestController
@Slf4j
public class UserController {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private EventPublisher eventPublisher;

    @Autowired
    private DefaultConfig defaultConfig;

    @Autowired
    private UserService userService;



    @RequestMapping(value = "/users/{username}",method = RequestMethod.GET)
    public String getUser(@PathVariable String username) {

        log.info("request /users/{},version:{}",username,defaultConfig.getVersion());

        applicationContext.publishEvent(new DemoEvent(username));

        //使用lambad方式，传递行为
        eventPublisher.publish(() -> {
            log.info("lambad publish");
        });
        return "Welcome,"+username;
    }


    @RequestMapping(value = "/remote/{v}",method = RequestMethod.GET)
    public String remote(@PathVariable String v) {

        userService.remote(v);

        return "Welcome,"+v;

    }
}

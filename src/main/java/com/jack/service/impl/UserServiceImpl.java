package com.jack.service.impl;

import com.jack.remote.service.RemoteService;
import com.jack.remote.service.RemoteService1;
import com.jack.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private RemoteService1 remoteService1;

    @Override
    public void remote(String v){

        log.info(remoteService1.toString());

        remoteService1.remoteMethod();

        RemoteService remoteService = applicationContext.getBean(RemoteService.class);
        remoteService.remoteMethod();
        remoteService.remoteMethod(v);
    }
}

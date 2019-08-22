package com.jack.service.impl;

import com.jack.remote.service.RemoteService;
import com.jack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void remote(String v){
        RemoteService remoteService = applicationContext.getBean(RemoteService.class);
        remoteService.remoteMethod();
        remoteService.remoteMethod(v);
    }
}

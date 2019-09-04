package com.jack.remote.proxy.jdk;

import com.jack.remote.service.RemoteService;

import java.lang.reflect.Proxy;

public class RemoteServiceProxy {

    public Object createProxy(Class<RemoteService> remoteClazz){
        return Proxy.newProxyInstance(remoteClazz.getClassLoader(),
                remoteClazz.getInterfaces(),new JDKProxyHandler());
    }
}

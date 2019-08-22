package com.jack.factorybean;

import com.jack.remote.proxy.cglib.CglbHandler;
import com.jack.remote.proxy.jdk.RemoteServiceProxy;
import com.jack.remote.service.RemoteService;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * factoryBean,学习factoryBean
 *
 *
 */
//@Component
public class DemoFactoryBean implements FactoryBean {

    @Override
    public Object getObject() throws Exception {
//        RemoteServiceProxy proxy = new RemoteServiceProxy();
//        return proxy.createProxy(RemoteService.class);

        RemoteService remoteService = ProxyFactory.getProxy(RemoteService.class, new CglbHandler());
        return remoteService;
    }

    @Override
    public Class<?> getObjectType() {
        return RemoteService.class;
    }
}

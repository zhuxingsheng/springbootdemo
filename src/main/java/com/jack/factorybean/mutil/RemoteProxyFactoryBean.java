package com.jack.factorybean.mutil;

import com.jack.remote.service.RemoteService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 很多remoteservice
 */
@Slf4j
public class RemoteProxyFactoryBean implements FactoryBean<Object> , MethodInterceptor {

    @Setter
    @Getter
    private Class remoteService;

    @Setter
    @Getter
    private String url;


    @Override
    public Object getObject() throws Exception {
        Object service = ProxyFactory.getProxy(remoteService, this);
        return service;
    }

    @Override
    public Class<?> getObjectType() {
        return remoteService;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("RemoteProxyFactoryBean invoke");
        Method method = invocation.getMethod();
        log.info("method:{} invoked",method.getName());

        StringBuilder sb = new StringBuilder(url);
        sb.append(method.getName());
        sb.append("?var=");
        sb.append(Arrays.toString(invocation.getArguments()));

        log.info("get url:{}",sb.toString());
        return null;
    }
}

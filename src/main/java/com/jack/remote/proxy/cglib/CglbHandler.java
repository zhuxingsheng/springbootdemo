package com.jack.remote.proxy.cglib;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
public class CglbHandler implements MethodInterceptor {


    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("cglib intercept");
        Method method = invocation.getMethod();
        log.info("method:{} invoked",method.getName());

        StringBuilder sb = new StringBuilder("http://remotehost/");
        sb.append(method.getName());
        sb.append("?var=");
        sb.append(Arrays.toString(invocation.getArguments()));

        log.info("get url:{}",sb.toString());
        return null;
    }
}

package com.lp.first.learn.old.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author LP
 * @date 2019/3/30
 */
public class DynamicProxy implements InvocationHandler {

    private Object realObject;

    public DynamicProxy(Object object) {
        this.realObject = object;
    }

    /**
     * 获取被代理接口实例对象
     *
     * @param <T>
     * @return
     */
    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(realObject.getClass().getClassLoader(), realObject.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        front();
        Object result = method.invoke(realObject,args);
        after();
        return result;
    }

    public void front() {
        System.err.println("------------------ front -----------------");
    }

    public void after() {
        System.err.println("------------------ after ------------------");
    }
}

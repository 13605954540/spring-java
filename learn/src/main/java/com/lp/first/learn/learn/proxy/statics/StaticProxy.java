package com.lp.first.learn.learn.proxy.statics;

import com.lp.first.learn.learn.proxy.common.IService;

/**
 * @author LP
 * @date 2019/4/14
 */
public class StaticProxy implements IService {

    public IService iService;

    public StaticProxy(IService iservice) {
        this.iService = iservice;
    }

    @Override
    public void eat() {
        front();
        iService.eat();
        after();
    }

    public void front() {
        System.err.println("------------------ front -----------------");
    }

    public void after() {
        System.err.println("------------------ after ------------------");
    }
}

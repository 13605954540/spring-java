package com.lp.first.learn.learn.proxy.dynamic;

import com.lp.first.learn.learn.proxy.common.IService;
import com.lp.first.learn.learn.proxy.common.IServiceImpl;

/**
 * @author LP
 * @date 2019/3/30
 */
public class Runner {

    public static void main(String[] args) {
        IService test = new DynamicProxy(new IServiceImpl()).getProxy();
        test.eat();
    }
}

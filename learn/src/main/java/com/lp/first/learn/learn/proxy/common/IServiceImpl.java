package com.lp.first.learn.learn.proxy.common;

/**
 * @author LP
 * @date 2019/3/30
 */
public class IServiceImpl implements IService {
    @Override
    public void eat() {
        System.err.println("------------ 吃饭 -------------");
    }
}

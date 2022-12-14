package com.lp.first.learn.learn.singleton;

/**
 *
 * 饿汉单例
 * @author LP
 * @date 2019/4/13
 */
public class SingleTon1 {

    private static final SingleTon1 singleTon1 = new SingleTon1();

    private SingleTon1() {

    }

    public SingleTon1 getInstance() {
        return singleTon1;
    }
}

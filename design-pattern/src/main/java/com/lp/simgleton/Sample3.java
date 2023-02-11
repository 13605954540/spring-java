package com.lp.simgleton;

/**
 * 懒汉模式
 *
 * <pre>
 *  优点:  性能好,调用getInstance才new
 *  缺点:  线程不安全
 *  解决:  加锁,双判断 成员变量加volatile,避免类加载乱序造成多例的问题
 * </pre>
 *
 */
public class Sample3 {

    private static Sample3 sample;

    private Sample3() {

    }

    public static Sample3 getInstance() {
        if(sample == null) {
            sample = new Sample3();
        }
        return sample;
    }
}

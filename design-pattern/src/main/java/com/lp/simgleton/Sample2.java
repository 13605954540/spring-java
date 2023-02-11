package com.lp.simgleton;

/**
 * 饿汉单例
 *
 * <pre>
 *  优点: 线程安全
 *  缺点: 类加载就new出来,性能有影响
 * </pre>
 *
 */
public class Sample2 {

    private static final Sample2 sample = new Sample2();

    private Sample2() {

    }

    public static Sample2 getInstance() {
        return sample;
    }
}

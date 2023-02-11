package com.lp.simgleton;

/**
 * 单例(双重检查锁)
 *
 * <pre>
 *  优点:  性能好,调用getInstance才new
 *  缺点:  线程不安全
 *  解决:  加锁,双判断 成员变量加volatile,避免类加载乱序造成多例的问题
 * </pre>
 *
 */
public class Sample {

    private static volatile Sample sample = null;

    private Sample() {

    }

    public static Sample getInstance() {
        if(sample == null) {
            synchronized(Sample.class) {
                if(sample == null) {
                    sample = new Sample();
                }
            }
        }
        return sample;
    }
}

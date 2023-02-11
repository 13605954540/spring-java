package com.lp.simgleton;

/**
 * 静态内部类单例模式(单例持有者模式)
 *
 * <pre>
 *      优点： 省资源 用的时候才实例化（类加载 不会加载内部类） 并且不加锁
 * </pre>
 */
public class Sample4 {

    private Sample4() {

    }

    private static class InnerSample {

        private final static Sample4 sample = new Sample4();

        public static Sample4 getInstance() {
            return InnerSample.sample;
        }
    }

    public static void main(String[] args) {
        Sample4 sample = Sample4.InnerSample.getInstance();
        Sample4 sample2 = Sample4.InnerSample.getInstance();
        System.err.println(sample == sample2);
    }
}

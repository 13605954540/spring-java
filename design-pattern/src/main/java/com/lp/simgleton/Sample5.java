package com.lp.simgleton;

/**
 * 枚举单例
 *
 * <pre>
 *     优点： 不用加锁 完美应用枚举单例 并且不会被反射实例化破坏单例
 * </pre>
 */
public class Sample5 {

    private enum SampleEnum {
        INSTANCE;

        private final Sample5 sample5;

        private SampleEnum() {
            sample5 = new Sample5();
        }

        public static Sample5 getInstance() {
            return SampleEnum.INSTANCE.sample5;
        }
    }
}

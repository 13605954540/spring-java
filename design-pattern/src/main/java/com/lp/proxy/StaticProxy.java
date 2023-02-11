package com.lp.proxy;

/**
 * 静态代理
 *
 * <pre>
 *     优点：  间接调用实现类，可在前后处理，增强实现代码
 *     缺点：  接口增加方法，实现类和proxy都要增加方法，增加代码维护的复杂度
 * </pre>
 */
public class StaticProxy {

    public interface Sample {
        void todo();
    }

    public class SampleImpl implements Sample {

        @Override
        public void todo() {

        }
    }

    public class ProxyPoint implements Sample {

        SampleImpl sample = new SampleImpl();

        @Override
        public void todo() {
            sample.todo();
        }
    }
}

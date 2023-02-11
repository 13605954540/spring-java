package com.lp.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理
 * <pre>
 *      优点： JDK1.8之后，效率比CGLIB高
 *      缺点： 必须定义接口
 * </pre>
 *
 */
public class JdkDynamixProxy {

    public interface Sample {
        void todo();
    }

    public class SampleImpl implements Sample {

        @Override
        public void todo() {

        }
    }

    public class CustomProxy {
        SampleImpl sample = new SampleImpl();

        public Sample getProxyObj() {
            Sample s = (Sample) Proxy.newProxyInstance(sample.getClass().getClassLoader(), sample.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    Object result = method.invoke(sample, args);
                    return result;
                }
            });
            return s;
        }
    }

    public void go() {
        CustomProxy customProxy = new CustomProxy();
        Sample sample = customProxy.getProxyObj();
        sample.todo();
    }
}

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

        void toReturn();
    }

    public class SampleImpl implements Sample {

        @Override
        public void todo() {
            System.err.println("----------------- to do ------------------");
        }

        @Override
        public void toReturn() {
            System.err.println("----------------- to return ------------------");
        }
    }

    public class CustomProxy {
        SampleImpl sample = new SampleImpl();
//        Object target;

        public Sample getProxyObj() {
            Sample s = (Sample) Proxy.newProxyInstance(sample.getClass().getClassLoader(), sample.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.err.println("--------------- 前置 ----------------");
                    Object result = method.invoke(sample, args);
                    System.err.println("--------------- 后置 ----------------");
                    return result;
                }
            });
            return s;
        }
    }

    public void go() {
        CustomProxy customProxy = new CustomProxy();
        Sample sample = customProxy.getProxyObj();
//        sample.todo();
        sample.toReturn();
    }

    public static void main(String[] args) {
        JdkDynamixProxy jdkDynamixProxy = new JdkDynamixProxy();
        jdkDynamixProxy.go();
    }
}

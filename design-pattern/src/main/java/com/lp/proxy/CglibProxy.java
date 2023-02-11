package com.lp.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB动态代理
 *
 * <pre>
 *     优点： 补充JDK动态代理只能代理接口的缺点
 *     缺点:  不能对声明final的类与方法代理 因为原理是动态生成被代理类的子类 final不能继承
 * </pre>
 */
public class CglibProxy {

    public class Sample {

        public void todo() {

        }
    }

    public class ProxyFactory implements MethodInterceptor {

        Sample sample = new Sample();

        public Sample getProxyObject() {
            //创建Enhancer对象，类似于JDK动态代理的Proxy类，下一步就是设置几个参数
            Enhancer enhancer =new Enhancer();
            //设置父类的字节码对象
            enhancer.setSuperclass(sample.getClass());
            //设置回调函数
            enhancer.setCallback(this);
            //创建代理对象
            Sample obj = (Sample) enhancer.create();
            return obj;
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("代理点收取一些服务费用(CGLIB动态代理方式)");
            Sample result = (Sample) methodProxy.invokeSuper(o, objects);
            return result;
        }

        public void go(String[] args) {
            //创建代理工厂对象
            ProxyFactory factory = new ProxyFactory();
            //获取代理对象
            Sample proxyObject = factory.getProxyObject();
            proxyObject.todo();
        }
    }
}

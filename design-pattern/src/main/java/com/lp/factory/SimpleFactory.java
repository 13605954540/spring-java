package com.lp.factory;

/**
 * 简单工厂模式
 *
 * <pre>
 *     不是一种设计模式，更像一种开发习惯
 *     优点：  封装了创建对象的过程，可以通过参数直接获取对象。把对象的创建和业务逻辑层分开
 *     缺点：  违反开闭原则 增加新产品时还是需要修改工厂类的代码
 * </pre>
 */
public class SimpleFactory {

    public interface IService {
        void doSomething();
    };

    public static class Service1 implements IService {

        @Override
        public void doSomething() {
            System.err.println("------------ one -----------");
        }
    }

    public static class Service2 implements IService {

        @Override
        public void doSomething() {
            System.err.println("------------ two -----------");
        }
    }

    public static IService createService(String s) {
        switch(s) {
            case "1":
                return new Service1();
            case "2":
                return new Service2();
            default:
                return null;
        }
    }

    public static void main(String[] args) {
        IService service = createService("2");
        service.doSomething();
    }
}

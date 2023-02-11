package com.lp.factory;

/**
 * 抽象工厂模式
 * ps：工厂模式多种生成扩展
 * <pre>
 *   优点：
 *       当一个产品族中的多个对象被设计成一起工作时，它能保证客户端始终只使用同一个产品族中的对象。
 *   缺点：
 *       当产品族中需要增加一个新的产品时，所有的工厂类都需要进行修改。
 * </pre>
 */
public class AbstractFactory {

    public class Coffee {

    }

    /**
     * 甜品类
     *
     */
    public class Dessert {

    }

    public interface FactoryService {
        Coffee createCoffee();

        Dessert createDessert();
    }

    //美式甜点工厂
    public class AmericanDessertFactory implements FactoryService {

        public Coffee createCoffee() {
            //应是子类 忽略
            return new Coffee();
        }

        public Dessert createDessert() {
            //应是子类 忽略
            return new Dessert();
        }
    }
    //意大利风味甜点工厂
    public class ItalyDessertFactory implements FactoryService {

        public Coffee createCoffee() {
            //应是子类 忽略
            return new Coffee();
        }

        public Dessert createDessert() {
            //应是子类 忽略
            return new Dessert();
        }
    }

    public class Store {
        FactoryService factoryService;

        public Store(FactoryService factoryService) {
            this.factoryService = factoryService;
        }

/*        public Factory.IService go() {
            IService iService = factoryService.createService();
            iService.doSomething();
            return iService;
        }*/
    }
}

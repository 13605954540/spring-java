package com.lp.factory;

/**
 * 工厂模式
 *
 * <pre>
 *     优点：
 *         - 用户只需要知道具体工厂的名称就可得到所要的产品，无须知道产品的具体创建过程；
 *         - 在系统增加新的产品时只需要添加具体产品类和对应的具体工厂类，无须对原工厂进行任何修改，满足开闭原则；
 *     缺点：
 *         - 每增加一个产品就要增加一个具体产品类和一个对应的具体工厂类，这增加了系统的复杂度。
 * </pre>
 */
public class Factory {

    public interface FactoryService {
        IService createService();
    }

    public class Factory1 implements FactoryService {

        @Override
        public IService createService() {
            return new Service1();
        }
    }

    public class Factory2 implements FactoryService {

        @Override
        public IService createService() {
            return new Service2();
        }
    }

    public interface IService {
        void doSomething();
    };

    public class Service1 implements IService {

        @Override
        public void doSomething() {
            System.err.println("------------ one -----------");
        }
    }

    public class Service2 implements IService {

        @Override
        public void doSomething() {
            System.err.println("------------ two -----------");
        }
    }

    public class Store {

        FactoryService factoryService;

        public Store(FactoryService factoryService) {
            this.factoryService = factoryService;
        }

        public IService go() {
            IService iService = factoryService.createService();
            iService.doSomething();
            return iService;
        }
    }

    /**
     * 执行代码
     *
     */
    public void go() {
        Store store = new Store(new Factory1());
        IService service = store.go();
        service.doSomething();
    }
}

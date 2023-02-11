package com.lp.builder;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 建造者模式
 * ps:简略版类似于自己写的es条件builder类
 *
 * <pre>
 *  优点：
 *     - 建造者模式的封装性很好。使用建造者模式可以有效的封装变化，在使用建造者模式的场景中，一般产品类和建造者类是比较稳定的，因此，将主要的业务逻辑封装在指挥者类中对整体而言可以取得比较好的稳定性。
 *     - 在建造者模式中，客户端不必知道产品内部组成的细节，将产品本身与产品的创建过程解耦，使得相同的创建过程可以创建不同的产品对象。
 *     - 可以更加精细地控制产品的创建过程 。将复杂产品的创建步骤分解在不同的方法中，使得创建过程更加清晰，也更方便使用程序来控制创建过程。
 *     - 建造者模式很容易进行扩展。如果有新的需求，通过实现一个新的建造者类就可以完成，基本上不用修改之前已经测试通过的代码，因此也就不会对原有功能引入风险。符合开闭原则。
 *
 *  缺点：
 *      造者模式所创建的产品一般具有较多的共同点，其组成部分相似，如果产品之间的差异性很大，则不适合使用建造者模式，因此其使用范围受到一定的限制。
 * </pre>
 */
public class Builder {

    @Data
    @Accessors(chain = true)
    public class Bike {

        //机架
        private String frame;

        //机身
        private String seat;
    }

    public abstract class BikeBuilder {

        protected Bike bike = new Bike();

        public abstract BikeBuilder setFrame();

        public abstract BikeBuilder setSeat();

        public abstract Bike create();
    }

    public class MobiBikeBuilder extends BikeBuilder {

        @Override
        public BikeBuilder setFrame() {
            bike.setFrame("铝合金车架");
            return this;
        }

        @Override
        public BikeBuilder setSeat() {
            bike.setSeat("真皮车座");
            return this;
        }

        @Override
        public Bike create() {
            return bike;
        }
    }

    public class OfoBikeBuilder extends BikeBuilder {

        @Override
        public BikeBuilder setFrame() {
            bike.setFrame("碳纤维车架");
            return this;
        }

        @Override
        public BikeBuilder setSeat() {
            bike.setSeat("橡胶车座");
            return this;
        }

        @Override
        public Bike create() {
            return bike;
        }
    }

    //指挥者类
    public class Director {
        private BikeBuilder builder;

        public Director(BikeBuilder param) {
            builder = param;
        }

        public Bike construct() {
            builder.setFrame();
            builder.setSeat();
            return builder.create();
        }
    }
}

package com.lp.aspect;

/**
 * 观察者模式(暂时不要了，应该是错误的)
 *
 * <pre>
 *     定义：通过为多个复杂的子系统提供一个一致的接口，而使这些子系统更加容易被访问的模式。。
 *     优点：
 *      - 降低了子系统与客户端之间的耦合度，使得子系统的变化不会影响调用它的客户类。
 *      - 对客户屏蔽了子系统组件，减少了客户处理的对象数目，并使得子系统使用起来更加容易。
 *     缺点：
 *      - 不符合开闭原则，修改很麻烦
 * </pre>
 */
public class Sample {

/*    public class Video {

        public void on() {

        }

        public void off() {

        }
    }

    public class Tv {

        public void on() {

        }

        public void off() {

        }
    }

    public class Aspect {

        Video video;

        Tv tv;

        public Aspect() {
            video = new Video();
            tv = new Tv();
        }

        public void say() {
            video.on();
            tv.on();
        }

        public void stop() {
            video.off();
            tv.off();
        }
    }*/
}

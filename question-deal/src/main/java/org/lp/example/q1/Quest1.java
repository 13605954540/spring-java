package org.lp.example.q1;

/**
 * 得出结论： 静态代码块会在类加载时只加载一次，并且会先于实例化方法，子类也会先于父类的实例化方法
 *
 */
public class Quest1 {

    public static void main(String[] args) {
        Entity1 entity1 = new Entity2();
        entity1 = new Entity2();
    }
}

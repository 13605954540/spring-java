package com.lp.first.learn.old.sjms.simpleFactory;

/**
 * 简单工厂模式
 * Created by Lin on 2018/3/16.
 */
public class Demo {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Cat cat = Factory.getClass(Cat.class);
        cat.eat();
        Dog dog = Factory.getClass(Dog.class);
        dog.eat();
    }
}

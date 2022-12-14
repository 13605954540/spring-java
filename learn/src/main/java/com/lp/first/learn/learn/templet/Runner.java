package com.lp.first.learn.learn.templet;

/**
 * @author LP
 * @date 2019/4/14
 */
public class Runner {

    public static void main(String[] args) {
        TempletAbstract t1 = new SonClass1();
        t1.go();

        TempletAbstract t2 = new SonClass2();
        t2.go();
    }
}

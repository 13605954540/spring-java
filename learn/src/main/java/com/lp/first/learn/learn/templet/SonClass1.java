package com.lp.first.learn.learn.templet;

/**
 * @author LP
 * @date 2019/4/14
 */
public class SonClass1 extends TempletAbstract {
    @Override
    public void run() {
        System.err.println("son1先跑步");
    }

    @Override
    public void walk() {
        System.err.println("son1跑累了,变成走路");
    }

    @Override
    public void stop() {
        System.err.println("son1虚脱了,停下来休息");
    }
}

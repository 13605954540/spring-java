package com.lp.first.learn.learn.templet;

/**
 * @author LP
 * @date 2019/4/14
 */
public class SonClass2 extends TempletAbstract {
    @Override
    public void run() {
        System.err.println("son2先跑步");
    }

    @Override
    public void walk() {
        System.err.println("son2跑累了,变成走路");
    }

    @Override
    public void stop() {
        System.err.println("son2虚脱了,停下来休息");
    }
}

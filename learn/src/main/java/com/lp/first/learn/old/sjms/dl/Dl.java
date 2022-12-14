package com.lp.first.learn.old.sjms.dl;

/**
 * 单例模式
 * Created by Lin on 2018/3/15.
 */
public class Dl {

    private static Dl dl = new Dl();

    private Dl(){

    };

    public static Dl getInstance(){
        return dl;
    }

    public static void main(String[] args) {
        Dl d = Dl.getInstance();
        System.err.println(d);
        Dl d2 = Dl.getInstance();
        System.err.println(d2);
    }
}

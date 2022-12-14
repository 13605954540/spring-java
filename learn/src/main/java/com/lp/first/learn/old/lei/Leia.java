package com.lp.first.learn.old.lei;

/**
 * Created by Lin on 2018/3/22.
 */
public class Leia {

    static{
        System.err.println("静态代码块");
    }

    public Leia(){
        System.err.println("进入构造器");
    }
    
    public void a(){
        System.err.println("父类");
    }
}

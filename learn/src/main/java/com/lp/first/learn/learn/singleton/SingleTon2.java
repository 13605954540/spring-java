package com.lp.first.learn.learn.singleton;

/**
 *
 * 懒汉单例
 * @author LP
 * @date 2019/4/13
 */
public class SingleTon2 {

    private static volatile SingleTon2 singleTon2 = null;

    private SingleTon2() {

    }

    public SingleTon2 getInstance() {
        if(singleTon2 == null) {
            synchronized(SingleTon2.class) {
                if(singleTon2 == null) {
                    singleTon2 = new SingleTon2();
                }
            }
        }
        return singleTon2;
    }
}

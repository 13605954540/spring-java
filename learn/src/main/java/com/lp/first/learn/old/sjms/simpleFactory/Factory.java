package com.lp.first.learn.old.sjms.simpleFactory;

/**
 * Created by Lin on 2018/3/16.
 */
public class Factory {

    public static <T> T getClass(Class<T> cla) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        T obj = (T) Class.forName(cla.getName()).newInstance();
        return obj;
    }
}

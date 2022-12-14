package com.lp.first.learn.old.jvm;

/**
 * Created by Lin on 2018/3/22.
 */
public class Lei {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
//        ClassLoader clazz = Leia.class.getClassLoader();
//        Class a = clazz.loadClass("learn.lei.Leia");
//        Class c = Class.forName("learn.lei.Leia");
//        Leia b = (Leia)a.newInstance();
        System.err.println(ret());
    }

    public static int ret(){
        int a = 1;
        try{
            return ++a;
        }catch(Exception e){

        }finally {
            a = a + 2;
        }
        return a;
    }
}

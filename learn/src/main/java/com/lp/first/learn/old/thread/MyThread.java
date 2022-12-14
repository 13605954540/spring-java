package com.lp.first.learn.old.thread;

/**
 * @author LP
 * @date 2019/3/23
 */
public class MyThread implements Runnable {

    private String name;

    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        Long start = System.currentTimeMillis();
        System.err.println(name + ": " + "start");
        for(int i = 0;i < 1000000;i++) {
            System.err.println(name + ": " + i);
        }
        Long end = System.currentTimeMillis();
        System.err.println(name + ": " + "end");
        System.err.println(name + " 耗时: " + (end - start)/1000 + "秒");
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new MyThread("A"));
        t1.start();
        Thread t2 = new Thread(new MyThread("B"));
        t2.start();
//        Thread t3 = new Thread(new MyThread("C"));
//        t3.start();
        long endTime = System.currentTimeMillis();
    }
}

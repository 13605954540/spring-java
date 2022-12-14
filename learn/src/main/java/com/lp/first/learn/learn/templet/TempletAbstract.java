package com.lp.first.learn.learn.templet;

/**
 * @author LP
 * @date 2019/4/14
 */
public abstract class TempletAbstract {

    public abstract void run();

    public abstract void walk();

    public abstract void stop();

    public final void go() {
        run();
        walk();
        stop();
    }
}

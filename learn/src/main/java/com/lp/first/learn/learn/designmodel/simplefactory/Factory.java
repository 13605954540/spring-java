package com.lp.first.learn.learn.designmodel.simplefactory;

import com.lp.first.learn.learn.designmodel.common.TestInterface;
import com.lp.first.learn.learn.designmodel.common.TestInterfaceImpl1;
import com.lp.first.learn.learn.designmodel.common.TestInterfaceImpl2;

/**
 * @author LP
 * @date 2019/4/12
 */
public class Factory {

    public static TestInterface initImpl1() {
        return new TestInterfaceImpl1();
    }

    public static TestInterface initImpl2() {
        return new TestInterfaceImpl2();
    }
}

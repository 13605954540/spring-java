package com.lp.first.learn.learn.designmodel.ordinaryfactory;

import com.lp.first.learn.learn.designmodel.common.TestInterface;

/**
 * @author LP
 * @date 2019/4/12
 */
public class Run {

    public static void main(String[] args) {
        Provider provider = new TestImpl1Provider1();
        TestInterface testInterface = provider.createObj();
    }
}

package com.lp.first.learn.learn.designmodel.ordinaryfactory;

import com.lp.first.learn.learn.designmodel.common.TestInterface;
import com.lp.first.learn.learn.designmodel.common.TestInterfaceImpl1;

/**
 * @author LP
 * @date 2019/4/12
 */
public class TestImpl1Provider1 implements Provider {
    @Override
    public TestInterface createObj() {
        return new TestInterfaceImpl1();
    }
}

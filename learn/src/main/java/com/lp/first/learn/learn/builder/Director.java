package com.lp.first.learn.learn.builder;

/**
 * 指挥者
 *
 * @author LP
 * @date 2019/4/14
 */
public class Director {

    public static Model direct() {
        return new IBuilderImpl()
                .buildHead()
                .buildBody()
                .buildFoot()
                .build();
    }
}

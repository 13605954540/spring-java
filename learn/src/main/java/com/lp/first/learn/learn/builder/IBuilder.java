package com.lp.first.learn.learn.builder;

/**
 * @author LP
 * @date 2019/4/14
 */
public interface IBuilder {

    IBuilder buildHead();

    IBuilder buildBody();

    IBuilder buildFoot();

    Model build();
}

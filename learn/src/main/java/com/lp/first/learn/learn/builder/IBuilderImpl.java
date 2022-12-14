package com.lp.first.learn.learn.builder;

/**
 * @author LP
 * @date 2019/4/14
 */
public class IBuilderImpl implements IBuilder {

    private Model model;

    public  IBuilderImpl() {
        this.model = new Model();
    }

    @Override
    public IBuilder buildHead() {
        this.model.setHead("头部建造");
        return this;
    }

    @Override
    public IBuilder buildBody() {
        this.model.setBody("身体建造");
        return this;
    }

    @Override
    public IBuilder buildFoot() {
        this.model.setFoot("脚步建造");
        return this;
    }

    public Model build() {
        return this.model;
    }
}

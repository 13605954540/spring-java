package com.lp.first.framework.bean;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * @author LP
 * @date 2018/5/1
 */
@ApiModel("分页参数类")
public class PageParam<T> implements Serializable {

    private Integer size = 10;

    private Integer current = 1;

    private T param;

    public Integer getSize() {
        return size;
    }

    public PageParam<T> setSize(Integer size) {
        this.size = size;
        return this;
    }

    public Integer getCurrent() {
        return current;
    }

    public PageParam<T> setCurrent(Integer current) {
        this.current = current;
        return this;
    }

    public T getParam() {
        return param;
    }

    public PageParam<T> setParam(T param) {
        this.param = param;
        return this;
    }
}

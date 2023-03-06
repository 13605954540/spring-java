package org.example.base;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BasePageCondition<T> extends Page {

    T condition;

    public T getCondition() {
        return condition;
    }

    public BasePageCondition<T> setCondition(T condition) {
        this.condition = condition;
        return this;
    }

    @JsonIgnore
    public Page getPageParam() {
        return this;
    }
}

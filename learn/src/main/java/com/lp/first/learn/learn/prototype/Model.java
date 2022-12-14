package com.lp.first.learn.learn.prototype;

import lombok.ToString;

import java.io.Serializable;

/**
 * @author LP
 * @date 2019/4/14
 */
@ToString
public class Model implements Serializable {

    private static final long serialVersionUID = -7693544693865593525L;

    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public Model setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Model setName(String name) {
        this.name = name;
        return this;
    }
}

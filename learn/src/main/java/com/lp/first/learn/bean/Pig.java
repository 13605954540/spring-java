package com.lp.first.learn.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author LP
 * @date 2019/3/30
 */
public class Pig implements Serializable {

    private static final long serialVersionUID = -7919776871507766661L;

    private String id;

    private String name;

    private Integer age;

    private Integer sex;

    public String getId() {
        return id;
    }

    public Pig setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Pig setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public Pig setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Integer getSex() {
        return sex;
    }

    public Pig setSex(Integer sex) {
        this.sex = sex;
        return this;
    }
}

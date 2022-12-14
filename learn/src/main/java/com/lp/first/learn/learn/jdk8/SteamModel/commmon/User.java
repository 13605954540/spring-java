package com.lp.first.learn.learn.jdk8.SteamModel.commmon;

public class User {

    private String id;

    private String name;

    private Integer age;

    private Integer moneyCount;

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public User setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Integer getMoneyCount() {
        return moneyCount;
    }

    public User setMoneyCount(Integer moneyCount) {
        this.moneyCount = moneyCount;
        return this;
    }
}

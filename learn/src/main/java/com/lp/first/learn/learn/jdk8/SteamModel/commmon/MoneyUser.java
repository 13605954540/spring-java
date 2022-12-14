package com.lp.first.learn.learn.jdk8.SteamModel.commmon;

public class MoneyUser {

    private String id;

    private String name;

    private Integer age;

    private Integer moneyCount;

    public String getId() {
        return id;
    }

    public MoneyUser setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public MoneyUser setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public MoneyUser setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Integer getMoneyCount() {
        return moneyCount;
    }

    public MoneyUser setMoneyCount(Integer moneyCount) {
        this.moneyCount = moneyCount;
        return this;
    }
}

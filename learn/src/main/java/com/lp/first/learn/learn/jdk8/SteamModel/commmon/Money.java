package com.lp.first.learn.learn.jdk8.SteamModel.commmon;

public class Money {

    private String moneyId;

    private String userId;

    private Integer moneyCount;

    public String getMoneyId() {
        return moneyId;
    }

    public Money setMoneyId(String moneyId) {
        this.moneyId = moneyId;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public Money setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public Integer getMoneyCount() {
        return moneyCount;
    }

    public Money setMoneyCount(Integer moneyCount) {
        this.moneyCount = moneyCount;
        return this;
    }
}

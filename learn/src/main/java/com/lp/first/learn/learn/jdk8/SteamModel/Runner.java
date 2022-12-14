package com.lp.first.learn.learn.jdk8.SteamModel;

import com.google.common.collect.Lists;
import com.lp.first.learn.learn.jdk8.SteamModel.commmon.Money;
import com.lp.first.learn.learn.jdk8.SteamModel.commmon.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Runner {

    public static void main(String[] args) {
//        System.err.println(getOptionalData());
        String str = "111";
        System.err.println(Optional.ofNullable(str).orElse("6"));
    }

    /**
     * Optional - orElse
     *
     * @return
     */
    public static String getOptionalData() {
        User user = new User().setName("123");
        return Optional.ofNullable(user).map(User::getName).orElse("1");
    }

    /**
     * anyMatch
     *
     * @return
     */
    public static boolean getBoolean() {
        List<Integer> list = Lists.newArrayList(1,3,5);
        return list.stream().anyMatch(item -> item.equals(2));
    }

    public static List<Map<String, Object>> merge() {
        User user1 = new User();
        user1.setId("1");
        user1.setName("小花");
        user1.setAge(7);
        User user2 = new User()
            .setId("2")
            .setName("小红")
            .setAge(8);
        User user3 = new User()
            .setId("3")
            .setName("小黑")
            .setAge(9);
        List<User> users = Lists.newArrayList(user1, user2, user3);

        Money money1 = new Money()
            .setMoneyId("1")
            .setUserId("1")
            .setMoneyCount(111);
        Money money3 = new Money()
            .setMoneyId("3")
            .setUserId("3")
            .setMoneyCount(333);
        List<Money> monies = Lists.newArrayList(money1, money3);

        List<User> result = users.stream().map(item -> {
            monies.stream()
                .filter(money -> money.getUserId().equals(item.getId()))
                .findAny()
                .map(m -> item.setMoneyCount(m.getMoneyCount()));
            return item;
        }).collect(Collectors.toList());

        result.forEach(item -> {
            System.err.println(item.getId() + ": " + item.getMoneyCount());
        });
        return null;
    }
}

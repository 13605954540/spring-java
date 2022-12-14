package com.lp.first.learn.jdk8;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LP
 * @date 2018/5/1
 */
public class Lambda {

    public static void main(String[] args) {
//        int[] strs = {5,2,6,7,87,1,5,9,18,10,6};
//        List<Object> objectList = Arrays.asList(strs);
//        List<Integer> list = new ArrayList();
//        objectList.forEach(item -> list.add((Integer)item));
//        test3(list);

//        List<User> list = new ArrayList<>();
//        User user1 = new User();
//        user1.setId("1");
//        user1.setUserName("名称");
//        user1.setAge(1);
//        User user2 = new User();
//        user2.setId("2");
//        user2.setUserName("名称");
//        user2.setAge(2);
//        User user3 = new User();
//        user3.setId("3");
//        user3.setUserName("名称");
//        user3.setAge(3);
//        User user4 = new User();
//        user4.setId("4");
//        user4.setUserName("另外的");
//        user4.setAge(4);
//        list.add(user1);
//        list.add(user2);
//        list.add(user3);
//        list.add(user4);
//
//        System.err.println(test10(list));
//        list.stream().collect(Collectors.groupingBy(User::getUserName, Collectors.toList()));
//        System.err.println(sets);
//        List<String> list = objectList.stream().map(Integer:valueOf).collect(Collectors.toList());
//        test1(list);
    }

//    public static Set<User> test10(List<User> user) {
//        Set<User> sets = new TreeSet<User>((user1, user2) -> user1.getUserName().compareTo(user2.getUserName()));
//        sets.addAll(user);
//        return sets;
//    }

    public static void test1(List<Integer> list) {
        list.forEach(System.err::println);
    }

    public static void test2(List<Integer> list) {
        long count = list.stream()
            .mapToInt(item -> (int) item).sum();
        System.err.println(count);
    }

    public static void test3(List<Integer> list) {
        List<Integer> it = list.stream()
            .sorted((a, b) -> b.compareTo(a))
            .collect(Collectors.toList());
        System.err.println(it);
    }
}

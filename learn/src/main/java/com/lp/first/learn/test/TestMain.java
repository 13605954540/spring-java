package com.lp.first.learn.test;

import org.assertj.core.util.Lists;

import java.util.List;

/**
 * @author LP
 * @date 2020/5/17
 */
public class TestMain {

    public static void main(String[] args) {
//        Test test = new Test();
//        test.sys();
//        Set<String> set = new HashSet<>();
//        set.add("v");
//        set.add("a");
//        set.add("c");
//        set.add("a");
//        set.remove("a");
//        System.err.println(set);

        List<Integer> list = Lists.newArrayList(4,2,3,1,7,5,6,9,8);
        for(int i = 0; i < list.size(); i++) {
            for(int y = i + 1; y < list.size(); y++) {
                if(list.get(i) > list.get(y)) {
                    int c = list.get(i);
                    list.set(i, list.get(y));
                    list.set(y, c);
                }
            }
        }
        System.err.println(list);
    }
}

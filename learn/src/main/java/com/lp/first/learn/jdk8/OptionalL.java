package com.lp.first.learn.jdk8;

import com.lp.first.learn.bean.Dog;
import com.lp.first.learn.bean.Pig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author LP
 * @date 2019/4/11
 */
public class OptionalL {

    public static void main(String[] args) {
        String testStr = null;
        Pig pig = new Pig()
                .setAge(1)
                .setId(testStr)
                .setName(testStr)
                .setSex(1);
        Optional<List<Integer>> optional = Optional.of(new ArrayList<>(Arrays.asList(4,5,7)));
        Optional<List<Integer>> opt = optional.filter(item -> item.contains(7));
        System.err.println(opt);

    }
}

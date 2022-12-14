package com.lp.first.learn.learn.prototype;

import org.assertj.core.util.Lists;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LP
 * @date 2019/4/13
 */
public class Runner {

    public static void main(String[] args) {
        Prototype prototype = new Prototype();
        Map<String,Object> map =  new HashMap<>();
        map.put("key","value");
        prototype.setName("名称")
                .setInLow(1)
                .setInHigh(5)
                .setMap(map)
                .setModel(new Model()
                        .setId("1")
                        .setName("前"))
                .setList(Lists.newArrayList("1","2","3"));
        Prototype prototype1 = prototype.clone();
        Integer a = prototype1.getInHigh();
        a = 9;
        Map map2 = prototype1.getMap();
        map2.put("key","嘿嘿");
        prototype1.setInHigh(a);
        prototype1.setModel(prototype1.getModel().setId("2").setName("后"));
        prototype1.setList(Lists.newArrayList("4","5","6"));
//        prototype1.setInLow(2).setInHigh(3);
        System.err.println(prototype.getModel().toString());
        System.err.println(prototype1.getModel().toString());
        System.err.println(prototype.getInHigh());
        System.err.println(prototype1.getInHigh());
        System.err.println(prototype.getMap());
        System.err.println(prototype1.getMap());
    }
}

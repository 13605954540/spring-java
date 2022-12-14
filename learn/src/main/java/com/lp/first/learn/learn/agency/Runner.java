package com.lp.first.learn.learn.agency;

/**
 * @author LP
 * @date 2019/4/15
 */
public class Runner {

    public static void main(String[] args) {
        Human human1 = new Human("小华");
        Human human2 = new Human("小明");
        human1.say("你好啊,小明");
        human2.say("你好啊,小华");
    }
}

package com.lp.first.learn.learn.agency;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author LP
 * @date 2019/4/15
 */
public class Human {

    private String name;

    public Human(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Human setName(String name) {
        this.name = name;
        return this;
    }

    public void say(String message) {
        QQ.send(this,message);
    }
}

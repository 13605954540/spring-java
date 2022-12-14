package com.lp.first.learn.learn.agency;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author LP
 * @date 2019/4/15
 */
public class QQ {

    public static void send(Human human,String message) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.err.println(sdf.format(new Date()) + " " + human.getName() + ": " + message);
    }
}

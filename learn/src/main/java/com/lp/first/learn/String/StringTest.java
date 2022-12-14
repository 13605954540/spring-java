package com.lp.first.learn.String;

/**
 * @author LP
 * @date 2018/5/1
 */
public class StringTest {

    public static void main(String[] args) {
        String a = "12345";
        a = a.replace(a.substring(2,4),"9832");
        System.err.println(a);
    }
}

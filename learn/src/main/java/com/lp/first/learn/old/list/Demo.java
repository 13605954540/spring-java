package com.lp.first.learn.old.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * List和set
 * Created by Lin on 2018/3/25.
 */
public class Demo {

    public static void main(String[] args) {
        List<Integer> set = new ArrayList<Integer>();
        set.add(2);
        set.add(5);
        set.add(7);
        set.add(2);
        set.add(4);
        set.add(1);
        set.add(5);
        int a = 2;
        Iterator it = set.iterator();
        while(it.hasNext()){
            if(it.next().equals(5) && a == 2){
                System.err.println("00000");
                it.remove();
                a = 1;
            }
        }
        System.err.println(set);
//        System.err.println();
//        for(Integer s : set){
//            if(s == 5){
//                set.remove(s);
//            }
//            System.err.println(s);
//        }

        List<Integer> list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
    }
}

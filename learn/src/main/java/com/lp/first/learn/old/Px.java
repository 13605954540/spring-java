package com.lp.first.learn.old;

import java.util.LinkedList;
import java.util.List;

/**
 * 排序算法
 * Created by Lin on 2018/2/28.
 */
public class Px {
    public static void main(String[] args){
        List<Integer> list = new LinkedList<Integer>();
        list.add(5);
        list.add(3);
        list.add(4);
        list.add(1);
        list.add(9);
        list.add(7);
        list.add(10);
        list.add(8);
        list.add(2);
        list.add(6);
//        Px.insertSort(list);
        Px.quickSort(list,0,list.size()-1);
        System.err.println(list);
    }

    //冒泡排序
    public static void bubbleSort(List<Integer> list){
        int c = 0;
        for (int i = 0;i<list.size();i++){
            for(int y = 0;y<list.size() - 1;y++){
                if(list.get(y) > list.get(y+1)){
                    c = list.get(y);
                    list.set(y,list.get(y + 1));
                    list.set(y+1,c);
                }
            }
        }
    }

    //选择排序
    public static void selectSort(List<Integer> list){
        int c = 0;
        for (int i = 0;i<list.size() - 1;i++){
            for(int y = i+1;y<list.size();y++){
                if(list.get(i) > list.get(y)){
                    c = list.get(y);
                    list.set(y,list.get(i));
                    list.set(i,c);
                }
            }
        }
    }

    //插入排序
    public static void insertSort(List<Integer> list){
        int j,t = 0;
        for (int i = 1; i < list.size(); i++) {
            t = list.get(i);
            for (j = i - 1; j >= 0 && t < list.get(j); j--) {
                list.set(j + 1, list.get(j));
            }
            list.set(j+1,t);
        }
    }

    // 分治法快速排序(效率最高)
    public static void quickSort(List<Integer> list, int low, int high) {// 传入low=0，high=array.length-1;
        int pivot, p_pos, i, t;// pivot->位索引;p_pos->轴值。
        if (low < high) {
            p_pos = low;
            System.err.println("<后面的index: "+p_pos);
            pivot = list.get(p_pos);
            for (i = low + 1; i <= high; i++) {
                if (list.get(i) < pivot) {
//                    System.err.println(i+" < "+pivot);
                    p_pos++;
                    t = list.get(p_pos);
                    list.set(p_pos, list.get(i));
                    list.set(i, t);
                }
            }
            t = list.get(low);
            list.set(low,list.get(p_pos));
            list.set(p_pos,t);
            // 分而治之
            quickSort(list, low, p_pos - 1);// 排序左半部分
            quickSort(list, p_pos + 1, high);// 排序右半部分
        }
    }
}

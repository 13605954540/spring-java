package com.lp.deckor;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * 装饰者模式
 * <pre>
 *     优点:  对原实现类进行增强（类似继承）
 * </pre>
 */
public class Sample {

    public static void main(String[] args) throws Exception{
        //创建BufferedWriter对象
        //创建FileWriter对象
        FileWriter fw = new FileWriter("C:\\Users\\Think\\Desktop\\a.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        //写数据
        bw.write("hello Buffered");
        bw.close();
    }
}

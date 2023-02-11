package com.lp.proto;

import com.alibaba.fastjson.JSONObject;

import java.io.*;

/**
 * 原型模式
 * <pre>
 *      用一个已经创建的实例作为原型，通过复制该原型对象来创建一个和原型对象相同的新对象。
 *      实现：
 *          浅克隆：创建一个新对象，新对象的属性和原来对象完全相同，对于非基本类型属性，仍指向原有属性所指向的对象的内存地址。
 *          深克隆：创建一个新对象，属性中引用的其他对象也会被克隆，不再指向原有对象地址。
 * </pre>
 */
public class Proto {

    public class Father implements Cloneable {

        private String name;

        private Son son;

        /**
         * 浅克隆（father的son仍然会随着f改变而改变）
         *
         * @return
         * @throws CloneNotSupportedException
         */
        public Father clone() throws CloneNotSupportedException {
            return (Father) super.clone();
        }

        /**
         * 深克隆
         *
         * @return
         * @throws CloneNotSupportedException
         */
        public Father clone2() throws IOException, ClassNotFoundException {
            //内存数组输出流
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            //序列化流
            ObjectOutputStream oos = new ObjectOutputStream(bao);
            //将数据tea写入序列化流中,随后会被传递到内存数组输出流中,将对象序列化为byte[]类型的数据
            oos.writeObject(this);
            //从内存数组输出流中获取到tea的byte[]类型的数据,传入内存数组输入流
            ByteArrayInputStream bai = new ByteArrayInputStream(bao.toByteArray());
            //将内存数组输入流传给反序列化流,这样也实现了byte[]类型的数据的传递
            ObjectInputStream ois = new ObjectInputStream(bai);
            //使用readObject,从反序列化流中读取数据,将byte[]类型的数据反序列化成Teacher对象
            Father father2 = (Father) ois.readObject();
            return (Father) ois.readObject();
        }

        /**
         * 深克隆(用其他api)
         *
         * @return
         */
        public Father clone3()  {
            return JSONObject.parseObject(JSONObject.toJSONString(this), Father.class);
        }
    }

    public class Son {}
}

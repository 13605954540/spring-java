package com.lp.first.learn.old.reflex;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 反射应用
 * Created by Lin on 2018/3/17.
 */
public class ReflexDemo {

    private static SimpleDateFormat simpleDateFormat = null;

    public static void main(String[] args) throws Exception {
        Map map = new HashMap();
//        map.put("age",6);
//        map.put("id",2);
//        map.put("name","测试员");
//        map.put("birthDay","1991-02-05");
//        Bean bean = mapToBean(map, Bean.class);
        Bean bean = new Bean();
        bean.setAge(2);
        bean.setBirthDay(new Date());
        bean.setId(5);
        map = beanToMap(bean);
    }

    /**
     * 将map转为Bean实体类
     * @param map
     * @param cla
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T>T mapToBean(Map map,Class<T> cla) throws Exception {
        Field[] fields = cla.getDeclaredFields();
        T t = cla.newInstance();
        String str = "";
        Method method = null;
        Object value = null;
        for (Object key : map.keySet()){
            str = key + "";
            for(Field m: fields){
                if(str.equals(m.getName())){
                    if(isEqualsForType(m.getType(),"class java.util.Date") && isEqualsForType(map.get(key).getClass(), "class java.lang.String")){
                        value = strToDate(map.get(key));
                    }else{
                        value = map.get(key);
                    }
                    method = cla.getMethod("set"+str.substring(0,1).toUpperCase()+str.substring(1),m.getType());
                    method.invoke(t,value);
                }
            }
        }
        return t;
    }

    /**
     * 将Bean实体类转为map
     * @param object
     * @return
     * @throws Exception
     */
    public static Map beanToMap(Object object) throws Exception {
        Map res = new HashMap();
        Class cla = object.getClass();
        Method[] methods = cla.getMethods();
        for(Method method : methods){
            if(method.getName().startsWith("get") && !("getClass").equals(method.getName()) && method.invoke(object) != null){
                res.put(getKeyName(method), method.invoke(object));
            }
        }
        return res;
    }

    /**
     * 将getXXX转为对应的map key
     * @param method
     * @return
     */
    public static String getKeyName(Method method){
        String name = method.getName();
        name = name.substring(3);
        StringBuffer res = new StringBuffer(name);
        res.replace(0,1,name.substring(0,1).toLowerCase());
        return res.toString();
    }

    /**
     * 对比方法的类型
     * @param obj
     * @param type
     * @return
     */
    public static boolean isEqualsForType(Object obj,String type){
        String s = obj.toString();
        if(type.equals(s)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 根据前台传的日期字符串长度转为相应的Date值
     * @param object
     * @return
     * @throws ParseException
     */
    public static Date strToDate(Object object) throws ParseException {
        String s = object == null ? "" : object.toString();
        String sdf = "";
        if(s.length() == 10){
            sdf = "yyyy-MM-dd";
        }else{
            sdf = "yyyy-MM-dd HH:mm:ss";
        }
        simpleDateFormat = new SimpleDateFormat(sdf);
        return simpleDateFormat.parse(s);
    }
}

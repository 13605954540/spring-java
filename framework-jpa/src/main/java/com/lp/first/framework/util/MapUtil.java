package com.lp.first.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author LP
 * @date 2018/5/1
 */
public class MapUtil {

    private final static Logger logger = LoggerFactory.getLogger(MapUtil.class);

    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * map key对应的value转为Integer
     *
     * @param map
     * @param key
     * @return
     */
    public static Integer getInteger(Map<Object, Object> map, Object key) {
        try {
            if (map != null && map.containsKey(key)) {
                return Integer.valueOf(map.get(key).toString());
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * map key对应的value转为Long
     *
     * @param map
     * @param key
     * @return
     */
    public static Long getLong(Map<Object, Object> map, Object key) {
        try {
            if (map != null && map.containsKey(key)) {
                return Long.valueOf(map.get(key).toString());
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * map key对应的value转为String
     *
     * @param map
     * @param key
     * @return
     */
    public static String getString(Map<Object, Object> map, Object key) {
        try {
            if (map != null && map.containsKey(key)) {
                return map.get(key).toString();
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * map key对应的value转为Date
     *
     * @param map
     * @param key
     * @return
     */
    public static Date getDate(Map<Object, Object> map, Object key) {
        try {
            if (map != null && map.containsKey(key)) {
                String str = map.get(key).toString();
                Date res = null;
                if (str.length() == 10) {
                    res = sdf.parse(str + " 00:00:00");
                } else {
                    res = sdf.parse(str);
                }
                return res;
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * 判断map中key对应的值是否为空
     *
     * @param map
     * @param key
     * @return
     */
    public static boolean isEmpty(Map map, Object key) {
        if (map != null && map.containsKey(key)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 判断map key对应的value不为空
     *
     * @param map
     * @param key
     * @return
     */
    public static boolean isNotEmpty(Map map, Object key) {
        return !MapUtil.isEmpty(map, key);
    }

    /**
     * map转成实体类
     *
     * @param m
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> m, Class<T> type) {
        Map<String, Object> data = toMap(m);
        T bean = null;
        try {
            bean = type.newInstance();
        } catch (Exception e) {
            return null;
        }
        Method[] methods = type.getDeclaredMethods();
        for (Method method : methods) {
            try {
                if (method.getName().startsWith("set")) {
                    if (method.getGenericParameterTypes().length == 1) {
                        Class clazz = method.getParameterTypes()[0];
                        String field = method.getName();
                        field = field.substring(field.indexOf("set") + 3);
                        field = field.toLowerCase().charAt(0)
                            + field.substring(1);
                        Object dataValue = data.get(field);
                        if (dataValue != null) {
                            if (Date.class.equals(clazz)) {
                                Date date = null;
                                String innerValue = dataValue.toString();
                                if(innerValue.contains("-")) {
                                    if (innerValue.indexOf(':') < 0) {
                                        innerValue += " 00:00:00";
                                    }
                                    date = new SimpleDateFormat("yyyy-MM-dd").parse(innerValue);
                                } else {
                                    date = new Date((Long) dataValue);
                                }
                                method.invoke(bean, new Object[]{date});
                            } else if (Timestamp.class.equals(clazz)) {
                                Timestamp timestamp = new Timestamp(new Date().getTime());
                                method.invoke(bean, new Object[]{timestamp});
                            } else if(Boolean.class.equals(clazz)) {
                                System.err.println(data.get(field).getClass());
                                if(data.get(field).getClass().equals(Integer.class)) {
                                    if(data.get(field).equals(1)) {
                                        method.invoke(bean, new Object[]{Boolean.TRUE});
                                    } else if(data.get(field).equals(0)) {
                                        method.invoke(bean, new Object[]{Boolean.FALSE});
                                    }
                                }
                            }else {
                                Object object = clazz.getConstructor(new Class[]{String.class})
                                    .newInstance(new Object[]{dataValue});
                                method.invoke(bean, new Object[]{object});
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bean;
    }

    /**
     * 将前台参数转成可控map
     *
     * @param parameterMap
     * @return
     * @author linping
     */
    public static Map<String, Object> toMap(Map<String, Object> parameterMap) {
        Map<String, Object> returnMap = new HashMap();
        Iterator entries = parameterMap.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        Object value = null;
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            value = entry.getValue();
            returnMap.put(name, value);
        }
        return returnMap;
    }

    /**
     * 将javaBean转换成Map
     *
     * @param javaBean javaBean
     * @return Map对象
     */
    public static Map<String, Object> beanToMap(Object javaBean) {
        Map<String, Object> result = new HashMap<>();
        Method[] methods = javaBean.getClass().getDeclaredMethods();
        for (Method method : methods) {
            try {
                if (method.getName().startsWith("get")) {
                    String field = method.getName();
                    field = field.substring(field.indexOf("get") + 3);
                    field = field.toLowerCase().charAt(0) + field.substring(1);

                    Object value = method.invoke(javaBean, (Object[]) null);
                    result.put(field, null == value ? "" : value.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}

package com.lp.first.framework.util;

/**
 * @author LP
 * @Date 2018/5/18 22:09
 */
public class StringUtil {

    public static Boolean isEmpty(String str) {
        return (str == null || "".equals(str)) ? true : false;
    }

    public static Boolean isNotEmpty(String str) {
        return (str != null && !"".equals(str)) ? true : false;
    }
}

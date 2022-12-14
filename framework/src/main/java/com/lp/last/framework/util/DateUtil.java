package com.lp.last.framework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author LP
 * @date 2018/5/1
 */
public class DateUtil {

    private final static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

    private final static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 将字符串日期转化为date(根据常见的日期长度直接转化)
     * @param date 字符串日期
     * @return
     */
    public static Date stringToDate(String date){
        Date res = null;
        try {
            if(date.length() == 10){
                res = sdf1.parse(date);
            }else{
                res = sdf2.parse(date);
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 根据pattern格式将字符串转日期
     * @param date 字符串日期
     * @param pattern 日期格式
     * @return
     */
    public static Date stringToDate(String date,String pattern){
        Date res = null;
        try {
            res = new SimpleDateFormat(pattern).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 计算前后两天相差天数
     * @param startTime 初始日期
     * @param endTime 结束日期
     * @return
     */
    public static Integer countDays(String startTime,String endTime){
        Date s = DateUtil.stringToDate(startTime);
        Date e = DateUtil.stringToDate(endTime);
        long sTime = s.getTime();
        long eTime = e.getTime();
        return (int)(eTime - sTime)/1000/60/60/24;
    }

    /**
     * 判断结束日期是否比初试日期大
     * @param startTime 初试日期
     * @param endTime 结束日期
     * @return
     */
    public static boolean isBigger(String startTime,String endTime){
        Date s = DateUtil.stringToDate(startTime);
        Date e = DateUtil.stringToDate(endTime);
        return e.getTime() >= s.getTime() ? true : false;
    }

    /**
     * 日期天数加减得到新的日期
     * @author Lin
     * @param date 初始日期
     * @param days 天数
     * @return
     */
    public static Date dateAddtion(Date date,int days){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }
}

package com.lp.test.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LocationAwareLogger;

import java.text.MessageFormat;

/**
 * 来自苹神写的日志打印工具类
 *
 * <PRE>
 *     打印调用方的完整报名以及行数
 * </PRE>
 */
public class LogUtils {

    public static Logger logger = LoggerFactory.getLogger(LogUtils.class);

    //类名
    private static final String CLASS_NAME = LogUtils.class.getName();

    //空数组
    private static final Object[] EMPTY = new Object[] {};

    //StackTraceElement数组的下标， 2为调用该工具类的类， 用来取到它的包名以及行号
    private static final Integer INDEX = 2;

    /*    //测试获取调用方包名以及行号代码
    public static void doLog(String name) {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        String className = Thread.currentThread().getStackTrace()[2].getClassName();
        int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
        System.err.println("className: " + className);
        System.err.println("lineNumber： " + lineNumber);
        logger.debug("name: {}", name);
    }*/

    /**
     * 获取调用方的logger
     *
     * @return
     */
    private static ch.qos.logback.classic.Logger getLogger() {
        //调用链浅到深排序的数组
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        //调用者信息
        StackTraceElement stackTraceElement = stackTraceElements[INDEX];
        return (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(stackTraceElement.getClassName());
    }

    /**
     * debug打印
     *
     * @param str 打印信息
     * @param args 参数（可多个，“，”隔开）
     */
    public static void debug(String str, Object... args) {
        getLogger().log(null, CLASS_NAME, LocationAwareLogger.DEBUG_INT, format(str, args), EMPTY, null);
    }

    /**
     * info打印
     *
     * @param str 打印信息
     * @param args 参数（可多个，“,”隔开）
     */
    public static void info(String str, Object... args) {
        getLogger().log(null, CLASS_NAME, LocationAwareLogger.INFO_INT, format(str, args), EMPTY, null);
    }

    /**
     * error打印
     *
     * @param str 打印信息
     * @param args 参数（可多个，“,”隔开）
     */
    public static void error(String str, Object... args) {
        getLogger().log(null, CLASS_NAME, LocationAwareLogger.ERROR_INT, format(str, args), EMPTY, null);
    }

    //字符转义
    private static String format(String str, Object... args) {
        return (args != null && args.length > 0) ? new MessageFormat(str).format(args) : str;
    }
};

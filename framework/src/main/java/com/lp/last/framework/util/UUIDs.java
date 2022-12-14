package com.lp.last.framework.util;

import java.util.UUID;

/**
 * <pre>
 * {@linkplain UUID} 相关的工具类
 * </pre>
 * @author cwentao@linewell.com
 * @date 2018/1/10
 */
public final class UUIDs {
    private UUIDs() {
    }

    /**
     * 获取 32 位不带连字符的 uuid
     */
    public static String get() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-", "");
    }
}

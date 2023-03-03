package org.example.algorithm;

/**
 * <pre>
 *     限流算法
 * </pre>
 */
public class Algorithm {

    public long last;

    public int count = 0;

    public final int limit = 1000; // 时间窗口内最大请求数

    public final long interval = 1000 * 60; // 时间窗口ms

    /**
     * 计数器算法
     *
     */
    public boolean countAlgorithm() {
        long current = System.currentTimeMillis();
        if(current - last > interval) {
            last = current;
            count = 0;
        }
        if(count > limit) {
            return false;
        }
        count++;
        return true;
    }
}

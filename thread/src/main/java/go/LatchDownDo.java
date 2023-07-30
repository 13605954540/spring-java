package go;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LatchDownDo {

    public static void main(String[] args) throws InterruptedException {
        int data = 1000000;
        //分页数
        int cs = 20;
        //每页多少数据
        int every = data/cs;
        CountDownLatch countDownLatch = new CountDownLatch(cs);
        // 创建线程池，同时处理多个数据
        ExecutorService executor = Executors.newFixedThreadPool(cs);
        long start = System.currentTimeMillis();
        for(int y = 0; y < cs; y++) {
            int s = y * every;
            int e = (y + 1) * every;
            executor.execute(() -> {
                for(int z = s; z < e; z++) {
                    System.err.println(z);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.err.println("所有执行结束了哦");
        System.err.println("执行时长： " + (end - start) + " ms");
    }

/*    public static void main(String[] args) throws InterruptedException {
        int data = 10000000;
        long start = System.currentTimeMillis();
        for(int i = 0; i < data; i++) {
            System.err.println(i);
        }
        long end = System.currentTimeMillis();
        System.err.println("所有执行结束了哦");
        System.err.println("执行时长： " + (end - start) + " ms");
    }*/
}

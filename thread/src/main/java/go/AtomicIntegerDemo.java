package go;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger原子类 乐观锁通过cas自旋
 *
 * <pre>
 *     优点： 不用加锁，避开线程上下文切换问题，性能好
 *     缺点： 如果高并发下 内存地址v与期望一直不等 将会重复尝试，对cpu性能有影响
 * </pre>
 */
public class AtomicIntegerDemo {

    private static AtomicInteger a = new AtomicInteger(2);

    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                // 如果 flag 变量为 true 就终止执行
                while (a.intValue() == 2) {
                }
                System.out.println("终止执行");
            }
        });
        t1.start();
        // 1s 之后将 flag 变量的值修改为 true
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("设置 flag 变量的值为 true！");
//                flag = true;
                a.incrementAndGet();
            }
        });
        t2.start();
    }
}

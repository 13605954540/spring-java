package go;

/**
 *  threadLocal 线程局部变量
 *
 *  <pre>
 *      优点： 高并发下，不需要加锁，性能好
 *      注意点： 必须remove 避免内存泄漏 建议static声明
 *  </pre>
 */
public class ThreadLocalDemo {

    private static boolean flag = false;

    private static ThreadLocal<Integer> in1 = new ThreadLocal<>();


    public static void main(String[] args) {

        in1.set(2);
        System.err.println(in1.get());
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                // 如果 flag 变量为 true 就终止执行
                in1.set(5);
            }
        });
        t1.start();
        // 1s 之后将 flag 变量的值修改为 true
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    System.err.println(in1.get());
                }
            }
        });
        t2.start();
    }
}

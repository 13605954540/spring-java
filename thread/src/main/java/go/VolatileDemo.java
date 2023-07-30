package go;

/**
 * 加volatile后，其他线程可以看到变量的改变
 *
 */
public class VolatileDemo {

    private static boolean flag = false;

    private static volatile int a = 2;

    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                // 如果 flag 变量为 true 就终止执行
                while (a == 2) {
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
                a = 3;
            }
        });
        t2.start();
    }
}

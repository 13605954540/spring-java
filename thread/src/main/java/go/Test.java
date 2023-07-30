package go;

import java.util.concurrent.atomic.AtomicInteger;

public class Test {

    private static AtomicInteger a = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        Runnable r = ()-> {
          for(int i = 0; i < 10000; i++) {
              a.incrementAndGet();
          }
        };
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.err.println(a);
    }
}

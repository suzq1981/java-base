package com.senior.concurrence.pool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class WilliamMain {

    public static void main(String[] args) throws Exception {
        WilliamThreadPool pool = new WilliamThreadPool(1, 3, 2, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(10));
        TimeUnit.SECONDS.sleep(3);
        System.out.println("main getActiveCount = " + pool.getActiveCount());
        pool.execute(new WilliamRunnable("Apple"));
        TimeUnit.MILLISECONDS.sleep(100);
        pool.execute(new WilliamRunnable("pear"));
        TimeUnit.MILLISECONDS.sleep(100);
        pool.execute(new WilliamRunnable("apricot"));
        TimeUnit.MILLISECONDS.sleep(100);
        pool.execute(new WilliamRunnable("peach"));
        TimeUnit.MILLISECONDS.sleep(100);
        pool.execute(new WilliamRunnable("grape"));
        TimeUnit.MILLISECONDS.sleep(100);
        pool.execute(new WilliamRunnable("banana"));
        TimeUnit.MILLISECONDS.sleep(100);
        pool.execute(new WilliamRunnable("pineapple"));
        TimeUnit.MILLISECONDS.sleep(100);
        pool.execute(new WilliamRunnable("plum"));
        TimeUnit.MILLISECONDS.sleep(100);
        pool.execute(new WilliamRunnable("watermelon"));
        TimeUnit.MILLISECONDS.sleep(100);
        pool.execute(new WilliamRunnable("orange"));
        TimeUnit.MILLISECONDS.sleep(100);
        pool.execute(new WilliamRunnable("lemon"));
        TimeUnit.MILLISECONDS.sleep(100);
        pool.execute(new WilliamRunnable("mango"));
        TimeUnit.MILLISECONDS.sleep(100);
        pool.execute(new WilliamRunnable("strawberry"));
        TimeUnit.MILLISECONDS.sleep(100);
        pool.execute(new WilliamRunnable("loquat"));

        pool.shutdown();
        pool.execute(new WilliamRunnable("ganze"));

    }

}

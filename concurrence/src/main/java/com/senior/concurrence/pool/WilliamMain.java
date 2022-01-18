package com.senior.concurrence.pool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class WilliamMain {

    public static void main(String[] args) {
        WilliamThreadPool pool = new WilliamThreadPool(5, 5, 1, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(100));

        pool.execute(new WilliamRunnable("Apple"));
        pool.execute(new WilliamRunnable("pear"));
        pool.execute(new WilliamRunnable("apricot", 0));
        pool.execute(new WilliamRunnable("peach"));
        pool.execute(new WilliamRunnable("grape", 0));
        pool.execute(new WilliamRunnable("banana"));
        pool.execute(new WilliamRunnable("pineapple", 0));
        pool.execute(new WilliamRunnable("plum"));
        pool.execute(new WilliamRunnable("watermelon"));
        pool.execute(new WilliamRunnable("orange", 0));
        pool.execute(new WilliamRunnable("lemon"));
        pool.execute(new WilliamRunnable("mango"));
        pool.execute(new WilliamRunnable("strawberry"));
        pool.execute(new WilliamRunnable("loquat"));

        pool.shutdown();
        pool.execute(new WilliamRunnable("ganze"));

    }

}

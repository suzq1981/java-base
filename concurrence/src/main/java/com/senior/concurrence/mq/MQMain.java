package com.senior.concurrence.mq;

import java.util.concurrent.*;

public class MQMain {

    public static void main(String[] args) throws Exception {

        BlockingDeque<Data> queue = new LinkedBlockingDeque<>();
        Provider p1 = new Provider("p1", queue);
        Provider p2 = new Provider("p2", queue);
        Provider p3 = new Provider("p3", queue);

        Consumer c1 = new Consumer("c1", queue);
        Consumer c2 = new Consumer("c2", queue);
        Consumer c3 = new Consumer("c3", queue);

        ExecutorService pool = Executors.newFixedThreadPool(6);
        pool.execute(p1);
        pool.execute(p2);
        pool.execute(p3);
        pool.execute(c1);
        pool.execute(c2);
        pool.execute(c3);

        TimeUnit.SECONDS.sleep(10);
        Provider.shutdown = true;
        pool.shutdown();
    }
}

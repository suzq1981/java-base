package com.senior.concurrence.mq;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Provider implements Runnable {

    public static volatile boolean shutdown = false;
    private String name;
    private BlockingDeque<Data> queue;

    public Provider(String name, BlockingDeque<Data> queue) {
        this.name = name;
        this.queue = queue;
    }

    private static AtomicInteger atId = new AtomicInteger(1);

    @Override
    public void run() {
        try {
            while (!shutdown) {
                TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(1000));
                int value = atId.getAndIncrement();
                Data data = new Data(value, String.valueOf(value));
                System.out.println(name + " produce: " + data);
                //queue.offer(data, 2, TimeUnit.SECONDS);
                queue.put(data);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

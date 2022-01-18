package com.senior.concurrence.mq;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {

    private String name;
    private BlockingDeque<Data> queue;

    public Consumer(String name, BlockingDeque<Data> queue) {
        this.name = name;
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Data data = queue.poll(5, TimeUnit.SECONDS);
                if (data == null) {
                    System.out.println("消费者5秒没有获取到数据，退出！");
                    break;
                }
                TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(1000));
                System.out.println(name + " consume: " + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

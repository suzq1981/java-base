package com.senior.concurrence.disruptor.ch01;

import com.lmax.disruptor.EventHandler;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class OrderEventHandlerPrice implements EventHandler<Order> {

    @Override
    public void onEvent(Order order, long sequence, boolean endOfBatch) throws Exception {
        order.setPrice(ThreadLocalRandom.current().nextDouble(100));
        TimeUnit.MILLISECONDS.sleep(30);
        System.out.println(Thread.currentThread().getName() + " Sequence=" + sequence + " OrderEventHandlerPrice " + order);
    }
}

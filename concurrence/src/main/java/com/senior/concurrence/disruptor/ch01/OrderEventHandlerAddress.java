package com.senior.concurrence.disruptor.ch01;

import com.lmax.disruptor.EventHandler;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class OrderEventHandlerAddress implements EventHandler<Order> {

    @Override
    public void onEvent(Order order, long sequence, boolean endOfBatch) throws Exception {
        order.setAddr(""+ThreadLocalRandom.current().nextInt(10000));
        TimeUnit.MILLISECONDS.sleep(30);
        System.out.println(Thread.currentThread().getName() + " Sequence=" + sequence + " OrderEventHandlerAddress " + order);
    }
}

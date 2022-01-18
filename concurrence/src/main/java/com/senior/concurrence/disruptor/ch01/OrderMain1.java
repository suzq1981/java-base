package com.senior.concurrence.disruptor.ch01;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class OrderMain1 {

    public static void main(String[] args) throws InterruptedException {
        OrderEventFactory eventFactory = new OrderEventFactory();
        int bufferSize = 128;
        OrderThreadFactory threadFactory = new OrderThreadFactory();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3,3,0L,TimeUnit.SECONDS,new LinkedBlockingDeque<>());
        Disruptor<Order> disruptor = new Disruptor<Order>(eventFactory, bufferSize, executor);
        disruptor.handleEventsWith(new OrderEventHandlerAddress());

        disruptor.start();

        RingBuffer<Order> ringBuffer = disruptor.getRingBuffer();

        OrderProducer producer = new OrderProducer(ringBuffer);
        for (int q = 1; true; q++) {
            producer.onData(q);
            TimeUnit.MILLISECONDS.sleep(10);
        }
    }
}

package com.senior.concurrence.disruptor.ch01;

import com.lmax.disruptor.RingBuffer;

import java.util.Date;

public class OrderProducer {

    private RingBuffer<Order> ringBuffer;

    public OrderProducer(RingBuffer<Order> ringBuffer){
        this.ringBuffer = ringBuffer;
    }

    public void onData(int orderId){
        long sequence = ringBuffer.next();
        try {
            // 根据下一个序号获取Event
            Order order = ringBuffer.get(sequence);
            // Do some work with the event.
            order.setOrderId(orderId);
            order.setOrderTime(new Date());
            System.out.println("Sequence=" + sequence + ", Main product -> " + order);
        } finally {
            // 发布序号(发布后可以被消费)
            ringBuffer.publish(sequence);
        }
    }
}

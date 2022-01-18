package com.senior.concurrence.disruptor.ch01;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.Date;

public class OrderMain0 {

    public static void main(String[] args) {
        //初始化EventFactory
        OrderEventFactory eventFactory = new OrderEventFactory();
        // 初始化RingBuffer的大小,必须是2的指数
        int bufferSize = 1024;
        // 初始化RingBuffer
        Disruptor<Order> disruptor = new Disruptor<Order>(eventFactory, bufferSize, new OrderThreadFactory());
        // 指定事件处理器
        disruptor.handleEventsWith(new OrderEventHandlerAddress());
        // 开启Disruptor,开启所有线程,(此方法只能调用一次,并且所有的EventHandler必须在start之前添加,包括ExceptionHandler)
        disruptor.start();
        // 获取RingBuffer
        RingBuffer<Order> ringBuffer = disruptor.getRingBuffer();
        // 以下代码为模板化代码,怎样发布事件到RingBuffer
        // 获取下一个序号
        long sequence = ringBuffer.next();
        try {
            // 根据下一个序号获取Event
            Order order = ringBuffer.get(sequence);
            // Do some work with the event.
            order.setOrderId(1000);
            order.setOrderTime(new Date());
            System.out.println("Sequence=" + sequence + ", Main product -> " + order);
        } finally {
            // 发布序号(发布后可以被消费)
            ringBuffer.publish(sequence);
        }

    }
}

package com.senior.concurrence.disruptor.ch01;

import com.lmax.disruptor.EventFactory;

public class OrderEventFactory implements EventFactory<Order> {

    @Override
    public Order newInstance() {
        return Order.builder().build();
    }

}

package com.senior.concurrence.disruptor.ch01;

import com.lmax.disruptor.ExceptionHandler;

public class DefaultExceptionHandler<Order> implements ExceptionHandler<Order> {

    @Override
    public void handleEventException(Throwable ex, long sequence, Order order) {
        System.out.println("Exception Message: " + ex.getMessage() + ", Order=" + order);
    }

    @Override
    public void handleOnStartException(Throwable ex) {

    }

    @Override
    public void handleOnShutdownException(Throwable ex) {

    }
}

package com.senior.concurrence.disruptor.ch02;

import com.lmax.disruptor.ExceptionHandler;

public class MessageExceptionHandler implements ExceptionHandler<Message> {

    @Override
    public void handleEventException(Throwable ex, long sequence, Message message) {
        System.out.println("Exception Message: " + ex.getMessage() + ", Message=" + message);
    }

    @Override
    public void handleOnStartException(Throwable ex) {

    }

    @Override
    public void handleOnShutdownException(Throwable ex) {

    }
}

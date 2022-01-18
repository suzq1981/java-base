package com.senior.concurrence.disruptor.ch02;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class MessageEventHandlerContent implements WorkHandler<Message> {

    @Override
    public void onEvent(Message message) throws Exception {
        handle(message);
    }

    public void handle(Message message) throws Exception{
        message.setContent(String.valueOf(ThreadLocalRandom.current().nextDouble(100, 1000)));
        TimeUnit.MILLISECONDS.sleep(30);
        System.out.println(System.currentTimeMillis() + " : " + Thread.currentThread().getName() + " MessageEventHandlerContent " + message);
    }
}

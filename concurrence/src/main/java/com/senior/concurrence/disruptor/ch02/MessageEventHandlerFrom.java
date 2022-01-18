package com.senior.concurrence.disruptor.ch02;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class MessageEventHandlerFrom implements WorkHandler<Message> {

    @Override
    public void onEvent(Message message) throws Exception {
        handle(message);
    }

    public void handle(Message message) throws Exception {
        message.setFrom(String.valueOf(ThreadLocalRandom.current().nextInt(100, 1000)));
        TimeUnit.MILLISECONDS.sleep(30);
        System.out.println(System.currentTimeMillis() + " : " + Thread.currentThread().getName() + " MessageEventHandlerFrom " + message);
    }
}

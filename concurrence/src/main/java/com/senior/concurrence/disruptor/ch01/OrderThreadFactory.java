package com.senior.concurrence.disruptor.ch01;

import java.util.concurrent.ThreadFactory;

public class OrderThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r);
    }
}

package com.senior.concurrence.pool;

import java.util.concurrent.ThreadFactory;

public class WilliamThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);

        return thread;
    }
}

package com.senior.concurrence.ch08;

import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setUncaughtExceptionHandler((t, e) -> {
            System.out.println("Thread Name=" + t.getName() + ", Exception Message=" + e.getMessage());
        });
        System.out.println(thread.getName() + " is create.");
        return thread;
    }
}

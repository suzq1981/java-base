package com.senior.concurrence.pool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class WilliamThreadPool extends ThreadPoolExecutor {

    public WilliamThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue workQueue) {

        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
                new WilliamThreadFactory(), new WilliamRejectedExecutionHandler());

        if (keepAliveTime <= 0) {
            throw new IllegalArgumentException("keepAliveTime not allow lower than 0");
        }

        this.allowCoreThreadTimeOut(true);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        WilliamRunnable thread = (WilliamRunnable) r;
        System.out.println("before make " + thread.getData());
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        WilliamRunnable thread = (WilliamRunnable) r;
        System.out.println("after make " + thread.getData());
    }

    @Override
    protected void terminated() {
        super.terminated();
    }
}

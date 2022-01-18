package com.senior.concurrence.pool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class WilliamRejectedExecutionHandler implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        WilliamRunnable runnable = (WilliamRunnable) r;
        System.out.println("Rejected: data=" + runnable.getData());
    }

}

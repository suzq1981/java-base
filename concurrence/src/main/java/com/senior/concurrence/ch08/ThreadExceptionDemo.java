package com.senior.concurrence.ch08;

import java.util.concurrent.*;

public class ThreadExceptionDemo {

    public static void main(String[] args) throws InterruptedException {

        //submit提交的任务用setUncaughtExceptionHandler无法捕获异常，execute则可以
        /*System.out.println("没有捕获异常就是一个巨大的坑~~");
        Runner1 runner1 = new Runner1();
        Runner2 runner2 = new Runner2();
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,new LinkedBlockingQueue<>());
        executorService.setThreadFactory(new ThreadFactory3());
        executorService.submit(runner1);
        executorService.submit(runner2);
        executorService.shutdown();
        System.out.println("main is over");*/


        System.out.println("优雅的处理没有捕获的异常~~");
        Runner1 runner1 = new Runner1();
        Runner2 runner2 = new Runner2();
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,new LinkedBlockingQueue<>());
        //设置ThreadFactory, 在其内部设置UncaughtExceptionHandler
        executorService.setThreadFactory(new ThreadFactory3());
        executorService.execute(runner1);
        executorService.execute(runner2);
        executorService.shutdown();
        System.out.println("main is over");
    }

    static class Runner1 implements Runnable {
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName()+" run");
                int i = 10/0;
            }catch(Exception e){
                System.out.println(Thread.currentThread().getName()+" exception record:"+e.getMessage());
            }
        }
    }

    static class Runner2 implements Runnable {
        public void run() {
            System.out.println(Thread.currentThread().getName()+" run");
            int i=10/0;
        }
    }

    static class ThreadFactory3 implements ThreadFactory{

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("William "+System.currentTimeMillis());
            thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(Thread t, Throwable e) {
                    System.out.println(t.getName()+" exception " + e.getMessage());
                }
            });
            return thread;
        }
    }

}
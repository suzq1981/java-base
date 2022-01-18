package com.senior.concurrence.ch06;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 通过跟踪ReentrantLock的运行， 理解AQS的原理和作用
 */
public class AQSDemo1 {

    public static void main(String[] args) throws InterruptedException {

        ReentrantLock rl = new ReentrantLock();
        System.out.println(Thread.currentThread().getName()+" start rl.lock");
        rl.lock();
        rl.lock();

        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+" start rl.lock");
                System.out.println("start re lock2");
                rl.lock();
                System.out.println("kkkk");
            }
        },"t1");

        Thread th2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+" start rl.lock");
                System.out.println("start re lock2");
                rl.lock();
                System.out.println("kkkk");
            }
        },"t2");

        th.start();
        TimeUnit.MILLISECONDS.sleep(100);
        th2.start();


        th.join();
        th2.join();
        rl.unlock();
        System.out.println(Thread.currentThread().getName()+" start rl.unlock");
        System.out.println("main is over");
    }

}

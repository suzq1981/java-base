package com.senior.concurrence.ch02;

import java.util.concurrent.ThreadLocalRandom;

public class ObjectLock {

    final Object lock = new Object();
    private int count = 0;

    public void addCount() {
        //synchronized 用在变量上是获取变量上的锁
        synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + " addCount is start.");
            count++;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " addCount is run over.");
        }
    }

    //synchronized 用在方法上是获得当前对象的锁
    public synchronized void dance() {
        System.out.println(Thread.currentThread().getName() + " dance is run over.");
    }

    public static void main(String[] args) {
        ObjectLock ol = new ObjectLock();

        Thread t1 = new Thread(() -> ol.addCount());
        Thread t2 = new Thread(()-> ol.dance());

        t1.start();
        t2.start();
    }

}

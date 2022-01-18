package com.senior.concurrence.ch02;

public class SleepDemo1 {

    public static void main(String[] args) {

        final Object lock = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " get Lock, sleeping");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " sleep over and run over");
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " get Lock, sleeping");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " sleep over and run over");
            }
        });

        t1.start();
        t2.start();

        //t1.interrupt();
    }
}

package com.senior.concurrence.ch02;

public class WaitDemo2 {

    public static void main(String[] args) {

        final Object lock = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " get lock, waiting...");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " release lock, run over");
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " get lock, t1.interrupt");
                t1.interrupt();
                System.out.println(Thread.currentThread().getName() + " release lock, run over");
            }
        });

        t1.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("final:" + t1.getName() + " and " + t2.getName() + " run over!");
        System.out.println("t1's state:" + t1.getState());
        System.out.println("t2's state:" + t2.getState());

    }
}

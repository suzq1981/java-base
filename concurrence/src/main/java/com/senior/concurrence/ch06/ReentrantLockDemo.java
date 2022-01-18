package com.senior.concurrence.ch06;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    private Lock lock = new ReentrantLock();

    public void run1() {
        try {
            lock.lock();
            System.out.println("当前线程:" + Thread.currentThread().getName() + "进入run1..");
            Thread.sleep(1000);
            System.out.println("当前线程:" + Thread.currentThread().getName() + "退出run1..");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void run2() {
        try {
            lock.lock();
            System.out.println("当前线程:" + Thread.currentThread().getName() + "进入run2..");
            Thread.sleep(2000);
            System.out.println("当前线程:" + Thread.currentThread().getName() + "退出run2..");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        final ReentrantLockDemo ur = new ReentrantLockDemo();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                ur.run1();
            }
        }, "t1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                ur.run2();
            }
        }, "t2");

        t1.start();
        t2.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

package com.senior.concurrence.ch06;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo1 {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void run1() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " 线程进入等待状态...");
            TimeUnit.SECONDS.sleep(3);
            System.out.println(Thread.currentThread().getName() + " 线程释放锁..await");
            condition.await();
            System.out.println(Thread.currentThread().getName() + " 线程继续执行.");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void run2() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " 线程进入...");
            TimeUnit.SECONDS.sleep(3);
            System.out.println(Thread.currentThread().getName() + " 线程发出唤醒");
            condition.signal();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final ConditionDemo1 lc = new ConditionDemo1();

        new Thread(() -> lc.run1()).start();

        new Thread(() -> lc.run2()).start();

    }
}

package com.senior.concurrence.ch06;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ABC10AwaitSignal extends Thread {

    private String printChar;
    private Lock lock;
    private Condition thisCondition;
    private Condition nextCondition;

    public ABC10AwaitSignal(String printChar, Lock lock, Condition thisCondition, Condition nextCondition) {
        this.printChar = printChar;
        this.lock = lock;
        this.thisCondition = thisCondition;
        this.nextCondition = nextCondition;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            for (int i = 0; i < 10; i++) {
                System.out.print(printChar);
                nextCondition.signal();
                if (i < 9)
                    thisCondition.await();
            }
        } catch (Exception ex) {

        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws Exception {
        Lock lock = new ReentrantLock();
        Condition aCondition = lock.newCondition();
        Condition bCondition = lock.newCondition();
        Condition cCondition = lock.newCondition();

        ABC10AwaitSignal t1 = new ABC10AwaitSignal("A", lock, aCondition, bCondition);
        ABC10AwaitSignal t2 = new ABC10AwaitSignal("B", lock, bCondition, cCondition);
        ABC10AwaitSignal t3 = new ABC10AwaitSignal("C", lock, cCondition, aCondition);

        t1.start();
        TimeUnit.MILLISECONDS.sleep(100);
        t2.start();
        TimeUnit.MILLISECONDS.sleep(100);
        t3.start();
    }
}

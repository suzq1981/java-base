package com.senior.concurrence.ch06;

import java.util.concurrent.TimeUnit;

public class ABC10WaitNotify extends Thread {

    private String printChar;
    private Object preLock;
    private Object thisLock;

    public ABC10WaitNotify(String printChar, Object preLock, Object thisLock) {
        this.printChar = printChar;
        this.preLock = preLock;
        this.thisLock = thisLock;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            synchronized (preLock) {
                synchronized (thisLock) {
                    System.out.print(printChar);
                    thisLock.notify();
                }
                preLock.notify();
                try {
                    if (i < 9)
                        preLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        final Object aLock = new Object();
        final Object bLock = new Object();
        final Object cLock = new Object();

        ABC10WaitNotify t1 = new ABC10WaitNotify("A", cLock, aLock);
        ABC10WaitNotify t2 = new ABC10WaitNotify("B", aLock, bLock);
        ABC10WaitNotify t3 = new ABC10WaitNotify("C", bLock, cLock);

        t1.start();
        TimeUnit.MILLISECONDS.sleep(100);
        t2.start();
        TimeUnit.MILLISECONDS.sleep(100);
        t3.start();
    }
}

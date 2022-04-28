package com.senior.concurrence.ch06;

import java.util.concurrent.TimeUnit;

public class ABC10WaitNotify2 extends Thread {

    private static int index = 0;
    private String printChar;
    private Object preLock;
    private Object curlock;

    public ABC10WaitNotify2(String printChar, Object preLock, Object curlock) {
        this.printChar = printChar;
        this.preLock = preLock;
        this.curlock = curlock;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            synchronized (preLock) {
                synchronized (curlock) {
                    System.out.print(printChar);
                    if(printChar.endsWith("C")){
                    	System.out.print(" ");
                    }
                    curlock.notify();
                }
                if (i < 19) {
                    try {
                        preLock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        final Object aLock = new Object();
        final Object bLock = new Object();
        final Object cLock = new Object();

        ABC10WaitNotify2 t1 = new ABC10WaitNotify2("A", cLock, aLock);
        ABC10WaitNotify2 t2 = new ABC10WaitNotify2("B", aLock, bLock);
        ABC10WaitNotify2 t3 = new ABC10WaitNotify2("C", bLock, cLock);

        t1.start();
        TimeUnit.MILLISECONDS.sleep(100);
        t2.start();
        TimeUnit.MILLISECONDS.sleep(100);
        t3.start();
    }
}

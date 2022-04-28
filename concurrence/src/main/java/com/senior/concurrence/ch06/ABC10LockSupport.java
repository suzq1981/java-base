package com.senior.concurrence.ch06;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class ABC10LockSupport extends Thread {

    private Thread nextThread;
    private String printChar;

    public ABC10LockSupport(String printChar) {
        this.printChar = printChar;
    }

    public void setNextThread(Thread nextThread){
        this.nextThread = nextThread;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.print(printChar);
            LockSupport.unpark(nextThread);
            if (i < 9)
                LockSupport.park(this);
        }
    }

    public static void main(String[] args) throws Exception {

        ABC10LockSupport t1 = new ABC10LockSupport("A");
        ABC10LockSupport t2 = new ABC10LockSupport("B");
        ABC10LockSupport t3 = new ABC10LockSupport("C");

        t1.setNextThread(t2);
        t2.setNextThread(t3);
        t3.setNextThread(t1);

        t1.start();
        TimeUnit.MILLISECONDS.sleep(100);
        t2.start();
        TimeUnit.MILLISECONDS.sleep(100);
        t3.start();
    }
}

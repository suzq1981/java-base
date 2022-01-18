package com.senior.concurrence.ch02;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class YieldDemo2 extends Thread {

    public static Object lock = new Object();

    @Override
    public void run() {
        synchronized (lock) {
            System.out.println(this.getName() + " yield.");
            //Thread.yield();

            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(this.getName() + " run over.");
        }
    }

    public static void main(String[] args) throws Exception {
        List<Thread> threadList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Thread t = new YieldDemo2();
            threadList.add(t);
            t.start();
        }
        Thread.sleep(500);
        for (Thread thread : threadList) {
            System.out.println(thread.getName() + " , state=" + thread.getState());
        }
        synchronized (lock) {
            lock.notifyAll();
        }

    }
}

package com.senior.concurrence.ch03;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalDemo2 extends Thread {

    private static InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    @Override
    public void run() {
        System.out.println(this.getName() + " get threadLocal is: " + threadLocal.get());
        new ThreadLocalDemo2().start();
    }

    public static void main(String[] args) throws InterruptedException {
        threadLocal.set("1001");
        new ThreadLocalDemo2().start();

        threadLocal.set("2002");
        new ThreadLocalDemo2().start();
    }
}

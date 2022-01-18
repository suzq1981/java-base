package com.senior.concurrence.ch02;

public class DaemonAndUserThreadDemo {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            int i = 0;
            while (true) {
                System.out.println(Thread.currentThread().getName() + " i=" + (++i));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);

        thread.start();
        System.out.println(thread.getName() + " is daemon? " + thread.isDaemon());
        System.out.println(Thread.currentThread().getName() + " is daemon? " + Thread.currentThread().isDaemon());

        System.out.println("main is over.");
    }
}

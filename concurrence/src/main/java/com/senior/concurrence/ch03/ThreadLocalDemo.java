package com.senior.concurrence.ch03;

public class ThreadLocalDemo {

    static ThreadLocal<Integer> th = new ThreadLocal<Integer>();

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            try {
                th.set(100);
                System.out.println("t1 set th=" + th.get());
                Thread.sleep(2000);
                System.out.println("t1 get th=" + th.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(1000);

        new Thread(() -> {
            Integer ele = th.get();
            System.out.println("t2 get th=" + ele);
            th.set(200);
            //th = 200;
            System.out.println("t2 get th=" + th.get());
        }).start();

    }
}

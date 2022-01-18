package com.senior.concurrence.ch02;

public class JoinDemo1 {

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " run over!");
        });

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " run over!");
        });

        t1.start();
        t2.start();

        System.out.println(Thread.currentThread().getName() + " wait "
                + t1.getName() + " and " + t2.getName() + " run over");

        //join 在主线程main中等待给定的线程执行完毕后再继续后续代码
       /* try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        try {
            t1.join(1000);
            t2.join(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("final:" + t1.getName() + " and " + t2.getName() + " run over");

        //查看线程状态
        System.out.println("t1's state:" + t1.getState());
        System.out.println("t2's state:" + t2.getState());
    }
}

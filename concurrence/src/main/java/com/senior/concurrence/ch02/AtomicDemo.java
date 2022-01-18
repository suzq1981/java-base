package com.senior.concurrence.ch02;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {

    static int count = 0;

    public static void main(String[] args) {
        //因为count++非原子操作，可以用 synchronized或 AtomicInteger ai = new AtomicInteger ( 0 );

        ArrayList<Thread> arrayList = new ArrayList();

        for (int i = 0; i < 100; i++) {

            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    //ai.incrementAndGet();
                    count++;
                }
            });

            thread.start();
            arrayList.add(thread);
        }

        //用与判断所有线程是否结束
        while (!arrayList.isEmpty()) {
            for (int i = 0; i < arrayList.size(); i++) {
                if (!arrayList.get(i).isAlive()) {
                    arrayList.remove(i--);
                }
            }
        }
        System.out.println(count);
    }

}

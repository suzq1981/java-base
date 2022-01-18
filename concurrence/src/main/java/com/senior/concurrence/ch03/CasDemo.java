package com.senior.concurrence.ch03;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

public class CasDemo {

    private static AtomicStampedReference<Integer> atomic = new AtomicStampedReference<>(100, 0);

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                int oldVlue = atomic.getReference().intValue();
                int oldStamp = atomic.getStamp();
                boolean success = atomic.compareAndSet(oldVlue, 101, oldStamp, oldStamp + 1);
                System.out.println(Thread.currentThread().getName() + " set " + oldVlue + " > 101 , " + success);
                oldStamp = atomic.getStamp();
                success = atomic.compareAndSet(101, 100, oldStamp, oldStamp + 1);
                System.out.println(Thread.currentThread().getName() + " set 101 > 100 , " + success);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();

        Thread t2 = new Thread(() -> {
            try {
                int oldStamp = atomic.getStamp();
                System.out.println(Thread.currentThread().getName() + " 修改之前的stamp: " + oldStamp);
                TimeUnit.SECONDS.sleep(2);

                int nowStamp = atomic.getStamp();
                System.out.println(Thread.currentThread().getName() + " 等待两秒之后,版本被t1线程修改为 : " + nowStamp);
                boolean success = atomic.compareAndSet(100, 101, oldStamp, oldStamp + 1);
                System.out.println(Thread.currentThread().getName() + " set 100>101 使用错误的时间戳: " + success);
                success = atomic.compareAndSet(101, 100, oldStamp, oldStamp + 1);
                System.out.println(Thread.currentThread().getName() + " set 101>100 使用错误的时间戳: " + success);

                //以下修改是成功的,因为使用了正确的版本号,正确的期待值
                oldStamp = atomic.getStamp();
                success = atomic.compareAndSet(100, 101, oldStamp, oldStamp + 1);
                System.out.println(Thread.currentThread().getName() + " set 100>101 使用正确的时间戳: " + success);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main is over");
    }
}

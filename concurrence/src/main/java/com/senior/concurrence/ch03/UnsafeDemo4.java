package com.senior.concurrence.ch03;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class UnsafeDemo4 {

    public static void main(String[] args) throws Exception {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);

        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start....");

            unsafe.park(false, 0);

            if(Thread.currentThread().isInterrupted()){
                System.out.println(Thread.currentThread().getName()+" is interrupted");
            }

            System.out.println(Thread.currentThread().getName() + " end.");

        });

        thread.start();
        System.out.println(Thread.currentThread().getName() + " run over.");

        //unsafe.unpark(thread);
        //thread.interrupt();
    }

}

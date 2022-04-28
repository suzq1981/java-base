package com.senior.concurrence.ch03;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeDemo {

    private int salary;

    public int getSalary() {
        return salary;
    }

    public static void main(String[] args) throws Exception {
        UnsafeDemo demo = new UnsafeDemo();

        Field field = Unsafe.class.getDeclaredField("theUnsafe");

        field.setAccessible(true);

        Unsafe unsafe = (Unsafe) field.get(null);
        long offset = unsafe.objectFieldOffset(UnsafeDemo.class.getDeclaredField("salary"));
        System.out.println(offset);

        unsafe.putInt(demo, offset, 15000);
        System.out.println("William's salary is " + demo.getSalary());

        unsafe.getAndAddInt(demo,offset,7000);
        System.out.println("Now William's salary is "+ demo.getSalary());

    }
}

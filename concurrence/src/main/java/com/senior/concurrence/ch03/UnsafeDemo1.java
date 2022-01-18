package com.senior.concurrence.ch03;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeDemo1 {
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args) throws Exception {
        UnsafeDemo1 demo = new UnsafeDemo1();

        Field field = Unsafe.class.getDeclaredField("theUnsafe");

        field.setAccessible(true);

        Unsafe unsafe = (Unsafe) field.get(null);

        long ageOffset = unsafe.objectFieldOffset(UnsafeDemo1.class.getDeclaredField("age"));

        unsafe.putInt(demo, ageOffset, 10);
        System.out.println("William-1-putInt>>" + demo.getAge());

        int age = unsafe.getInt(demo, ageOffset);
        System.out.println("William-1-getInt>>" + age);

        boolean result = unsafe.compareAndSwapInt(demo, ageOffset, 11, 50);
        System.out.println("William-2>>age=" + demo.getAge() + " ,result=" + result);

        result = unsafe.compareAndSwapInt(demo, ageOffset, 10, 100);
        System.out.println("William-3>>age=" + demo.getAge() + " ,result=" + result);

        age = unsafe.getAndSetInt(demo, ageOffset, 99);
        System.out.println("William-4-getAndSetInt>>" + age + "," + demo.getAge());

        age = unsafe.getAndAddInt(demo, ageOffset, 100);
        System.out.println("Willaim-5-getAndAddInt>>" + age + "," + demo.getAge());


    }
}

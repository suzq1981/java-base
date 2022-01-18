package com.senior.concurrence.ch03;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Arrays;

public class UnsafeDemo2 {

    private int[] orders = {1, 2, 3, 4};

    public int[] getOrders() {
        return orders;
    }

    public static void main(String[] args) throws Exception {
        UnsafeDemo2 demo = new UnsafeDemo2();

        Field field = Unsafe.class.getDeclaredField("theUnsafe");

        field.setAccessible(true);

        Unsafe unsafe = (Unsafe) field.get(null);

        int baseOffset = unsafe.arrayBaseOffset(long[].class);
        int scale = unsafe.arrayIndexScale(long[].class);

        long offset = unsafe.objectFieldOffset(UnsafeDemo2.class.getDeclaredField("orders"));
        System.out.println("baseOffset=" + baseOffset + ", scale=" + scale + ", offset=" + offset);

        int[] local = {5, 6, 7, 8, 9};
        unsafe.putObject(demo, offset, local);
        System.out.println("new array: " + Arrays.toString(demo.getOrders()));

        boolean result = unsafe.compareAndSwapObject(demo, offset, local, new int[]{8, 9, 10, 11});
        System.out.println("result=" + result + ", " + Arrays.toString(demo.getOrders()));

        result = unsafe.compareAndSwapObject(demo, offset, new int[]{8, 9, 10, 11}, new int[]{7, 6, 5, 4});
        System.out.println("result=" + result + ", " + Arrays.toString(demo.getOrders()));

        int[] orders = (int[]) unsafe.getObject(demo, offset);
        System.out.println(Arrays.toString(orders));

    }
}

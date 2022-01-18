package com.senior.concurrence.ch03;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Arrays;

public class UnsafeDemo3 {

    private int[] times = {1, 2, 3, 4};

    public int[] getTimes() {
        return times;
    }

    public static void main(String[] args) throws Exception {
        UnsafeDemo3 demo = new UnsafeDemo3();

        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);

        int baseOffset = unsafe.arrayBaseOffset(int[].class);
        int scale = unsafe.arrayIndexScale(int[].class);

        unsafe.putInt(demo.getTimes(), (long) baseOffset + scale * 0, 9);
        System.out.println(Arrays.toString(demo.getTimes()));
    }
}

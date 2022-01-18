package com.senior.jdk8.stream;

import java.util.stream.LongStream;

public class Stream003 {
    public static void main(String[] args) {
        serial();
        parallel();
    }

    public static void serial() {
        long start = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(0L, 2000000000L).sum();
        long end = System.currentTimeMillis();
        System.out.println("串行流所耗时间：" + (end - start));
    }

    public static void parallel() {
        long start = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(0L, 2000000000L).parallel().sum();
        long end = System.currentTimeMillis();
        System.out.println("并行流所耗时间：" + (end - start));
    }
}

package com.senior.concurrence.ch07;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAccumulator;

public class LongAccumulatorDemo extends Thread {

    private LongAccumulator a;

    public LongAccumulatorDemo(LongAccumulator a) {
        this.a = a;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            a.accumulate(i);
        }
    }

    public static void main(String[] args) throws Exception {
        LongAccumulator accumulator = new LongAccumulator((x, y) -> x + y, 0L);

        ExecutorService executorService = Executors.newFixedThreadPool(4 * 10);
        for (int i = 0; i < 100; i++) {
            executorService.submit(new LongAccumulatorDemo(accumulator));
        }

        TimeUnit.SECONDS.sleep(1);
        System.out.println(accumulator.longValue());

        executorService.shutdown();
    }
}

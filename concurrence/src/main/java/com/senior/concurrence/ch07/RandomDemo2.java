package com.senior.concurrence.ch07;

import java.util.Random;
import java.util.concurrent.*;

/**
 * ThreadLocalRandom 使用，性能高
 */
public class RandomDemo2 {

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        CountDownLatch cd = new CountDownLatch(100);
        CyclicBarrier barrier = new CyclicBarrier(100);
        ExecutorService executor = Executors.newFixedThreadPool(100);
        for(int i=0;i<100;i++){
            executor.submit(new RandomDemo2Runner(barrier,"thread"+i,cd));
        }
        cd.await();
        long use = System.currentTimeMillis()-start;
        System.out.println("main is over.."+use);
        executor.shutdown();
    }
}

class RandomDemo2Runner implements Runnable {

    private CyclicBarrier barrier;

    private String name;

    private CountDownLatch cd;

    public RandomDemo2Runner(CyclicBarrier barrier, String name, CountDownLatch cd) {
        super();
        this.barrier = barrier;
        this.name = name;
        this.cd = cd;
    }

    @Override
    public void run() {
        try {
            System.out.println(name + " 准备好了...");
            barrier.await();
            for (int j = 0; j < 10000; j++) {
                ThreadLocalRandom.current().nextInt(50);
                //System.out.println(Thread.currentThread().getName() + ">>" + ThreadLocalRandom.current().nextInt(50));
            }
            cd.countDown();
        } catch (InterruptedException e) {
            System.out.println(name + " 中断异常！");
        } catch (BrokenBarrierException e) {
            System.out.println(name + " Barrier损坏异常！");
        }
    }
}

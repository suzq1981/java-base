package com.senior.concurrence.ch05;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierDemo1 {

    public static void main(String[] args) throws IOException, InterruptedException {
        //如果将参数改为4，但是下面只加入了3个选手，这永远等待下去
        //Waits until all parties have invoked await on this barrier.
        CyclicBarrier barrier = new CyclicBarrier(20);

        ExecutorService executor = Executors.newFixedThreadPool(20);
        executor.submit(new Thread(new Runner(barrier, "1号选手")));
        executor.submit(new Thread(new Runner(barrier, "2号选手")));
        executor.submit(new Thread(new Runner(barrier, "3号选手")));
        executor.submit(new Thread(new Runner(barrier, "4号选手")));
        executor.submit(new Thread(new Runner(barrier, "5号选手")));
        executor.submit(new Thread(new Runner(barrier, "6号选手")));
        executor.submit(new Thread(new Runner(barrier, "7号选手")));
        executor.submit(new Thread(new Runner(barrier, "8号选手")));
        executor.submit(new Thread(new Runner(barrier, "9号选手")));
        executor.submit(new Thread(new Runner(barrier, "10号选手")));
        executor.submit(new Thread(new Runner(barrier, "11号选手")));
        executor.submit(new Thread(new Runner(barrier, "12号选手")));
        executor.submit(new Thread(new Runner(barrier, "13号选手")));
        executor.submit(new Thread(new Runner(barrier, "14号选手")));
        executor.submit(new Thread(new Runner(barrier, "15号选手")));
        executor.submit(new Thread(new Runner(barrier, "16号选手")));
        executor.submit(new Thread(new Runner(barrier, "17号选手")));
        executor.submit(new Thread(new Runner(barrier, "18号选手")));
        executor.submit(new Thread(new Runner(barrier, "19号选手")));
        executor.submit(new Thread(new Runner(barrier, "20号选手")));

        executor.shutdown();
    }
}

class Runner implements Runnable {
    // 一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点 (common barrier point)
    private CyclicBarrier barrier;

    private String name;

    public Runner(CyclicBarrier barrier, String name) {
        super();
        this.barrier = barrier;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000 * (new Random()).nextInt(8));
            System.out.println(name + " 准备好了...");
            // barrier的await方法，在所有参与者都已经在此 barrier 上调用 await 方法之前，将一直等待。
            barrier.await();
            //设置等待时间,如果等待了1秒,最后一个线程还没有就位,则自己继续运行,但是会导致Barrier被标记为一个已经破坏的Barrier
            //barrier.await(1,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println(name + " 中断异常！");
        } catch (BrokenBarrierException e) {
            System.out.println(name + " Barrier损坏异常！");
        }
        System.out.println(name + " 起跑！");
    }
} 

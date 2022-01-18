package com.senior.concurrence.ch05;

import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo2 {

	public static void main(String[] args) throws IOException, InterruptedException, BrokenBarrierException {
		
		CyclicBarrier barrier = new CyclicBarrier(5);

		new Thread(new Worker(barrier, "worker1")).start();
		new Thread(new Worker(barrier, "worker2")).start();
		new Thread(new Worker(barrier, "worker3")).start();
		new Thread(new Worker(barrier, "worker4")).start();

		System.out.println("................");
		barrier.await();
		System.out.println("所有的线程都工作完毕了, main线程继续执行!");
	}
}

class Worker implements Runnable {
	// 一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点 (common barrier point)
	private CyclicBarrier barrier;
	private String name;

	public Worker(CyclicBarrier barrier, String name) {
		this.barrier = barrier;
		this.name = name;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(2000);
			System.out.println(name+"运行完毕!");
			barrier.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
} 

package com.senior.concurrence.ch08;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolDemo {

	public static void test1() {
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);

		for (int i = 0; i < 10; i++) {
			final int index = i;
			Runnable task = new Runnable() {
				public void run() {
					System.out.println(Thread.currentThread().getName() + ">> delay " + index + " seconds run....");
				}
			};

			ScheduledFuture<?> schedule = scheduledThreadPool.schedule(task, i, TimeUnit.SECONDS);
		}

		scheduledThreadPool.shutdown();
	}

	public static void test2() throws Exception {
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(12);
		Runnable task = new Runnable() {
			public void run() {
				try {
					System.out.println(Thread.currentThread().getName() + ">> 0 sleep..." + System.currentTimeMillis());
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + ">> 0 run....." + System.currentTimeMillis());
			}
		};
//		Runnable task1 = new Runnable() {
//			public void run() {
//				try {
//					System.out.println(Thread.currentThread().getName() + ">> 1 sleep..." + System.currentTimeMillis());
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				System.out.println(Thread.currentThread().getName() + ">> 1 run....." + System.currentTimeMillis());
//			}
//		};
		//scheduledThreadPool.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);
		ScheduledFuture<?> scheduleAtFixedRate = scheduledThreadPool.scheduleAtFixedRate(task, 0, 2, TimeUnit.SECONDS);
		TimeUnit.SECONDS.sleep(1);
		scheduleAtFixedRate.cancel(true);
	}

	public static void test3() {
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(10);
		Runnable task = new Runnable() {
			public void run() {
				try {
					System.out.println(Thread.currentThread().getName() + ">> sleep..." + System.currentTimeMillis());
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + ">> run....." + System.currentTimeMillis());
			}
		};
		ScheduledFuture<?> scheduleWithFixedDelay = scheduledThreadPool.scheduleWithFixedDelay(task, 0, 2, TimeUnit.SECONDS);

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scheduleWithFixedDelay.cancel(true);
		scheduledThreadPool.shutdown();
	}

	public static void main(String[] args) throws Exception {
		// test1();
		 test2();
		// test3();

	}
}

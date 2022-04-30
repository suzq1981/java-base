package com.senior.concurrence.rl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.RateLimiter;

public class RateLimiterDemo1 {

	static class Runner implements Runnable {
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + " is run over. " + System.currentTimeMillis());
		}
	}

	public static void main(String[] args) throws Exception {
		RateLimiter rateLimiter = RateLimiter.create(2);
		
		TimeUnit.SECONDS.sleep(100);
		ExecutorService pool = Executors.newCachedThreadPool();

		for (int i = 1; i <= 10; i++) {
			rateLimiter.acquire();
			pool.submit(new Runner());
		}
	}

}

package com.senior.concurrence.pool;

import java.lang.reflect.Method;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class WilliamThreadPool extends ThreadPoolExecutor {

	public WilliamThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue workQueue) {

		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, new WilliamThreadFactory(),
				new WilliamRejectedExecutionHandler());

		if (keepAliveTime <= 0) {
			throw new IllegalArgumentException("keepAliveTime not allow lower than 0");
		}

		this.allowCoreThreadTimeOut(true);
	}

	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		WilliamRunnable thread = (WilliamRunnable) r;
		System.out.println("before make " + thread.getData());
		double per = this.getQueue().remainingCapacity()
				/ ((float) this.getQueue().remainingCapacity() + this.getQueue().size());

		double threadPer = (float) this.getActiveCount() / this.getMaximumPoolSize();
		if (per < 0.9 && threadPer < 0.6) {
			System.out.println("XXXXXXXXXXXXXXXXXXXX");
			try {
				Method method = ThreadPoolExecutor.class.getDeclaredMethod("addWorker", new Class[] { Runnable.class,
						boolean.class });
				method.setAccessible(true);
				method.invoke(this, null, false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("per = " + per + ", threadPer = " + threadPer + " ,getActiveCount " + this.getActiveCount());
	}

	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		WilliamRunnable thread = (WilliamRunnable) r;
		//System.out.println("after make " + thread.getData());
	}

	@Override
	protected void terminated() {
		super.terminated();
	}
}

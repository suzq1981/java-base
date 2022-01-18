package com.djt.mapper.test.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPoolExecutor extends ThreadPoolExecutor {
	
	public ConcurrentHashMap<MyThread, Thread> map = new ConcurrentHashMap<MyThread, Thread>();
	
	public List<MyObject> list = Collections.synchronizedList(new ArrayList<>());
	

	public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		MyThread myt = (MyThread) r;
		MyObject obj = new MyObject(myt, t);
		map.put(myt, t);
		list.add(obj);
		
		super.beforeExecute(t, r);
	}

}

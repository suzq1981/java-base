package com.senior.concurrence.ch02;

import java.util.ArrayList;
import java.util.List;

public class MQueue {

	private List<String> list = new ArrayList<String>();

	private int maxSize;

	private Object lock = new Object();

	public MQueue(int maxSize) {
		this.maxSize = maxSize;
		System.out.println("线程" + Thread.currentThread().getName() + "已初始化长度为" + this.maxSize + "的队列");
	}

	public void put(String element) {
		synchronized (lock) {
			if (this.list.size() == this.maxSize) {
				try {
					System.out.println("线程" + Thread.currentThread().getName() + "当前队列已满put等待...");
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			this.list.add(element);
			System.out.println("线程" + Thread.currentThread().getName() + "向队列中加入元素:" + element);
			lock.notifyAll(); // 通知可以取数据
		}
	}

	public String take() {
		synchronized (lock) {
			if (this.list.size() == 0) {
				try {
					System.out.println("线程" + Thread.currentThread().getName() + "队列为空take等待...");
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			String result = list.get(0);
			list.remove(0);
			System.out.println("线程" + Thread.currentThread().getName() + "获取数据:" + result);
			lock.notifyAll(); // 通知可以加入数据
			return result;
		}
	}

	public static void main(String[] args) {
		final MQueue q = new MQueue(5);

		new Thread(new Runnable() {
			@Override
			public void run() {
				q.put("1");
				q.put("2");
				q.put("3");
				q.put("4");
				q.put("5");
				q.put("6");
			}
		}, "t1").start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				q.put("11");
				q.put("21");
				q.put("31");
				q.put("41");
				q.put("51");
				q.put("61");
			}
		}, "t2").start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				q.take();
				q.take();
				q.take();
				q.take();
				q.take();
			}
		}, "t3").start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				q.take();
				q.take();
				q.take();
				q.take();
				q.take();
			}
		}, "t4").start();
	}

}

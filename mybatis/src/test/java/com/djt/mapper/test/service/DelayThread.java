package com.djt.mapper.test.service;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import lombok.ToString;

import com.djt.mapper.service.IDepartmentService;

@ToString
public class DelayThread extends Thread implements Delayed {

	private long startTime;
	private IDepartmentService deptService;

	public DelayThread(String name, IDepartmentService deptService) {
		super(name);
		this.deptService = deptService;
		this.startTime = System.currentTimeMillis();
	}

	public long getStartTime() {
		return startTime;
	}

	@Override
	public int compareTo(Delayed o) {
		DelayThread dt = (DelayThread) o;
		if (this.startTime > dt.getStartTime()) {
			return 1;
		} else if (this.startTime < dt.getStartTime()) {
			return -1;
		}
		return 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		return (this.startTime + 2500) - System.currentTimeMillis();
	}

	@Override
	public void run() {
		try {
			deptService.testTransaction();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

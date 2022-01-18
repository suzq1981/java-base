package com.djt.mapper.test.service;

import com.djt.mapper.service.IDepartmentService;

public class MyThread extends Thread {

	private long startTime;
	private IDepartmentService deptService;

	public long getStartTime() {
		return this.startTime;
	}
	
	public void setStartTime(long time){
		this.startTime = time;
	}
	
	public MyThread(String name,IDepartmentService deptService){
		super(name);
		this.deptService = deptService;
	}

	@Override
	public void run() {
		this.startTime = System.currentTimeMillis();
		try {
			deptService.testTransaction();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}

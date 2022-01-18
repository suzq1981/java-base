package com.senior.concurrence.mw;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

public class Worker implements Runnable {

    private String name;
    private ConcurrentLinkedQueue<Task> tasks;
    private ConcurrentHashMap<String, Integer> resultMap;


    public Worker(String name, ConcurrentLinkedQueue tasks, ConcurrentHashMap<String, Integer> resultMap) {
        this.name = name;
        this.tasks = tasks;
        this.resultMap = resultMap;
    }

    @Override
    public void run() {
        while (true) {
            Task task = tasks.poll();
            if (task == null) {
                break;
            }
            try {
                TimeUnit.MILLISECONDS.sleep(500);
                System.out.println(Thread.currentThread().getName() + " 处理 " + task);
                resultMap.put(task.getId() + "", task.getPrice() + 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

package com.senior.concurrence.mw;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class Master {

    private ConcurrentLinkedQueue<Task> tasks;
    private List<Thread> workers;
    private ConcurrentHashMap<String, Integer> resultMap;

    public Master(int workCount) {
        this.tasks = new ConcurrentLinkedQueue<>();
        this.workers = new ArrayList<>();
        this.resultMap = new ConcurrentHashMap<>();
        for (int i = 1; i <= workCount; i++) {
            workers.add(new Thread(new Worker("worker" + i, this.tasks, this.resultMap)));
        }
    }

    public void submitTask(Task task) {
        tasks.offer(task);
    }

    public void execute() {
        for (Thread worker : workers) {
            worker.start();
        }
    }

    public boolean isComplete() {
        for (Thread worker : workers) {
            if (worker.getState() != Thread.State.TERMINATED) {
                return false;
            }
        }
        return true;
    }

    public int getResult() {
        return resultMap.values().stream().mapToInt(Integer::intValue).sum();
    }

}

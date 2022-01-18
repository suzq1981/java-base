package com.senior.concurrence.mw;

import java.util.concurrent.ThreadLocalRandom;

public class MWMain {

    public static void main(String[] args) {
        Master master = new Master(10);

        for (int i = 1; i <= 5; i++) {
            Task task = new Task(i, ThreadLocalRandom.current().nextInt(100));
            master.submitTask(task);
        }

        master.execute();
        long start = System.currentTimeMillis();

        while (!master.isComplete()) {

        }
        long end = System.currentTimeMillis();
        System.out.println("花费时间：" + (end - start) + ", result: " + master.getResult());
    }
}

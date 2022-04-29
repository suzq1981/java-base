package com.senior.concurrence.fj;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class WilliamForkJoinTask extends RecursiveTask<Long> {

	private static final long serialVersionUID = -3173696393332612405L;
	
	private Long start;
    private Long end;
    private Long factor;

    public WilliamForkJoinTask(Long start, Long end, Long factor) {
        this.start = start;
        this.end = end;
        this.factor = factor;
    }

    @Override
    protected Long compute() {
        if (end - start >= factor) {
        	WilliamForkJoinTask subTask1 = new WilliamForkJoinTask(start, (start + end) / 2, factor);
            subTask1.fork();
            WilliamForkJoinTask subTask2 = new WilliamForkJoinTask((start + end) / 2 + 1, end, factor);
            subTask2.fork();
            return subTask1.join() + subTask2.join();
        } else {
        	Long total = 0L;
            for (Long i = start; i <= end; i++) {
                total += i;
            }
            return total;
        }
    }

    public static void main(String[] args) throws Exception {

        long end = 1000;
        ForkJoinPool forkjoinPool = new ForkJoinPool();

        ForkJoinTask<Long> futureTask = forkjoinPool.submit(new WilliamForkJoinTask(1L, end, 100L));

        System.out.println(futureTask.get());

        long total = 0;
        for (long i = 1L; i <= end; i++) {
            total += i;
        }
        System.out.println(total);
    }
}

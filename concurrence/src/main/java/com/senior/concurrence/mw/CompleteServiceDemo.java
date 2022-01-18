package com.senior.concurrence.mw;

import java.util.concurrent.*;

public class CompleteServiceDemo {

    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 3, 0,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        //pool.allowCoreThreadTimeOut(true);

        ExecutorCompletionService service = new ExecutorCompletionService(pool);

        Thread taskThread = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                SalaryCalculator task = new SalaryCalculator(100, ThreadLocalRandom.current().nextInt(100));
                service.submit(task);
            }
        });
        taskThread.start();

        Thread resultThread = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    Future<Integer> future = service.take();
                    System.out.println("salary=" + future.get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        resultThread.start();

        taskThread.join();
        resultThread.join();

        pool.shutdown();

    }
}

class SalaryCalculator implements Callable<Integer> {

    private int baseSalary;
    private int costTime;

    public SalaryCalculator(int baseSalary, int costTime) {
        this.baseSalary = baseSalary;
        this.costTime = costTime;
    }

    @Override
    public Integer call() throws Exception {
        TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(1000));
        System.out.println("baseSalary=" + this.baseSalary + ", costTime=" + costTime);
        return baseSalary * costTime;
    }
}

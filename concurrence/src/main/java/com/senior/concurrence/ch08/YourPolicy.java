package com.senior.concurrence.ch08;

import java.lang.reflect.Field;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class YourPolicy implements RejectedExecutionHandler {

    private <T> T getOriWork(Object futureTask) {
        boolean isFutureTask = futureTask instanceof FutureTask;

        try {
            // 获取 FutureTask.callable
            Field callableField = FutureTask.class.getDeclaredField("callable");
            callableField.setAccessible(true);
            Object callableObj = callableField.get(futureTask);

            // 获取 上一步callable的数据类型：Executors的内部类RunnableAdapter
            Class<?>[] classes = Executors.class.getDeclaredClasses();
            Class tarClass = null;
            for (Class<?> cls : classes) {
                if (cls.getName().equals("java.util.concurrent.Executors$RunnableAdapter")) {
                    tarClass = cls;
                    break;
                }
            }

            // 获取原始任务对象
            Field taskField = tarClass.getDeclaredField("task");
            taskField.setAccessible(true);

            return  (T)taskField.get(callableObj);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("从FutureTask获取原始任务对象失败", e);
        }
    }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

        ShutdownDemo.Runner t = getOriWork(r);


        System.out.println("Your reject " + t.getData());
    }
}

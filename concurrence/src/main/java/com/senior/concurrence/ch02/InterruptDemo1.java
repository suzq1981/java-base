package com.senior.concurrence.ch02;

public class InterruptDemo1 {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 999999; i++) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + " is interrupted.");
                    break;
                }
                System.out.println(Thread.currentThread().getName() + " is running");
            }
        });

        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(thread.getName() + " state is " + thread.getState());
    }
}

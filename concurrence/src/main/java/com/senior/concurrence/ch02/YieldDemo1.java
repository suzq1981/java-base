package com.senior.concurrence.ch02;

public class YieldDemo1 extends Thread {

    @Override
    public void run() {
        System.out.println(this.getName() + " yield.");
        //Thread.yield();
        System.out.println(this.getName() + " run over.");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            Thread t = new YieldDemo1();
            t.start();
        }
    }
}

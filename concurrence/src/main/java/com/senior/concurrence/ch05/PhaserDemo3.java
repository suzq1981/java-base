package com.senior.concurrence.ch05;

import java.util.concurrent.Phaser;

public class PhaserDemo3 {

    public static void main(String[] args) throws InterruptedException {

        Phaser phaser = new Phaser();
        for (int i = 0; i < 4; i++) {
            phaser.register();
            new Thread(new Runner(phaser, i)).start();
        }
    }

    static class Runner implements Runnable {

        private Phaser phaser;

        private int no;

        public Runner(Phaser phaser, int no) {
            this.phaser = phaser;
            this.no = no;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " start");
                phaser.arriveAndAwaitAdvance();
                System.out.println(Thread.currentThread().getName() + " running 1");
                if (no == 1) {
                    int a = 10 / 0;
                    /*phaser.arriveAndDeregister();
                    return;*/
                }
                System.out.println(Thread.currentThread().getName() + " running 2");
                if (no == 3) {
                    phaser.arriveAndDeregister();
                    return;
                }
                phaser.arriveAndAwaitAdvance();
                System.out.println(Thread.currentThread().getName() + " end");
            } catch (Exception ex) {
                ex.printStackTrace();
                phaser.arriveAndDeregister();
            }

        }
    }

}

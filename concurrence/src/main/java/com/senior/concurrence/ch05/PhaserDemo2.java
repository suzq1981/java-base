package com.senior.concurrence.ch05;

import java.util.concurrent.Phaser;
import java.util.concurrent.ThreadLocalRandom;

public class PhaserDemo2 {

    public static void main(String[] args) throws InterruptedException {

        Phaser phaser = new WilliamPhaser(5, 3);
        System.out.println("需要参与者数量：" + phaser.getRegisteredParties());
        new Runner(phaser, 1).start();
        Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
        new Runner(phaser, 2).start();
        Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
        new Runner(phaser, 3).start();
    }

    static class WilliamPhaser extends Phaser {

        //总计阶段数量
        private int totalPhaseNum = 3;

        public WilliamPhaser(int totalPhaseNum, int parties) {
            super(parties);
            this.totalPhaseNum = totalPhaseNum;
        }

        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            System.out.println("phase " + phase + " is over, registeredParties is " + registeredParties);
            //如果已经到达了最后一个阶段，或者参与者为0，则结束
            return (totalPhaseNum - 1) == phase || registeredParties == 0;
        }
    }


    static class Runner extends Thread {

        private Phaser phaser;
        private int no;

        public Runner(Phaser phaser, int no) {
            this.no = no;
            this.phaser = phaser;
        }

        @Override
        public void run() {
            try {
                System.out.println(this.getName() + " is ready1");
                phaser.arriveAndAwaitAdvance();//1
                System.out.println(this.getName() + " running...");
                Thread.sleep(ThreadLocalRandom.current().nextInt(3000));
                System.out.println(this.getName() + " is ready2");
                phaser.arriveAndAwaitAdvance();//2
                System.out.println(this.getName() + " running...");
                Thread.sleep(ThreadLocalRandom.current().nextInt(3000));
                System.out.println(this.getName() + " is ready3");
                phaser.arriveAndAwaitAdvance();//3
                System.out.println(this.getName() + " running...");
                Thread.sleep(ThreadLocalRandom.current().nextInt(3000));
                System.out.println(this.getName() + " is ready4");
                phaser.arriveAndAwaitAdvance();//4
                System.out.println(this.getName() + " running...");
                Thread.sleep(ThreadLocalRandom.current().nextInt(3000));
                System.out.println(this.getName() + " is ready5");
                phaser.arriveAndAwaitAdvance();//5
                Thread.sleep(ThreadLocalRandom.current().nextInt(3000));
                System.out.println(this.getName() + " over");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

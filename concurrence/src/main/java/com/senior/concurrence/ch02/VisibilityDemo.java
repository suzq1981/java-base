package com.senior.concurrence.ch02;

public class VisibilityDemo {

    //可见性问题, 加了volatile就解决了
    static int money = 1000;//初始钱数

    public static void main(String[] args) {

        Thread women = new Thread(() -> {
            while (money < 200000) ;

            System.out.println("我们可以结婚了");
        });

        Thread men = new Thread(() -> {
            System.out.println("给我5年,我存够钱来娶你");
            //搞个休眠,模拟5年
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            money = 200000;
            System.out.println("我存够钱了!");
        });
        women.start();
        men.start();

    }
}
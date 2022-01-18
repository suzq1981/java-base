package com.djt.jdk8;

import java.util.Random;

public class Lambda001 {

	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Random random = new Random();
				System.out.println("匿名类 *** 程序员是Number two. 随机数：" + random.nextInt(100));
			}
		}).start();

		new Thread(() -> {
			Random random = new Random();
			System.out.println("Lambda*** 程序员是Number two. 随机数：" + random.nextInt(100));
		}).start();
	}

}

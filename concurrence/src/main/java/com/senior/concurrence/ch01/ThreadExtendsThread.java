package com.senior.concurrence.ch01;

import com.senior.concurrence.entity.Account;

public class ThreadExtendsThread extends Thread {

    private Account account;

    public ThreadExtendsThread() {
    }

    public ThreadExtendsThread(Account account) {
        this.account = account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        System.out.println(this.getName() + " Account's information : " + this.account + ", " + this.getState());
    }

    public static void main(String[] args) throws Exception {

        Account account = new Account("80808", "Alexander", true);
        ThreadExtendsThread thread1 = new ThreadExtendsThread(account);
        System.out.println(thread1.getState());
        thread1.start();

        Thread.sleep(1000);//主线程也就是main线程，停止1秒

        System.out.println(thread1.getState());

        ThreadExtendsThread thread2 = new ThreadExtendsThread(account);
        thread2.start();
    }
}

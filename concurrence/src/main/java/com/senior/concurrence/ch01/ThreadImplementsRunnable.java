package com.senior.concurrence.ch01;

import com.senior.concurrence.entity.Account;

public class ThreadImplementsRunnable implements Runnable {

    private Account account;

    public ThreadImplementsRunnable() {
    }

    public ThreadImplementsRunnable(Account account) {
        this.account = account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + " Account: " + this.account);
    }

    public static void main(String[] args) {
        Account account = new Account("7373886", "William", true);

        ThreadImplementsRunnable thread1 = new ThreadImplementsRunnable();
        thread1.setAccount(account);
        new Thread(thread1).start();

        ThreadImplementsRunnable thread2 = new ThreadImplementsRunnable(account);
        new Thread(thread2).start();

        new Thread(() -> System.out.println(account)).start();

    }
}

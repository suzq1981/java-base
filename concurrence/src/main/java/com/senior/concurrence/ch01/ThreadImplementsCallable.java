package com.senior.concurrence.ch01;

import com.senior.concurrence.entity.Account;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class ThreadImplementsCallable implements Callable<Account> {

    private String accountNo;

    public ThreadImplementsCallable(){

    }

    public ThreadImplementsCallable(String accountNo){
        this.accountNo = accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    @Override
    public Account call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " Account No=" + this.accountNo);
        Account account = new Account();
        account.setAccountNo(this.accountNo);
        account.setAccountName("William");
        account.setValid(true);
        TimeUnit.SECONDS.sleep(3);

        return account;
    }

    public static void main(String[] args) throws Exception {

        ThreadImplementsCallable callable = new ThreadImplementsCallable("729988");
        FutureTask<Account> task = new FutureTask<>(callable);


        Thread thread = new Thread(task);
        thread.start();
        TimeUnit.SECONDS.sleep(3);

        System.out.println("获取数据......");
        Account account = task.get();
        System.out.println(account);
    }

}

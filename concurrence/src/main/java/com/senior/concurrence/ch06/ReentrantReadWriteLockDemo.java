package com.senior.concurrence.ch06;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;

public class ReentrantReadWriteLockDemo {

    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    ReadLock readLock = rwLock.readLock();
    WriteLock writeLock = rwLock.writeLock();

    public void read() {
        try {
            readLock.lock();
            System.out.println("当前线程:" + Thread.currentThread().getName() + "read进入...");
            Thread.sleep(3000);
            System.out.println("当前线程:" + Thread.currentThread().getName() + "read退出...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }

    public void write() {
        try {
            writeLock.lock();
            System.out.println("当前线程:" + Thread.currentThread().getName() + "write进入...");
            Thread.sleep(3000);
            System.out.println("当前线程:" + Thread.currentThread().getName() + "write退出...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        final ReentrantReadWriteLockDemo urrw = new ReentrantReadWriteLockDemo();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                urrw.read();
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                urrw.read();
            }
        }, "t2");
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                urrw.write();
            }
        }, "t3");
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                urrw.write();
            }
        }, "t4");

        t1.start(); // Read
        t2.start(); // Read

        //t1.start(); // Read
        //t3.start(); // Write

        //t3.start(); //write
        //t4.start(); //write
    }

}

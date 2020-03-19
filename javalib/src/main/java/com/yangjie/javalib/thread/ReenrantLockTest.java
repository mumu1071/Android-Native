package com.yangjie.javalib.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yangjie
 * @description:
 * @date :2020/3/14 4:20 PM
 */
/*
 * 使用ReentrantLock类实现同步
 * */

public class ReenrantLockTest {

    static class MyReenrantLock implements Runnable {
        //向上转型
        private Lock lock = new ReentrantLock();

        public void run() {
            //上锁
            lock.lock();
            for (int i = 0; i < 5; i++) {
                System.out.println("当前线程名： " + Thread.currentThread().getName() + " ,i = " + i);
            }
            //释放锁
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        MyReenrantLock myReenrantLock = new MyReenrantLock();
        Thread thread1 = new Thread(myReenrantLock);
        Thread thread2 = new Thread(myReenrantLock);
        Thread thread3 = new Thread(myReenrantLock);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}




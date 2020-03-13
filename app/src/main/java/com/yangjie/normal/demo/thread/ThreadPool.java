package com.yangjie.normal.demo.thread;

import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {

    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(3000);
                Log.e("TAG", "run : "  + "  当前线程：" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    public void test01() {
        //创建基本线程池
        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                3, 5, 1, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(50));

        for (int i = 0; i < 30; i++) {
            final int finali = i;

            threadPoolExecutor.execute(runnable);
        }
    }

    public void test02(){
        //创建及执行
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        //执行上述Demo的runnable
        fixedThreadPool.execute(runnable);

        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        //执行上述Demo的runnable
        singleThreadExecutor .execute(runnable);
        //创建及执行
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        //执行上述Demo的runnable
        cachedThreadPool.execute(runnable);

        //创建及执行
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        //延迟5秒执行
        scheduledExecutorService.schedule(runnable, 5, TimeUnit.SECONDS);

    }




}

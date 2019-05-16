package com.flf;

import java.util.concurrent.CountDownLatch;

public class ManyThreadTest implements PerformanceTest{

    private PerformanceTest test = null;

    private CountDownLatch latch = null;

    private int threadNum = 500;

    public ManyThreadTest(PerformanceTest test,int threadNum) {
        this.test = test;
        this.threadNum = threadNum;
    }

    private class TestThread implements Runnable{
        @Override
        public void run() {
            test.performance();
            System.out.println("threadName:" + Thread.currentThread().getName() + "结束.");
            latch.countDown();
        }
    }

    @Override
    public void performance() {
        if(test == null){
            System.out.println("测试对象为空");
            return;
        }
        latch = new CountDownLatch(threadNum);
        for (int i = 0 ; i < threadNum; ++i){
            Thread thread = new Thread(new TestThread());
            System.out.println("threadName:" + thread.getName() + "开始.");
            thread.start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

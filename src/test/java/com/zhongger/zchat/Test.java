package com.zhongger.zchat;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Test implements Runnable {
    Integer index;

    public Test(Integer index) {
        this.index = index;
    }

    @SneakyThrows
    @Override
    public void run() {
        System.out.println(Thread.currentThread().toString()+"  index:"+index+" time:"+System.currentTimeMillis()+"执行开始");
        Thread.sleep(10000);
    }
}

package com.zhongger.zchat;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class Thread_B implements Runnable{
    CountDownLatch countDownLatch;
    Logger logger=  LoggerFactory.getLogger(this.getClass());
    public Thread_B(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @SneakyThrows
    @Override
    public void run() {
        logger.info("执行线程B:"+Thread.currentThread().toString());
        Thread.sleep(5000);
        logger.info("执行线程B完成，准备执行主线程");
        if(countDownLatch!=null){
            countDownLatch.countDown();
        }
    }
}

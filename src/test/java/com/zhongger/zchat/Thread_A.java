package com.zhongger.zchat;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class Thread_A implements Runnable{
    CountDownLatch countDownLatch;
   Logger logger=  LoggerFactory.getLogger(this.getClass());
    public Thread_A(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @SneakyThrows
    @Override
    public void run() {
        logger.info("执行线程A:"+Thread.currentThread().toString());
        Thread.sleep(3000);
        logger.info("执行线程A完成，准备执行主线程");
        if(countDownLatch!=null){
            countDownLatch.countDown();
        }
    }
}

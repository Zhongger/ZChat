//package com.zhongger.zchat.mqtest;
//
//
//import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
//import org.apache.rocketmq.spring.core.RocketMQListener;
//import org.springframework.stereotype.Component;
//
//@Component
//@RocketMQMessageListener(consumerGroup = "MyConsumerGroup" ,topic ="TestTopic" )
//public class MqConsumer implements RocketMQListener<String> {
//    public String message;
//    @Override
//    public void onMessage(String message) {
//        this.message=message;
//        System.out.println(message);
//    }
//}

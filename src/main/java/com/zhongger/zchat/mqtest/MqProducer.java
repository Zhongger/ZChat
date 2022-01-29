//package com.zhongger.zchat.mqtest;
//
//import org.apache.rocketmq.client.producer.SendResult;
//import org.apache.rocketmq.spring.core.RocketMQTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MqProducer {
//    @Autowired
//    private RocketMQTemplate rocketMQTemplate;
//    public void sendMes(String topic,String msg){
//        rocketMQTemplate.convertAndSend(topic,msg);
//    }
//    public void sendthrantMq(String topic,String msg){
//        String[] tags=new String[]{"TagA","TagB","TagC"};
//        for(int i=0;i<10;i++){
//            Message<String> message= MessageBuilder.withPayload(msg).build();
//            String dest=topic+":"+tags[i% tags.length];
//            SendResult sendResult=rocketMQTemplate.sendMessageInTransaction(dest,message,dest);
//        }
//    }
//}

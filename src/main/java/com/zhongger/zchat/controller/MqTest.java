//package com.zhongger.zchat.controller;
//
//
//import com.zhongger.zchat.mqtest.MqConsumer;
//import com.zhongger.zchat.mqtest.MqProducer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class MqTest {
//    @Autowired
//    MqProducer mqProducer;
//    @Autowired
//    MqConsumer mqConsumer;
//    @GetMapping("/mq")
//    public String mq(@RequestParam(value = "topic") String topic,@RequestParam(value = "msg") String msg){
//        mqProducer.sendthrantMq(topic,msg);
//        return mqConsumer.message;
//    }
//
//}

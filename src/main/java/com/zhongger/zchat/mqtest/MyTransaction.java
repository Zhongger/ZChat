//package com.zhongger.zchat.mqtest;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
//import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
//import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
//import org.apache.rocketmq.spring.support.RocketMQUtil;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.converter.StringMessageConverter;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.LinkedList;
//import java.util.concurrent.ConcurrentHashMap;
//
//@RocketMQTransactionListener(rocketMQTemplateBeanName = "rocketMQTemplate")
//public class MyTransaction implements RocketMQLocalTransactionListener {
//    private ConcurrentHashMap<Object, String> localTrans = new ConcurrentHashMap<>();
//    @Override
//    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
//
//        Object id =message.getHeaders().get("id");
//        String des=o.toString();
//        localTrans.put(id,des);
//        org.apache.rocketmq.common.message.Message message1= RocketMQUtil.convertToRocketMessage(new StringMessageConverter(),"UTF-8",des,message);
//        String tags=message1.getTags();
//        System.out.println(message1.toString());
//        if(StringUtils.contains(tags,"TagA")){
//
//
//            return RocketMQLocalTransactionState.COMMIT;
//        }else {
//            return RocketMQLocalTransactionState.UNKNOWN;
//        }
//    }
//
//    @Override
//    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
//        ArrayList<> arrayList=new ArrayList();
//        LinkedList<> list=new LinkedList();
//        HashMap<String,String> map=new HashMap<>();
//        map.put();
//        arrayList.add("d");
//        return null;
//    }
//}

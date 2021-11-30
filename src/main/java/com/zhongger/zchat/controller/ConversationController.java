package com.zhongger.zchat.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhongger.zchat.PO.CreateConversation;
import com.zhongger.zchat.PO.SearchMessage;
import com.zhongger.zchat.PO.SendMessage;
import com.zhongger.zchat.VO.ReConversation;
import com.zhongger.zchat.VO.ReMessage;
import com.zhongger.zchat.entity.Conversation;
import com.zhongger.zchat.entity.Message;
import com.zhongger.zchat.service.ConversationService;
import com.zhongger.zchat.service.MessageService;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class ConversationController {
    @Autowired
    ConversationService conversationService;
    @Autowired
    MessageService messageService;
    @Autowired
    private Redisson redisson;

    Logger logger= LoggerFactory.getLogger(this.getClass());
    /**
     * 创建会话接口
     * 雷立 2021/11/2
     * @param createConversation
     * @return
     */
    @PostMapping("/createConversation")
    public ReConversation createConversation(@RequestBody CreateConversation createConversation){

        String lock="Conversationlock";
        RLock redissonLock=redisson.getLock(lock);
        try{
            redissonLock.lock();
            //查询是否创建过会话
            Conversation selectforsender = conversationService.selectforsender(createConversation);
            if(selectforsender!=null){
                return new ReConversation(200,selectforsender,true);
            }
            Conversation conversation =new Conversation();
            //将sender和to_id插入到表中之后反过来再插入一次
            conversation.setTo_id(createConversation.getSender());
            conversation.setSender(createConversation.getTo_id());
            conversation.setCreate_time(new Date());
            conversation.setConversation_id(UUID.randomUUID().toString().replace("-",""));
            conversationService.insert(conversation);
            conversation.setTo_id(createConversation.getTo_id());
            conversation.setSender(createConversation.getSender());
            conversationService.insert(conversation);
            return new ReConversation(200,conversation,true);
        }finally {
            redissonLock.unlock();
        }


    }
    /**
     * 创建消息接口
     * 雷立 2021/11/3
     */
    @PostMapping("/sendmessage")
    public ReMessage  sendmessage(@RequestBody SendMessage sendMessage){
        Message message=new Message();
        message.setMessage_id(UUID.randomUUID().toString());
        message.setConversation_id(sendMessage.getConversation_id());
        message.setSender(sendMessage.getSender());
        message.setContent(sendMessage.getContent());
        message.setCreate_time(new Date());
        messageService.insert(message);
        List<Message>list =new ArrayList<Message>();
        list.add(message);
        return new ReMessage(200,list,true);
    }
    /**
     * 搜索消息接口
     * 雷立 2021/11/4
     */
    @PostMapping("/searchmessage")
    public PageInfo<Message> searchmessage(@RequestBody SearchMessage searchMessage){

        PageHelper.startPage(searchMessage.getPagenum(),searchMessage.getPagesize());
        PageInfo<Message> pageInfo = new PageInfo<>(messageService.selectforcontent(searchMessage.getContent()));

        return pageInfo;
    }
}

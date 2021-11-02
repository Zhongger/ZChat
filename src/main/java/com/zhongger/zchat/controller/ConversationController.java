package com.zhongger.zchat.controller;


import com.zhongger.zchat.PO.CreateConversation;
import com.zhongger.zchat.VO.ReConversation;
import com.zhongger.zchat.VO.ResrponesUser;
import com.zhongger.zchat.entity.Conversation;
import com.zhongger.zchat.service.ConversationService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
public class ConversationController {
    @Autowired
    ConversationService conversationService;
    @PostMapping("/createConversation")
    public ReConversation createConversation(@RequestBody CreateConversation createConversation){
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

    }
}

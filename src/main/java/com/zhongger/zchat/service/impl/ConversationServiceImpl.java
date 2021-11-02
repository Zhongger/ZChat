package com.zhongger.zchat.service.impl;

import com.zhongger.zchat.PO.CreateConversation;
import com.zhongger.zchat.entity.Conversation;
import com.zhongger.zchat.mapper.ConversationMapper;
import com.zhongger.zchat.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConversationServiceImpl implements ConversationService {
    @Autowired
    ConversationMapper conversationMapper;
    @Override
    public Integer insert(Conversation conversation) {
        return conversationMapper.insert(conversation);
    }

    @Override
    public Conversation selectforsender(CreateConversation createConversation) {
        return conversationMapper.selectforsender(createConversation);
    }
}

package com.zhongger.zchat.service;

import com.zhongger.zchat.PO.CreateConversation;
import com.zhongger.zchat.entity.Conversation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

@Service
public interface ConversationService {
    Integer insert(Conversation conversation);
    Conversation selectforsender(CreateConversation createConversation);
}

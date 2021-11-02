package com.zhongger.zchat.mapper;

import com.zhongger.zchat.PO.CreateConversation;
import com.zhongger.zchat.entity.Conversation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConversationMapper {
    Integer insert(Conversation conversation);
    Conversation selectforsender(CreateConversation createConversation);
}

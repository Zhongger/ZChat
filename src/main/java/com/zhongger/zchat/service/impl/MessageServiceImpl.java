package com.zhongger.zchat.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhongger.zchat.entity.Message;
import com.zhongger.zchat.mapper.MessageMapper;
import com.zhongger.zchat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageMapper messageMapper;
    @Override
    public Integer insert(Message message) {
        return messageMapper.insert(message);
    }

    @Override
    public Page<Message> selectforcontent(String content) {

        return messageMapper.selectforcontent(content);
    }
}

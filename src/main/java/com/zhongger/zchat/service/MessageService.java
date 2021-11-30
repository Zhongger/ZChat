package com.zhongger.zchat.service;

import com.github.pagehelper.Page;
import com.zhongger.zchat.entity.Message;
import org.springframework.stereotype.Service;

@Service
public interface MessageService {
    Integer insert(Message message);
    Page<Message> selectforcontent(String content );
}

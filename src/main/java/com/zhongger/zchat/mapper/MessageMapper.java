package com.zhongger.zchat.mapper;

import com.github.pagehelper.Page;
import com.zhongger.zchat.entity.Message;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageMapper {
    Integer insert(Message message);
    Page<Message> selectforcontent(String content);
}

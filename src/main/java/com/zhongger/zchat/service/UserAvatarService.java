package com.zhongger.zchat.service;

import com.zhongger.zchat.entity.UserAvatar;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Mapper
public interface UserAvatarService {
    Integer insert(UserAvatar userAvatar);
    UserAvatar select(Integer user_id);
    Integer updataforuser(UserAvatar userAvatar);
}

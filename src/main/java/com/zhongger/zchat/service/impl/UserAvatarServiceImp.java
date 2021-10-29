package com.zhongger.zchat.service.impl;

import com.zhongger.zchat.entity.UserAvatar;
import com.zhongger.zchat.mapper.UserAvatarMapper;
import com.zhongger.zchat.service.UserAvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAvatarServiceImp implements UserAvatarService {
    @Autowired
    private UserAvatarMapper userAvatarMapper;
    @Override
    public Integer insert(UserAvatar userAvatar) {
        return userAvatarMapper.insert(userAvatar);
    }

    @Override
    public UserAvatar select(Integer user_id) {
        return userAvatarMapper.selectforuser(user_id);
    }

    @Override
    public Integer updataforuser(UserAvatar userAvatar) {
        return userAvatarMapper.updateforuser(userAvatar);
    }

    @Override
    public Integer delete(UserAvatar userAvatar) {
        return userAvatarMapper.delete(userAvatar);
    }
}

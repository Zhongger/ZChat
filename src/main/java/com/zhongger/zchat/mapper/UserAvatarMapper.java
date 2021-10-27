package com.zhongger.zchat.mapper;

import com.zhongger.zchat.entity.UserAvatar;

public interface UserAvatarMapper {
    Integer insert (UserAvatar userAvatar);
    UserAvatar selectforuser(Integer user_id);
    Integer updateforuser(UserAvatar userAvatar);

}

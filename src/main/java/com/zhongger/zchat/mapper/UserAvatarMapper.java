package com.zhongger.zchat.mapper;

import com.zhongger.zchat.entity.UserAvatar;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserAvatarMapper {
    Integer insert (UserAvatar userAvatar);
    UserAvatar selectforuser(Integer user_id);
    Integer updateforuser(UserAvatar userAvatar);
    Integer delete(UserAvatar userAvatar);
}

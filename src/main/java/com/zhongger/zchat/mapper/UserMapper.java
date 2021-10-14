package com.zhongger.zchat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhongger.zchat.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserMapper extends BaseMapper<User> {
    int insert(User record);

    List<User> selectAll();
    String selectForUsername(String userName);
    String selectForUphone(String phone);
}
package com.zhongger.zchat.mapper;

import com.zhongger.zchat.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Mapper
public interface UserMapper {
    int insert(User record);

    List<User> selectAll();
    String selectForUsername(String userName);
    String selectForUphone(String phone);
}
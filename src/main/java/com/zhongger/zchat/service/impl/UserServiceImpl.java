package com.zhongger.zchat.service.impl;

import com.zhongger.zchat.entity.User;
import com.zhongger.zchat.mapper.UserMapper;
import com.zhongger.zchat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhongmingyi
 * @date 2021/10/12 9:27 下午
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int insertUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int batchInsertUser(List<User> userList) {
        return userMapper.batchInsertUser(userList);
    }
}

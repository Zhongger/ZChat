package com.zhongger.zchat.service;

import com.zhongger.zchat.entity.User;

import java.util.List;

/**
 * @author zhongmingyi
 * @date 2021/10/12 9:26 下午
 */
public interface UserService {

    int insertUser(User user);

    int batchInsertUser(List<User> userList);
}

package com.zhongger.zchat.service;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
//    注册接口
    int add(String username,String password,String phone);
    //登录接口
    String select(String username);
}

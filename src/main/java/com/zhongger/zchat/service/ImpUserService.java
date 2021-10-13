package com.zhongger.zchat.service;

import com.zhongger.zchat.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface ImpUserService {
//    注册接口
    int add(String username,String password,String phone);
    //登录接口
    String select(String username);
}

package com.zhongger.zchat.service;

import com.zhongger.zchat.PO.UserRevise;
import com.zhongger.zchat.entity.Userforleili;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
//    注册接口
    int add(String username,String password,String phone);
    //登录接口
    String select(String username);
    //删除用户接口
    Integer delete(String username);
    Integer update(UserRevise userRevise);

    Userforleili selectAll (String username);

}

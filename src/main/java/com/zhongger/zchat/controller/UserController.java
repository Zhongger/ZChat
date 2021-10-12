package com.zhongger.zchat.controller;

import com.zhongger.zchat.entity.User;
import com.zhongger.zchat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhongmingyi
 * @date 2021/10/11 11:50 上午
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user) {
        int insertUser = userService.insertUser(user);
        return "success insert " + insertUser;
    }

    @GetMapping("/batchAddUserTest")
    public String batchAddUserTest() {
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setUsername("ZMY");
        user1.setPassword("123456");

        User user2 = new User();
        user2.setUsername("LL");
        user2.setPassword("12345678");

        userList.add(user1);
        userList.add(user2);

        int batchInsertUser = userService.batchInsertUser(userList);
        return "success insert " + batchInsertUser;
    }
}

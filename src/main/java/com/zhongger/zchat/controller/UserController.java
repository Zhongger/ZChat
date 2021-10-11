package com.zhongger.zchat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhongmingyi
 * @date 2021/10/11 11:50 上午
 */
@RestController
public class UserController {

    @GetMapping("/test")
    public String test() {
        return "test";
    }

}

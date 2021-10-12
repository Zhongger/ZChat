package com.zhongger.zchat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhongger.zchat.mapper")
public class ZChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZChatApplication.class, args);
    }

}

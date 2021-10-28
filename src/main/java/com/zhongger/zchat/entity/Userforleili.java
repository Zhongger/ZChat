package com.zhongger.zchat.entity;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
@Data
public class Userforleili {
    private Integer userId;

    private String userName;

    private String password;

    private String phone;
    private Integer staus;

    public Userforleili() {
    }

    public Userforleili(String userName, String password, String phone) {
        this.userName = userName;
        this.password = password;
        this.phone = phone;
    }


}
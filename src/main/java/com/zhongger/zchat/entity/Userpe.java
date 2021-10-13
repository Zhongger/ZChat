package com.zhongger.zchat.entity;


import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class Userpe {

    private  String username;

    private  String password;

    private  String phone;





    @Override
    public String toString() {
        return "Userpe{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

}

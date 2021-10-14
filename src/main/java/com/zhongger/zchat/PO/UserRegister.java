package com.zhongger.zchat.PO;


import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class UserRegister {

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

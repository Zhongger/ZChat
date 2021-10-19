package com.zhongger.zchat.PO;


import lombok.Data;

@Data
public class UserRevise {
    private String username;
    private String password;
    private String phone;
    private String old_username;
    private String old_phone;

    public UserRevise(String username, String password, String phone, String old_username, String old_phone) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.old_username = old_username;
        this.old_phone = old_phone;
    }

    public UserRevise() {
    }
}

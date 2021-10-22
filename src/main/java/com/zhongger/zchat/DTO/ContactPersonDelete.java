package com.zhongger.zchat.DTO;


import lombok.Data;

@Data
public class ContactPersonDelete {
    private  String username;
    private  String phone;
    private  String user_username;

    public ContactPersonDelete() {
    }

    public ContactPersonDelete(String username, String phone, String user_username) {
        this.username = username;
        this.phone = phone;
        this.user_username = user_username;
    }
}

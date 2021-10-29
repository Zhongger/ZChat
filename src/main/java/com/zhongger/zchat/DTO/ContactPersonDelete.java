package com.zhongger.zchat.DTO;


import lombok.Data;

@Data
public class ContactPersonDelete {

    private  String phone;
    private  String user_username;

    public ContactPersonDelete() {
    }

    public ContactPersonDelete( String phone, String user_username) {

        this.phone = phone;
        this.user_username = user_username;
    }
}

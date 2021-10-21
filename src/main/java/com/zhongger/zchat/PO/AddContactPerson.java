package com.zhongger.zchat.PO;

import lombok.Data;

@Data
public class AddContactPerson {
    private String username;
    private String phone;

    public AddContactPerson() {
    }

    public AddContactPerson(String username, String phone) {
        this.username = username;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "AddContactPerson{" +
                "username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

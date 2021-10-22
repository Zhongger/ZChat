package com.zhongger.zchat.PO;

import lombok.Data;

@Data
public class DeleteContactPerson {
    private String username;
    private String phone;

    public DeleteContactPerson() {
    }

    public DeleteContactPerson(String username, String phone) {
        this.username = username;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "DeleteContactPerson{" +
                "username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

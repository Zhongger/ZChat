package com.zhongger.zchat.PO;

import lombok.Data;

@Data
public class AddContactPerson {

    private String phone;

    public AddContactPerson() {
    }

    public AddContactPerson(String username, String phone) {

        this.phone = phone;
    }


}

package com.zhongger.zchat.PO;

import lombok.Data;

@Data
public class UserDelete {
    private String username;
    private String password;

    public UserDelete() {
    }

    public UserDelete(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

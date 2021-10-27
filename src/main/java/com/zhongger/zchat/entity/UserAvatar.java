package com.zhongger.zchat.entity;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;

@Data
public class UserAvatar {
    private Integer id;

    private byte[] file;
    private Integer user_id;
    private  String image_name;
    public UserAvatar() {
    }

    public UserAvatar(Integer id, byte[] file, Integer user_id, String image_name) {
        this.id = id;
        this.file = file;
        this.user_id = user_id;
        this.image_name = image_name;
    }
}

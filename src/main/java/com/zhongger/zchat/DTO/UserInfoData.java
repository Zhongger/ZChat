package com.zhongger.zchat.DTO;


import lombok.Data;

@Data
public class UserInfoData {
    private byte[] data;
    private  String image_name;
    private Integer userId;
    private String userName;
    private String phone;
}

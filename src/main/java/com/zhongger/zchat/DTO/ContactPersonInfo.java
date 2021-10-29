package com.zhongger.zchat.DTO;

import lombok.Data;
@Data
public class ContactPersonInfo {
        private String imag;
        private Integer userId;
        private String userName;
        private String phone;
        private Integer staus;

        public ContactPersonInfo() {
        }

        public ContactPersonInfo(String imag, Integer userId, String userName, String phone, Integer staus) {
                this.imag = imag;
                this.userId = userId;
                this.userName = userName;
                this.phone = phone;
                this.staus = staus;
        }
}

package com.zhongger.zchat.entity;

import org.springframework.stereotype.Component;

@Component
public class ResrponesUser {
    private Integer code;
    private String msg;
    private Boolean isSuc;

    public ResrponesUser() {
    }

    public ResrponesUser(Integer code, String msg, Boolean isSuc) {
        this.code = code;
        this.msg = msg;
        this.isSuc = isSuc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getSuc() {
        return isSuc;
    }

    public void setSuc(Boolean suc) {
        isSuc = suc;
    }

    @Override
    public String toString() {
        return "ResrponesUser{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", isSuc=" + isSuc +
                '}';
    }
}

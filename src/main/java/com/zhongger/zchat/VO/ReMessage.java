package com.zhongger.zchat.VO;

import com.zhongger.zchat.DTO.ContactPersonInfo;
import com.zhongger.zchat.entity.Message;
import lombok.Data;

import java.util.List;

@Data
public class ReMessage {
    private Integer code;
    private List<Message> msg;
    private Boolean istrue;


    public ReMessage() {
    }

    public ReMessage(Integer code, List<Message> msg, Boolean istrue) {
        this.code = code;
        this.msg = msg;
        this.istrue = istrue;
    }
}

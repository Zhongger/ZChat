package com.zhongger.zchat.VO;

import com.zhongger.zchat.entity.Conversation;
import lombok.Data;

@Data
public class ReConversation {
    private Integer code;
    private Conversation msg;
    private Boolean isSuc;

    public ReConversation() {
    }

    public ReConversation(Integer code, Conversation msg, Boolean isSuc) {
        this.code = code;
        this.msg = msg;
        this.isSuc = isSuc;
    }
}

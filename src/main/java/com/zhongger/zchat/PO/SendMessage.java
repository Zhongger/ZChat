package com.zhongger.zchat.PO;

import lombok.Data;

@Data
public class SendMessage {
    private  Integer sender;
    private  String content;
    private  String conversation_id;
}

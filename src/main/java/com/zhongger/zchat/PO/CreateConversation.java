package com.zhongger.zchat.PO;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class CreateConversation {
    private Integer sender;
    private Integer to_id ;

}

package com.zhongger.zchat.PO;

import lombok.Data;

@Data
public class SearchMessage {
    private  Integer pagenum;
    private  Integer pagesize;
    private  String content;

}

package com.zhongger.zchat.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class Message {
    @JsonIgnore
    private Integer id;
    private Integer sender;
    private  String message_id;
    private  String conversation_id;
    private  Integer type;
    private  String content;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date create_time;
}

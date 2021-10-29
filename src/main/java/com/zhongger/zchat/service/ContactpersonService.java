package com.zhongger.zchat.service;

import com.zhongger.zchat.DTO.ContactPersonDelete;
import com.zhongger.zchat.DTO.ContactPersonInfo;
import com.zhongger.zchat.entity.Contactperson;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContactpersonService {
    Integer insert(Contactperson contactperson);
    Integer deleteforuserid(String username);
    Integer delete(ContactPersonDelete contactPersonDelete);
    Contactperson select(ContactPersonDelete contactPersonDelete);
    List<ContactPersonInfo> selectforuserid(Integer user_id);
}

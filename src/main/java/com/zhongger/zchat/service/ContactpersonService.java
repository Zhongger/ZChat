package com.zhongger.zchat.service;

import com.zhongger.zchat.DTO.ContactPersonDelete;
import com.zhongger.zchat.entity.Contactperson;
import org.springframework.stereotype.Service;

@Service
public interface ContactpersonService {
    Integer insert(Contactperson contactperson);
    Integer deleteforuserid(String username);
    Integer delete(ContactPersonDelete contactPersonDelete);
    Contactperson select(ContactPersonDelete contactPersonDelete);
}

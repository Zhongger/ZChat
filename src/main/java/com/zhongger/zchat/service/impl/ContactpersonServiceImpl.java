package com.zhongger.zchat.service.impl;

import com.zhongger.zchat.entity.Contactperson;
import com.zhongger.zchat.mapper.ContactPersonMapper;
import com.zhongger.zchat.service.ContactpersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactpersonServiceImpl implements ContactpersonService {
    @Autowired
    ContactPersonMapper contactPersonMapper;


    @Override
    public Integer insert(Contactperson contactperson) {

        return  contactPersonMapper.insert(contactperson);
    }

    @Override
    public Integer deleteforuserid(String username) {
        return  contactPersonMapper.deleteforuserid(username);
    }
}

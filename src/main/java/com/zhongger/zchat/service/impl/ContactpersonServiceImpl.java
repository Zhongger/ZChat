package com.zhongger.zchat.service.impl;

import com.zhongger.zchat.DTO.ContactPersonDelete;
import com.zhongger.zchat.DTO.ContactPersonInfo;
import com.zhongger.zchat.entity.Contactperson;
import com.zhongger.zchat.mapper.ContactPersonMapper;
import com.zhongger.zchat.service.ContactpersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public Integer delete(ContactPersonDelete contactPersonDelete) {
        return contactPersonMapper.delete(contactPersonDelete);
    }

    @Override
    public Contactperson select(ContactPersonDelete contactPersonDelete) {
        return contactPersonMapper.select(contactPersonDelete);
    }

    @Override
    public List<ContactPersonInfo> selectforuserid(Integer user_id) {
        return contactPersonMapper.selectforuserid(user_id);
    }
}

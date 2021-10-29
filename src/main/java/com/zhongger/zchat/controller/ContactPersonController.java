package com.zhongger.zchat.controller;

import com.zhongger.zchat.DTO.ContactPersonDelete;
import com.zhongger.zchat.DTO.ContactPersonInfo;
import com.zhongger.zchat.PO.AddContactPerson;
import com.zhongger.zchat.PO.DeleteContactPerson;
import com.zhongger.zchat.PO.UserLogin;
import com.zhongger.zchat.VO.ReContactPerson;
import com.zhongger.zchat.VO.ResrponesUser;
import com.zhongger.zchat.entity.Contactperson;
import com.zhongger.zchat.entity.Userforleili;
import com.zhongger.zchat.service.ContactpersonService;
import com.zhongger.zchat.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class ContactPersonController {
    @Resource
    com.zhongger.zchat.service.UserService UserService;
    @Resource
    ContactpersonService contactpersonService;
    @Value("${regular.username}")
    String usernameRegular;
    @Value("${regular.password}")
    String passwordRegular;
    @Value("${regular.phone}")
    String phoneRegular;

    /**
     * 增加联系人接口
     * 雷立 2021/10/21
     * @param addContactPerson
     * @param
     * @return
     */
    @PostMapping("/addcontactperson")
    public ResrponesUser addContactPerson(@RequestBody AddContactPerson addContactPerson, @CookieValue(value = "cookie_username")String usernames){

        //查询当前用户是否添加了改联系人
        ContactPersonDelete contactPersonDelete=new ContactPersonDelete( addContactPerson.getPhone(), usernames);
        Contactperson contactpersonforall=contactpersonService.select(contactPersonDelete);
        if(contactpersonforall!=null){
            return new ResrponesUser(500,"添加失败,改联系人已被添加",false);
        }

        //查询当前用户的userid
        Userforleili userforleili =UserService.selectAll(usernames);
        //查询新加联系人的userid和username
        Userforleili person=UserService.selectAll(addContactPerson.getPhone());
        if(person==null){
            return  new ResrponesUser(500,"查找不到该用户，添加失败",false);
        }
        Contactperson contactperson=new Contactperson();
        contactperson.setPerson_name(person.getUserName());
        contactperson.setPerson_id(person.getUserId());
        contactperson.setPerson_phone(addContactPerson.getPhone());
        contactperson.setUser_id(userforleili.getUserId());

        contactperson.setCreate_date(new Date());
        contactpersonService.insert(contactperson);

        return  new ResrponesUser(200,"添加成功",true);
    }

    /**
     * 删除联系人接口
     * 雷立
     * @param deleteContactPerson
     * @param usernames
     * @return
     */
    @PostMapping("/deletecontactperson")
    public ResrponesUser deleteContactPerson(@RequestBody DeleteContactPerson deleteContactPerson,@CookieValue(value = "cookie_username")String usernames){

        ContactPersonDelete contactPersonDelete =new ContactPersonDelete(deleteContactPerson.getPhone(), usernames);
        contactpersonService.delete(contactPersonDelete);
        return  new ResrponesUser(200,"删除成功",true);

    }
    /**
     * 返回用户联系人列表信息
     */
    @PostMapping("/contactpersoninfo")
    public ReContactPerson contactpersoninfo(@CookieValue(value = "cookie_username")String username){
        //查询当前用户的user_id
        Userforleili userforleili=UserService.selectAll(username);

        List<ContactPersonInfo> list=contactpersonService.selectforuserid(userforleili.getUserId());
        return  new ReContactPerson(200,list,true);


    }
}

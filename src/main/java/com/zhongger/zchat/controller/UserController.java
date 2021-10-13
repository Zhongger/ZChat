package com.zhongger.zchat.controller;

import com.zhongger.zchat.entity.ResrponesUser;
import com.zhongger.zchat.entity.UserLogin;
import com.zhongger.zchat.entity.Userpe;
import com.zhongger.zchat.service.ImpUserService;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;


/**
 * @author zhongmingyi
 * @date 2021/10/11 11:50 上午
 */
@RestController
@PropertySource(value = "classpath:commonconfiguration/regular.properties")
public class UserController {

    /**
     * 注册接口
     */
    @Resource
    ImpUserService userServiceImp;
    @Value("${regular.username}")
    String usernameRegular;
    @Value("${regular.password}")
    String passwordRegular;
    @Value("${regular.phone}")
    String phoneRegular;
    @PostMapping("/register")
    public ResrponesUser register(@Valid @RequestBody Userpe userpe){
        System.out.print(userpe);
        //验证用户名是否合法，必须是以字母开头，只能包含字母数字下划线和减号，4到16位
        if(!userpe.getUsername().matches(usernameRegular)){
            return new ResrponesUser(500,"Username不符合格式,注册失败",false);
        }
        //密码强度正则，密码至少包含 数字和英文，长度6-20
        if(!userpe.getPassword().matches(passwordRegular)){
            return new ResrponesUser(500,"Password不符合格式,注册失败",false);
        }

        if(userpe.getPhone().matches(phoneRegular)){
            return new ResrponesUser(500,"Phone不符合格式,注册失败",false);
        }
        if(userServiceImp.select(userpe.getUsername())!=null){
            return new ResrponesUser(500,"用户名已被创建",false);
        }
        if(userServiceImp.select(userpe.getPhone())!=null){
            return new ResrponesUser(500,"手机号已被创建",false);
        }
        int count=userServiceImp.add(userpe.getUsername(),userpe.getPassword(),userpe.getPhone());

        if(count==1){
            return new ResrponesUser(200,"注册成功",true);
        }else {
            return new ResrponesUser(500,"未知错误",false);
        }
    }
    /**
     * 登录接口
     */
    @PostMapping("/login")
    public ResrponesUser login(@RequestBody UserLogin userLogin){
        System.out.print(userLogin);
//        !userLogin.getUsername().matches(usernameRegular)&&
        System.out.print(userLogin.getUsername().matches(phoneRegular));
//        if(!userLogin.getUsername().matches(usernameRegular)&&!userLogin.getUsername().matches(phoneRegular)){
//            return new ResrponesUser(500,"用户名格式错误，登陆失败",false);
//            }

        if(!userLogin.getPassword().matches(passwordRegular)){
            return new ResrponesUser(500,"密码错误，登陆失败",false);
        }
        String password = userServiceImp.select(userLogin.getUsername());

        if(password.equals(userLogin.getPassword())){
            return new ResrponesUser(200,"登录成功",true);
        }else {
            return  new ResrponesUser(500,"密码错误,登录失败",false);
        }
    }
}

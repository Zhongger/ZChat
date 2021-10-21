package com.zhongger.zchat.controller;

import com.zhongger.zchat.PO.UserDelete;
import com.zhongger.zchat.PO.UserRevise;
import com.zhongger.zchat.VO.ResrponesUser;
import com.zhongger.zchat.PO.UserLogin;
import com.zhongger.zchat.PO.UserRegister;
import com.zhongger.zchat.service.ContactpersonService;
import com.zhongger.zchat.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * @author zhongmingyi
 * @date 2021/10/11 11:50 上午
 */
@RestController
@PropertySource(value = "classpath:commonconfiguration/regular.properties")
public class UserController {

    /**
     * 注册接口
     * 雷立 2021/10/12
     */
    @Resource
    UserService UserService;
    @Resource
    ContactpersonService contactpersonService;
    @Value("${regular.username}")
    String usernameRegular;
    @Value("${regular.password}")
    String passwordRegular;
    @Value("${regular.phone}")
    String phoneRegular;
    @PostMapping("/register")
    public ResrponesUser register( @RequestBody UserRegister userRegister){
        //验证用户名是否合法，必须是以字母开头，只能包含字母数字下划线和减号，4到16位
        if(!userRegister.getUserName().matches(usernameRegular)){
            return new ResrponesUser(500,"Username不符合格式,注册失败",false);
        }
        //密码强度正则，密码至少包含 数字和英文，长度6-20
        if(!userRegister.getPassword().matches(passwordRegular)){
            return new ResrponesUser(500,"Password不符合格式,注册失败",false);
        }

        if(!userRegister.getPhone().matches(phoneRegular)){
            return new ResrponesUser(500,"Phone不符合格式,注册失败",false);
        }
        if(UserService.select(userRegister.getUserName())!=null){
            return new ResrponesUser(500,"用户名已被创建",false);
        }
        if(UserService.select(userRegister.getPhone())!=null){
            return new ResrponesUser(500,"手机号已被创建",false);
        }
        int count=UserService.add(userRegister.getUserName(),userRegister.getPassword(),userRegister.getPhone());

        if(count==1){
            return new ResrponesUser(200,"注册成功",true);
        }else {
            return new ResrponesUser(500,"未知错误",false);
        }
    }
    /**
     * 登录接口
     * 雷立 2021/10/13
     */
    @PostMapping("/login")
    public ResrponesUser login(@RequestBody UserLogin userLogin, HttpSession session, HttpServletRequest request, HttpServletResponse response){

//        !userLogin.getUsername().matches(usernameRegular)&&
//        if(!userLogin.getUsername().matches(usernameRegular)&&!userLogin.getUsername().matches(phoneRegular)){
//            return new ResrponesUser(500,"用户名格式错误，登陆失败",false);
//            }

        if(!userLogin.getPassword().matches(passwordRegular)){
            return new ResrponesUser(500,"密码错误，登陆失败",false);
        }
        String password = UserService.select(userLogin.getUsername());

        if(password!=null&&password.equals(userLogin.getPassword())){
            // 将登录用户信息保存到session中
            session.setAttribute("user_session", userLogin);
            // 保存cookie，实现自动登录
            Cookie cookie_username = new Cookie("cookie_username", userLogin.getUsername());
            // 设置cookie的持久化时间，30天
            cookie_username.setMaxAge(30 * 24 * 60 * 60);
            // 设置为当前项目下都携带这个cookie
            cookie_username.setPath(request.getContextPath());
            // 向客户端发送cookie
            response.addCookie(cookie_username);

            return new ResrponesUser(200,"登录成功",true);
        }else {
            return  new ResrponesUser(500,"密码错误,登录失败",false);
        }

    }
    /**
     * 清除用户接口
     * 雷立 2021/10/15
     */
    @PostMapping("/deleteuser")
    public ResrponesUser deletuser(@RequestBody UserDelete userDelete){
        String password = UserService.select(userDelete.getUsername());
       if(password!=null&&password.equals(userDelete.getPassword())){
           //先清除联系人表数据
           contactpersonService.deleteforuserid(userDelete.getUsername());
           UserService.delete(userDelete.getUsername());

           return  new ResrponesUser(500,"清除用户成功",true);
       }else{
           return  new ResrponesUser(500,"密码错误,删除失败",false);
       }
    }
    /**
     * 修改信息接口
     * 雷立 2020/10/18
     */
    @PostMapping("/revise")
    public ResrponesUser revise(@RequestBody UserRevise userRevise ,HttpSession session){
        System.out.println(userRevise.getUsername().trim()=="");
        if(userRevise.getUsername()!=null&&userRevise.getUsername().trim()==""){
            userRevise.setUsername(null);
        }
        if(userRevise.getPhone()!=null&&userRevise.getPhone().trim()==""){
            userRevise.setPhone(null);
        }
        if(session.getAttribute("user_session")==null){
            return new ResrponesUser(500,"用户未登录",false);
        }
//        查询是否已经存在该修改的用户名或手机号
        if(userRevise.getUsername()!=null){
            String password = UserService.select(userRevise.getUsername());
            if (password!=null){
                return new ResrponesUser(500,"该用户名已存在",false);
            }
        }
        if(userRevise.getPhone()!=null){
            String password = UserService.select(userRevise.getPhone());
            if (password!=null){
                return new ResrponesUser(500,"该手机号已存在",false);
            }
        }
        UserLogin userLogin=(UserLogin) session.getAttribute("user_session");
        if(userLogin.getUsername().matches(usernameRegular)){
            userRevise.setOld_username(userLogin.getUsername());
        }else{
            userRevise.setOld_phone(userLogin.getUsername());
        }
        UserService.update(userRevise);
        return new ResrponesUser(200,"修改信息成功",true);
    }

    /**
     * 注销登录接口
     * 2021/10/19
     */
    @PostMapping("/logout")
    public ResrponesUser logout(HttpSession session){
        session.invalidate();
        return new ResrponesUser(200,"注销成功",true);
    }
}

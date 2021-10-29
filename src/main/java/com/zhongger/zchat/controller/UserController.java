package com.zhongger.zchat.controller;

import com.zhongger.zchat.DTO.FileSave;
import com.zhongger.zchat.DTO.UserInfoData;
import com.zhongger.zchat.VO.UserInfo;
import com.zhongger.zchat.PO.UserDelete;
import com.zhongger.zchat.PO.UserRevise;
import com.zhongger.zchat.VO.ResrponesUser;
import com.zhongger.zchat.PO.UserLogin;
import com.zhongger.zchat.PO.UserRegister;
import com.zhongger.zchat.entity.UserAvatar;
import com.zhongger.zchat.entity.Userforleili;
import com.zhongger.zchat.service.ContactpersonService;
import com.zhongger.zchat.service.UserAvatarService;
import com.zhongger.zchat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.HttpCookie;


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
    @Autowired
    FileSave fileSave;
    @Autowired
    UserAvatarService userAvatarService;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
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
            //生成头像表数据
            UserAvatar userAvatar=new UserAvatar();
            userAvatar.setUser_id(UserService.selectAll(userRegister.getUserName()).getUserId());
            userAvatarService.insert(userAvatar);
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
    public ResrponesUser login(@RequestBody UserLogin userLogin,  HttpServletRequest request, HttpServletResponse response){

//        !userLogin.getUsername().matches(usernameRegular)&&
//        if(!userLogin.getUsername().matches(usernameRegular)&&!userLogin.getUsername().matches(phoneRegular)){
//            return new ResrponesUser(500,"用户名格式错误，登陆失败",false);
//            }

        if(!userLogin.getPassword().matches(passwordRegular)){
            return new ResrponesUser(500,"密码错误，登陆失败",false);
        }
        String password = UserService.select(userLogin.getUsername());

        if(password!=null&&password.equals(userLogin.getPassword())){
            // 将登录用户信息保存到redis中
            stringRedisTemplate.opsForHash().put("user",userLogin.getUsername(),userLogin.getPassword());

            // 保存cookie，实现自动登录
            Cookie cookie_username = new Cookie("cookie_username", userLogin.getUsername());
            // 设置cookie的持久化时间，30天
            cookie_username.setMaxAge(30 * 24 * 60 * 60);
            // 设置为当前项目下都携带这个cookie
            cookie_username.setPath(request.getContextPath());
            // 向客户端发送cookie
            response.addCookie(cookie_username);
            //将登录状态置1
            Userforleili userforleili=new Userforleili();
            userforleili.setUserName(userLogin.getUsername());
            userforleili.setStaus(1);
            UserService.updateforstaus(userforleili);
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
    public ResrponesUser deletuser(@RequestBody UserDelete userDelete, @CookieValue(value = "cookie_username")String usernames){
        String password = UserService.select(userDelete.getUsername());
       if(password!=null&&password.equals(userDelete.getPassword())){
           //先清除联系人表数据
           contactpersonService.deleteforuserid(userDelete.getUsername());
           UserService.delete(userDelete.getUsername());
           stringRedisTemplate.opsForHash().delete("user",userDelete.getUsername());
            //清除联系人头像表
           UserAvatar userAvatar=new UserAvatar();
           userAvatar.setUser_id(UserService.selectAll(usernames).getUserId());
           userAvatarService.delete(userAvatar);
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
    public ResrponesUser revise(@RequestBody UserRevise userRevise ,@CookieValue(value = "cookie_username")String usernames){
        System.out.println(userRevise.getUsername().trim()=="");
        if(userRevise.getUsername()!=null&&userRevise.getUsername().trim()==""){
            userRevise.setUsername(null);
        }
        if(userRevise.getPhone()!=null&&userRevise.getPhone().trim()==""){
            userRevise.setPhone(null);
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

        if(usernames.matches(usernameRegular)){
            userRevise.setOld_username(usernames);
        }else{
            userRevise.setOld_phone(usernames);
        }
        UserService.update(userRevise);
        return new ResrponesUser(200,"修改信息成功",true);
    }

    /**
     * 注销登录接口
     * 2021/10/19
     */
    @PostMapping("/logout")
    public ResrponesUser logout(@CookieValue(value = "cookie_username") String username){

        stringRedisTemplate.opsForHash().delete("user",username);
        //将用户登录状态置0
        Userforleili userforleili=new Userforleili();
        userforleili.setUserName(username);
        userforleili.setStaus(0);
        UserService.updateforstaus(userforleili);
        return new ResrponesUser(200,"注销成功",true);
    }
    /**
     * 用户信息接口
     * 2021/10/27
     */
    @PostMapping("/user")
    public UserInfo user(@CookieValue(value = "cookie_username")String usernames) throws IOException {

        Userforleili userforleili=new Userforleili();
        if(usernames.matches(usernameRegular)){
            userforleili.setUserName(usernames);
        }else{
            userforleili.setPhone(usernames);
        }
        UserInfoData userInfoData=UserService.selectuser(userforleili);
        UserInfo userInfo=new UserInfo();
        //未上传头像
        if(userInfoData==null){
            Userforleili info=UserService.selectAll(usernames);
            userInfo.setPhone(info.getPhone());
            userInfo.setUserName(info.getUserName());
            userInfo.setUserId(info.getUserId());
            return userInfo;
        }
        //如果本地有这个头像则不再重新生成文件
        File file =new File(userInfoData.getImage_name());
        if(file.exists()){
            userInfo.setSrc(file.getAbsolutePath());
        }else{
            userInfo.setSrc(fileSave.SaveFile(userInfoData.getImage_name(), userInfoData.getData()));

        }
        userInfo.setPhone(userInfoData.getPhone());
        userInfo.setUserName(userInfoData.getUserName());
        userInfo.setUserId(userInfoData.getUserId());
        return userInfo;

    }
}

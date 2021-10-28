package com.zhongger.zchat;

import com.sun.org.apache.bcel.internal.Const;
import com.zhongger.zchat.PO.UserLogin;
import com.zhongger.zchat.VO.ResrponesUser;
import com.zhongger.zchat.entity.Userforleili;
import com.zhongger.zchat.mapper.UserMapper;
import com.zhongger.zchat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * 未登录拦截器
 * 雷立 2021/10/18
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setContentType("application/json; charset=utf-8");
        // 获得cookie
        Cookie[] cookies = request.getCookies();
        // 没有cookie信息，则重定向到登录界面
        if (null == cookies) {
            PrintWriter out = response.getWriter().append(new ResrponesUser(500,"用户未登录，请先登录",false).toString().substring(13));
            return false;
        }
        // 定义cookie_username，用户的一些登录信息，例如：用户名，密码等
        String cookie_username = null;
        // 获取cookie里面的一些用户信息
        for (Cookie item : cookies) {
            if ("cookie_username".equals(item.getName())) {
                cookie_username = item.getValue();
                break;
            }
        }
        // 如果cookie里面没有包含用户的一些登录信息，则重定向到登录界面
        if (StringUtils.isEmpty(cookie_username)) {
            PrintWriter out = response.getWriter().append(new ResrponesUser(500,"用户未登录，请先登录",false).toString().substring(13));
            return false;
        }
        //判断redies中是否有相关cookie
        if (stringRedisTemplate.opsForHash().hasKey("user",cookie_username)) {
            // 根据用户登录账号获取数据库中的用户信息
            String password = userService.select(cookie_username);
            if (password!=null&&password.equals(stringRedisTemplate.opsForHash().get("user",cookie_username))){
                return true;
            }else {

                PrintWriter out = response.getWriter().append(new ResrponesUser(500,"用户未登录，请先登录",false).toString().substring(13));
                return  false;
            }

        }
        PrintWriter out = response.getWriter().append(new ResrponesUser(500,"用户未登录，请先登录",false).toString().substring(13));
        return false;
    }
}

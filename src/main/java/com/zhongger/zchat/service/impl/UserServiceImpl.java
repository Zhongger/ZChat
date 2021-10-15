package com.zhongger.zchat.service.impl;

import com.zhongger.zchat.PO.UserDelete;
import com.zhongger.zchat.mapper.UserMapper;
import com.zhongger.zchat.entity.Userforleili;
import com.zhongger.zchat.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service ( "userService" )
@PropertySource(value = "classpath:commonconfiguration/regular.properties")
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;
    @Override
    public int add(String username, String password, String phone) {
        Userforleili user=new Userforleili(username,password,phone);

//        SqlSessionFactory sqlSessionFactory ;
//        String resource="mybatis-config1.xml";
//        InputStream inputStream=null;
//        try {
//            inputStream = Resources.getResourceAsStream(resource);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream);
//        SqlSession session=sqlSessionFactory.openSession();
//        UserMapper userMapper =session.getMapper(UserMapper.class);

        int count= userMapper.insert(user);

        return count;
    }
    @Value("${regular.username}")
    String usernameRegular;
    @Override
    public String select(String username) {
        String password;
        Userforleili userforleili=new Userforleili();
        if(username.matches(usernameRegular)){
            userforleili.setUserName(username);

        }else{
            userforleili.setPhone(username);
        }
        password=userMapper.select(userforleili);
        return password;
    }

    @Override
    public Integer delete(String username) {
        Integer flag;
        Userforleili userforleili=new Userforleili();
        if(username.matches(usernameRegular)){
            userforleili.setUserName(username);

        }else{
            userforleili.setPhone(username);
        }
        flag=userMapper.delete(userforleili);
        return flag;
    }
}

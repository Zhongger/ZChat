package com.zhongger.zchat.service.Impl;

import com.zhongger.zchat.mapper.UserMapper;
import com.zhongger.zchat.pojo.User;
import com.zhongger.zchat.service.ImpUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@PropertySource(value = "classpath:commonconfiguration/regular.properties")
public class UserServiceImp implements ImpUserService {
    @Resource
    UserMapper userMapper;
    @Override
    public int add(String username, String password, String phone) {
        User user=new User(username,password,phone);

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
        if(username.matches(usernameRegular)){
             password=userMapper.selectForUsername(username);
        }else{
             password=userMapper.selectForUphone(username);

        }

        return password;
    }
}

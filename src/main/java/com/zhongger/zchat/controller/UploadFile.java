package com.zhongger.zchat.controller;


import com.baomidou.mybatisplus.core.toolkit.ClassUtils;
import com.zhongger.zchat.DTO.FileUtil;
import com.zhongger.zchat.PO.UserLogin;
import com.zhongger.zchat.VO.ResrponesUser;
import com.zhongger.zchat.entity.UserAvatar;
import com.zhongger.zchat.service.UserAvatarService;
import com.zhongger.zchat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

@RestController
public class UploadFile {

    @Autowired
    UserService userService;
    @Autowired
    UserAvatarService userAvatarService;
    @PostMapping("/upload")
    public ResrponesUser upload(@RequestBody MultipartFile file, HttpSession session) throws IOException, SQLException {
        UserLogin userLogin=(UserLogin)session.getAttribute("user_session");
        Integer user_id=userService.selectAll(userLogin.getUsername()).getUserId();
        byte[] flies = file.getBytes();
        UserAvatar userAvatar=new UserAvatar();
        userAvatar.setFile(flies);
        File filepath=new File("");
        String path=filepath.getAbsolutePath()+"\\ZChat\\src\\main\\java\\com\\zhongger\\zchat\\photo\\";
        userAvatar.setImage_name(path+file.getOriginalFilename());
        userAvatar.setUser_id(user_id);
        if(userAvatarService.select(user_id)==null){
            userAvatarService.insert(userAvatar);
        }else{
            userAvatarService.updataforuser(userAvatar);
        }

        return new ResrponesUser(200,"上传成功",true);
    }
}

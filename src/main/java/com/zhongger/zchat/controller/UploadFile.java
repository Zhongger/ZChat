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
import org.springframework.web.bind.annotation.CookieValue;
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

    /**
     * 上传头像接口
     * 雷立 2021/10/27
     */
    @PostMapping("/upload")
    public ResrponesUser upload(@RequestBody MultipartFile file, @CookieValue(value = "cookie_username")String usernames) throws IOException {

        Integer user_id=userService.selectAll(usernames).getUserId();
        byte[] flies = file.getBytes();
        if(flies.length>=4194304){
            return new ResrponesUser(500,"附件太大，上传失败",false);
        }
        UserAvatar userAvatar=new UserAvatar();
        userAvatar.setFile(flies);
        File filepath=new File("");
        String path=filepath.getAbsolutePath()+"\\src\\main\\java\\com\\zhongger\\zchat\\photo\\";
        userAvatar.setImage_name(path+file.getOriginalFilename());
        userAvatar.setUser_id(user_id);
        UserAvatar userAvatar1=userAvatarService.select(user_id);

            if(userAvatar1==null){
                userAvatarService.insert(userAvatar);
            }else{
                File dele=new File( userAvatar1.getImage_name());
                if(dele.exists()){
                    dele.delete();
                }
                userAvatarService.updataforuser(userAvatar);
            }


        return new ResrponesUser(200,"上传成功",true);
    }
}

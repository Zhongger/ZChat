package com.zhongger.zchat.DTO;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class FileUtil {



    public String getFileName(String name){
        name=name.substring(name.lastIndexOf("."));
        return UUID.randomUUID().toString()+name;
    }

    public  String upload(MultipartFile file,String path,String filename) throws IOException {
        String newFileName=getFileName(filename);
        //生成新的文件名
        String newpath=path+"\\"+newFileName;
        File files=new File(newpath);
        if(!files.getParentFile().exists()){
            System.out.print("创建文件夹");
            files.getParentFile().mkdir();
        }
        file.transferTo(files);
        return newFileName;
    }
}

package com.zhongger.zchat.DTO;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
@Component
public class FileSave {
    public String SaveFile(String filename,byte[] data) throws IOException {
        File filefather=new File(filename);
        if(!filefather.getParentFile().exists()){
            filefather.getParentFile().mkdirs();
        }
        if(data!=null){
            String filepath=filename;
            File file=new File(filepath);
            FileOutputStream fos=new FileOutputStream(file);
            fos.write(data);
            fos.flush();
            fos.close();
        }
        return filename;
    }
}

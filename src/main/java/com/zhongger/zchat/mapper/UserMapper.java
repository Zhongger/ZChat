package com.zhongger.zchat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhongger.zchat.PO.UserDelete;
import com.zhongger.zchat.PO.UserLogin;
import com.zhongger.zchat.entity.Userforleili;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserMapper extends BaseMapper<Userforleili> {
    int insert(Userforleili record);

    List<Userforleili> selectAll();
    String select(Userforleili userforleili);
    Integer delete(Userforleili userforleili);

}
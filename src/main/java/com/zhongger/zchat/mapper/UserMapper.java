package com.zhongger.zchat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhongger.zchat.entity.Userforleili;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserMapper extends BaseMapper<Userforleili> {
    int insert(Userforleili record);

    List<Userforleili> selectAll();
    String selectForUsername(String userName);
    String selectForUphone(String phone);
}
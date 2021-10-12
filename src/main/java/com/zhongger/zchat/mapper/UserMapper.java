package com.zhongger.zchat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhongger.zchat.entity.User;

import java.util.List;

/**
 * @author zhongmingyi
 * @date 2021/10/12 9:16 下午
 */
public interface UserMapper extends BaseMapper<User> {
    int batchInsertUser(List<User> userList);
}

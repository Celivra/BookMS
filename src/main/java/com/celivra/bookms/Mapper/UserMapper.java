package com.celivra.bookms.Mapper;

import com.celivra.bookms.Entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    //根据username获取用户信息
    User findByUsername(String username);
    //根据用户名更新用户信息
    boolean updateInfo(String username, String phone, String email);
    //修改用户密码
    boolean updatePassword(String username, String password);
}

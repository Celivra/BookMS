package com.celivra.bookms.Mapper;

import com.celivra.bookms.Entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findByUsername(String username);
    boolean updateInfo(String username, String phone, String email);
    boolean updatePassword(String username, String password);
}

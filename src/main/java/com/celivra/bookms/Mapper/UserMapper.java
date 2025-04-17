package com.celivra.bookms.Mapper;

import com.celivra.bookms.Entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {
    //根据username获取用户信息
    @Select("select * from user where username = #{username}")
    User findByUsername(String username);

    @Select("select * from user")
    List<User> getAllUsers();

    //根据用户名更新用户信息
    @Update("update user set phone = #{phone}, email = #{email} where username = #{username}")
    boolean updateInfo(String username, String phone, String email);
    //修改用户密码
    @Update("update user set password = #{password} where username = #{username}")
    boolean updatePassword(String username, String password);
}

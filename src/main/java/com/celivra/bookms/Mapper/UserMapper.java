package com.celivra.bookms.Mapper;

import com.celivra.bookms.Entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    //根据username获取用户信息
    @Select("select * from user where username = #{username}")
    User findByUsername(String username);

    @Select("select * from user where id = #{userId}")
    User findByUserId(String userId);

    @Insert("insert into user(username, password, phone, email, power)" +
            "values(#{username}, #{password}, #{phone}, #{email}, #{power})")
    boolean addUser(User user);

    @Update("update user set username = #{username}, password = #{password},  phone = #{phone}, email = #{email}, power = #{power} where id = #{id}")
    boolean updateUser(User user);

    @Delete("delete from user where id = #{id}")
    boolean deleteUser(String id);

    @Select("select * from user")
    List<User> getAllUsers();

    //根据关键字查找用户名
    @Select("select * from user where " +
            "username like concat('%',#{target},'%') or " +
            "phone like concat('%',#{target},'%') or " +
            "email like concat('%',#{target},'%')")
    List<User> getAllUsersByTarget(String target);

}
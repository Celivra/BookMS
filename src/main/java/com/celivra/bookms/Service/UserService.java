package com.celivra.bookms.Service;

import com.celivra.bookms.Entity.User;
import com.celivra.bookms.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    //添加用户
    //1：成功  2:用户已存在  0:数据库错误
    public int addUser(User user) {
        User CheckUser = userMapper.findByUsername(user.getUsername());
        if(CheckUser != null){
            return 2;
        }
        if(userMapper.addUser(user)){
            return 1;
        }else{
            return 0;
        }
    }
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
    public boolean updateInfo(String username, String newPhone, String newEmail) {
        return userMapper.updateInfo(username, newPhone, newEmail);
    }
    public boolean updatePassword(String username, String newPassword) {
        return userMapper.updatePassword(username, newPassword);
    }
}

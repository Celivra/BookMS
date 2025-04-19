package com.celivra.bookms.Service;

import com.celivra.bookms.Entity.User;
import com.celivra.bookms.Mapper.BorrowMapper;
import com.celivra.bookms.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    private BorrowMapper borrowMapper;

    //添加用户
    //1：成功  2:用户已存在  0:数据库错误 3:用户名格式不正确
    public int addUser(User user) {
        String username = user.getUsername();

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
    public boolean updateUser(User user) {
        return userMapper.updateUser(user);
    }
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
    public User findByUserId(String userId) {
        return userMapper.findByUserId(userId);
    }
    public boolean updateInfo(String username, String newPhone, String newEmail) {
        return userMapper.updateInfo(username, newPhone, newEmail);
    }
    public boolean updatePassword(String username, String newPassword) {
        return userMapper.updatePassword(username, newPassword);
    }
    public boolean deleteUser(String userId) {
        borrowMapper.deleteBorrowByUser(userId);
        return userMapper.deleteUser(userId);
    }
}

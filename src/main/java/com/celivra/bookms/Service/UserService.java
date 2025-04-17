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

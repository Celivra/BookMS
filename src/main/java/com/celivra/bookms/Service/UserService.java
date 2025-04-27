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
    private UserMapper userMapper;
    @Autowired
    private BorrowMapper borrowMapper;

    //添加用户
    //1：成功  2:用户已存在  0:数据库错误 3:用户名格式不正确
    public int addUser(User user) {
        User CheckUser = userMapper.findByUsername(user.getUsername());
        if(CheckUser != null) return 2;
        return userMapper.addUser(user)?1:0;
    }
    //更新用户
    public boolean updateUser(User user) {
        return userMapper.updateUser(user);
    }
    //获取所有用户信息
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }
    //根据用户名查找
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
    //根据id查找
    public User findByUserId(String userId) {
        return userMapper.findByUserId(userId);
    }
    //删除用户
    public boolean deleteUser(String userId) {
        borrowMapper.deleteBorrowByUser(userId);
        return userMapper.deleteUser(userId);
    }

    public List<User> searchUser(String keyword) {
        return userMapper.getAllUsersByTarget(keyword);
    }
}

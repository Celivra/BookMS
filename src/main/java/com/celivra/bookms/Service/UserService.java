package com.celivra.bookms.Service;

import com.celivra.bookms.Entity.User;
import com.celivra.bookms.Mapper.BorrowMapper;
import com.celivra.bookms.Mapper.TicketMapper;
import com.celivra.bookms.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    /*==================实例化Mapper===================*/
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BorrowMapper borrowMapper;
    @Autowired
    private TicketMapper ticketMapper;
    /*===================实例化结束===================*/

    //添加用户
    //1：成功  2:用户已存在  0:数据库错误 3:用户名格式不正确
    public int addUser(User user) {

        /*==============================判断用户是否存在====================================*/
        User CheckUser = userMapper.getByUsername(user.getUsername());
        if(CheckUser != null) return 2;
        /*=================================判断结束=======================================*/

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
    public User getByUsername(String username) {
        return userMapper.getByUsername(username);
    }
    //根据id查找
    public User getByUserId(String userId) {
        return userMapper.getByUserId(userId);
    }
    //删除用户
    public boolean deleteUser(String userId) {

        if(userMapper.getByUserId(userId) == null) return false;

        /*=======================首先删除关于这个用户在其他表的数据============================*/
        borrowMapper.deleteBorrowByUser(userId);
        ticketMapper.deleteTicket(userId);
        /*==============================然后可删该用户=====================================*/
        return userMapper.deleteUser(userId);
    }

    public List<User> searchUser(String keyword) {
        return userMapper.getAllUsersByTarget(keyword);
    }
}

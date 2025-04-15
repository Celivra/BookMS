package com.celivra.bookms.Mapper;

import com.celivra.bookms.Entity.Borrow;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BorrowMapper {
    //获得某个用户借阅的所有信息
    List<Borrow> getAllUserBorrows(String userid);
    //添加借阅记录
    boolean insertBorrow(Borrow borrow);
}

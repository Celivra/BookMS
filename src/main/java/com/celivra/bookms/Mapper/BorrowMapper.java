package com.celivra.bookms.Mapper;

import com.celivra.bookms.Entity.Borrow;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BorrowMapper {
    //获得某个用户借阅的所有信息
    @Select("select * from borrow")
    List<Borrow> getAllUserBorrows(String userid);
    //添加借阅记录
    @Insert("insert into borrow " +
            "values(#{userid}, #{bookid}, #{borrowDate}, #{returnDate})")
    boolean insertBorrow(Borrow borrow);
    //获取某个用户借阅某本书的数据
    @Select("select * from borrow where userid = #{userid} and bookid = #{bookid}")
    Borrow getBorrowByUserAndBook(String userid, String bookid);
}

package com.celivra.bookms.Mapper;

import com.celivra.bookms.Entity.Book;
import com.celivra.bookms.Entity.Borrow;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
    @Select("select * from borrow where userid = #{userid} and bookid = #{bookid} and returndate is null")
    Borrow getBorrowByUserAndBook(String userid, String bookid);

    //修改归还日期
    @Update("update borrow set returndate = #{returnDate} where userid = #{userid} and bookid = #{bookid} and returndate is null")
    boolean updateBorrow(Borrow borrow);

    //获某个用户借阅的所有的书
    @Select("select * from book join borrow on id = borrow.bookid where borrow.userid = #{userid} and borrow.returndate is null")
    List<Book> getUserBorrowedBooks(String userid);


}

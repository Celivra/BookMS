package com.celivra.bookms.Service;

import com.celivra.bookms.Entity.*;
import com.celivra.bookms.Mapper.BookMapper;
import com.celivra.bookms.Mapper.BorrowMapper;
import com.celivra.bookms.Mapper.UserMapper;
import com.celivra.bookms.Util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BorrowService {

    /*==================实例化Mapper===================*/
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BorrowMapper borrowMapper;
    @Autowired
    private UserMapper userMapper;
    /*===================实例化结束===================*/

    //根据用户获取借阅信息
    public List<Book> getUserBorrowedBooks(String userId) {
        return borrowMapper.getUserBorrowedBooks(userId);
    }

    //归还图书
    public boolean returnBook(String bookid, String userid) {

        /*==================获取借阅记录并将归还时间设为当前时间,更新借阅记录===================*/
        Borrow borrow = borrowMapper.getBorrowByUserAndBook(userid, bookid);
        if(borrow == null) return false;
        borrow.setReturnDate(DateUtil.getCurrentDate());
        if(!borrowMapper.updateBorrow(borrow)) return false;
        /*===============================更新借阅记录结束=================================*/


        /*=============================将归还的书籍的数量+1================================*/
        Book book = bookMapper.getBookById(bookid);
        book.setBookNumber(book.getBookNumber() + 1);
        bookMapper.updateBookInfo(book);
        /*===============================更新书籍数量结束=================================*/
        return true;
    }

    //添加借阅记录 0:借阅失败 1:借阅成功 2:已经借阅过
    public int borrowBook(String bookid, User user) {
        /*===========================判断是否已经借过当前的图书了============================*/
        Book book = bookMapper.getBookById(bookid);
        if(book == null) return 0;
        Borrow CheckBorrow= borrowMapper.getBorrowByUserAndBook(user.getId().toString(), bookid);
        if (CheckBorrow != null) return 2;
        /*===================================判断结束====================================*/


        /*==========================添加借阅记录并将借阅的书籍数量-1==========================*/
        Borrow borrow = new Borrow(user.getId(), book.getId(), DateUtil.getCurrentDate(), null);
        if(!borrowMapper.insertBorrow(borrow)) return 0;

        book.setBookNumber(book.getBookNumber() - 1);
        bookMapper.updateBookInfo(book);
        /*================================添加借阅记录结束=================================*/
        return 1;
    }

    /**
     因为数据库借阅表里只记录了用户id和书籍id
     该函数根据借阅记录转换为可读的借阅信息
     **/
    //列出这个用户的所有借阅记录
    public List<BorrowInfo> getAllUserBorrows(String userid) {

        /*=================获取用户所有的借阅记录，根据每一条借阅记录创建bInfo===================*/
        List<Borrow> borrows = borrowMapper.getAllUserBorrows(userid);
        List<BorrowInfo> borrowInfos = new ArrayList<>();
        for (Borrow borrow : borrows) {
            Book book = bookMapper.getBookById(borrow.getBookid().toString());
            borrowInfos.add(new BorrowInfo(book.getBookName(), book.getAuthor(), borrow.getBorrowDate(), borrow.getReturnDate()));
        }
        /*====================================创建结束====================================*/

        Collections.reverse(borrowInfos);
        return borrowInfos;
    }
    //管理员
    public List<BorrowInfoAdmin> getAllBorrows() {

        /*================================与上一个函数一致==================================*/
        List<BorrowInfoAdmin> borrowInfos = new ArrayList<>();
        List<Borrow> borrows = borrowMapper.getAllBorrows();
        for (Borrow borrow : borrows) {
            User user = userMapper.getByUserId(borrow.getUserid().toString());
            Book book = bookMapper.getBookById(borrow.getBookid().toString());
            borrowInfos.add(new BorrowInfoAdmin(user.getUsername(), book.getBookName(), book.getAuthor(), borrow.getBorrowDate(), borrow.getReturnDate()));
        }
        /*=====================================结束======================================*/
        Collections.reverse(borrowInfos);
        return borrowInfos;
    }
}

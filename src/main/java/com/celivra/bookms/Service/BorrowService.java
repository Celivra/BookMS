package com.celivra.bookms.Service;

import com.celivra.bookms.Entity.Book;
import com.celivra.bookms.Entity.Borrow;
import com.celivra.bookms.Entity.BorrowInfo;
import com.celivra.bookms.Entity.User;
import com.celivra.bookms.Mapper.BookMapper;
import com.celivra.bookms.Mapper.BorrowMapper;
import com.celivra.bookms.Util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BorrowService {
    @Autowired
    BookMapper bookMapper;
    @Autowired
    BorrowMapper borrowMapper;

    public List<Book> getUserBorrowedBooks(String userId) {
        return borrowMapper.getUserBorrowedBooks(userId);
    }

    //根据用户id删除记录
    //当删除用户时需要的函数
    public boolean deleteBorrowByUser(String userId) {
        borrowMapper.deleteBorrowByUser(userId);
        return true;
    }


    //根据图书id删除记录
    //当删除图书的时候需要的函数
    public boolean deleteBorrowByBook(String bookId) {
        borrowMapper.deleteBorrowByBook(bookId);
        return true;
    }


    //归还图书
    public boolean returnBook(String bookid, String userid) {
        //获取当前借阅记录
        Borrow borrow = borrowMapper.getBorrowByUserAndBook(userid, bookid);
        //设置返回日期为当前日期
        borrow.setReturnDate(DateUtil.getCurrentDate());
        //判断是否更新成功
        if(borrowMapper.updateBorrow(borrow)){
            //获取当前要还的书籍信息
            Book book = bookMapper.findBookById(bookid);
            //修改书籍数量
            book.setBookNumber(book.getBookNumber() + 1);
            //更新书籍
            bookMapper.updateBookInfo(book);
            return true;
        }
        return false;
    }

    //添加借阅记录
    public boolean borrowBook(String bookid, User user) {
        //根据bookid获取选中的书籍信息
        Book book = bookMapper.findBookById(bookid);
        //如果已经借过这本书
        Borrow CheckBorrow= borrowMapper.getBorrowByUserAndBook(user.getId().toString(), bookid);
        if (CheckBorrow != null) {
            return false;
        }

        //建立借阅记录
        Borrow borrow = new Borrow(user.getId(), book.getId(), DateUtil.getCurrentDate(), null);
        //将记录插入到数据库里
        if(borrowMapper.insertBorrow(borrow)){
            book.setBookNumber(book.getBookNumber() - 1);
            bookMapper.updateBookInfo(book);
            return true;
        }
        return false;
    }

    /**
     * 因为数据库借阅表里只记录了用户id和书籍id
     * 该函数根据借阅记录转换为可读的借阅信息
     **/
    //列出这个用户的所有借阅记录
    public List<BorrowInfo> getAllUserBorrows(String userid) {
        //获取所有借阅记录
        List<Borrow> borrows = borrowMapper.getAllUserBorrows(userid);
        //建立借阅信息线性表
        List<BorrowInfo> borrowInfos = new ArrayList<>();
        //枚举每一个borrow记录
        for (Borrow borrow : borrows) {
            //获取被借阅的书籍的信息
            Book book = bookMapper.findBookById(borrow.getBookid().toString());
            //将信息添加到信息表
            borrowInfos.add(new BorrowInfo(book.getBookName(), book.getAuthor(), borrow.getBorrowDate(), borrow.getReturnDate()));
        }
        return borrowInfos;
    }
}

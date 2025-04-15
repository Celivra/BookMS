package com.celivra.bookms.Service;

import com.celivra.bookms.Entity.Book;
import com.celivra.bookms.Entity.Borrow;
import com.celivra.bookms.Entity.User;
import com.celivra.bookms.Mapper.BookMapper;
import com.celivra.bookms.Mapper.BorrowMapper;
import com.celivra.bookms.Util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BorrowService {
    @Autowired
    BookMapper bookMapper;
    @Autowired
    BorrowMapper borrowMapper;

    public boolean borrowBook(String bookid, User user) {
        //根据bookid获取选中的书籍信息
        Book book = bookMapper.findBookById(bookid);
        //如果已经借过这本书
        //Borrow CheckBorrow= borrowMapper.getBorrowByUserAndBook(user.getId().toString(), bookid);

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
}

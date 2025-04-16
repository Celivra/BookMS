package com.celivra.bookms.Service;

import com.celivra.bookms.Entity.Book;
import com.celivra.bookms.Entity.Borrow;
import com.celivra.bookms.Entity.User;
import com.celivra.bookms.Mapper.BookMapper;
import com.celivra.bookms.Mapper.BorrowMapper;
import com.celivra.bookms.Util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.BeanNameViewResolver;

import java.util.List;

@Service
public class BorrowService {
    @Autowired
    BookService bookService;
    @Autowired
    BorrowMapper borrowMapper;

    public List<Book> getUserBorrowedBooks(String userId) {
        return borrowMapper.getUserBorrowedBooks(userId);
    }
    public boolean borrowBook(String bookid, User user) {
        //根据bookid获取选中的书籍信息
        Book book = bookService.findBookById(bookid);
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
            bookService.updateBook(book);
            return true;
        }
        return false;
    }
}

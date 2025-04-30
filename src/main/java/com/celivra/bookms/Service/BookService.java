package com.celivra.bookms.Service;

import com.celivra.bookms.Entity.Book;
import com.celivra.bookms.Mapper.BookMapper;
import com.celivra.bookms.Mapper.BorrowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BorrowMapper borrowMapper;

    //添加图书
    //1：添加成功 0:系统原因添加失败 2：因为是相同的书籍, 添加失败
    public int addBook(Book book) {
        //通过要添加的书籍的isbn从数据库里搜索。
        Book check = bookMapper.getBookByISBN(book.getISBN());
        //如果存在名字相同的书籍且isbn相同，则判定为同一本书，返回2
        if(check != null) return 2;

        //若没有找到，则正常插入
        return bookMapper.addBook(book)?1:0;
    }

    //删除图书
    public boolean deleteBook(String bookId) {
        //要把有关这本书的所有借阅记录删除
        borrowMapper.deleteBorrowByBook(bookId);
        return bookMapper.deleteBook(bookId);
    }

    //根据id查找图书
    public Book getBookById(String bookId){
        return bookMapper.getBookById(bookId);
    }

    //更新图书
    public boolean updateBook(Book book){
        return bookMapper.updateBookInfo(book);
    }

    //获取所有图书
    public List<Book> getAllBooks() {
        return bookMapper.getAllBooks();
    }
    //根据关键字查找图书
    public List<Book> getAllBookByTarget(String target) {
        return bookMapper.getBookByTarget(target);
    }
}

package com.celivra.bookms.Service;

import com.celivra.bookms.Entity.Book;
import com.celivra.bookms.Mapper.BookMapper;
import com.celivra.bookms.Mapper.BorrowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {

    /*==================实例化Mapper===================*/
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BorrowMapper borrowMapper;
    /*===================实例化结束===================*/

    //添加图书
    //1：添加成功 0:系统原因添加失败 2：因为是相同的书籍, 添加失败
    public int addBook(Book book) {
        /*==================检查是存在isbn相同的书籍===================*/
        Book check = bookMapper.getBookByISBN(book.getISBN());
        if(check != null) return 2;
        /*=========================检查结束=========================*/

        return bookMapper.addBook(book)?1:0;
    }

    //删除图书
    public boolean deleteBook(String bookId) {
        borrowMapper.deleteBorrowByBook(bookId);
        return bookMapper.deleteBook(bookId);
    }

    //根据id查找图书
    public Book getBookById(String bookId){
        return bookMapper.getBookById(bookId);
    }

    public Book getBookByISBN(String isbn){
        return bookMapper.getBookByISBN(isbn);
    }

    //更新图书
    public boolean updateBook(Book originBook, Book newbook) {
        /*=============将源书籍的信息全都修改为新书的信息================*/

        originBook.setBookName(newbook.getBookName());
        originBook.setAuthor(newbook.getAuthor());
        originBook.setBookType(newbook.getBookType());
        originBook.setPublisher(newbook.getPublisher());
        originBook.setBookNumber(newbook.getBookNumber());
        originBook.setDescription(newbook.getDescription());

        originBook.setISBN(newbook.getISBN());
        originBook.setPublishedDate(newbook.getPublishedDate());
        /*======================修改结束============================*/

        return bookMapper.updateBookInfo(originBook);
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

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
    public boolean addBook(Book book) {
        return bookMapper.addBook(book);
    }

    //删除图书
    public boolean deleteBook(String bookId) {
        //要把有关这本书的所有借阅记录删除
        borrowMapper.deleteBorrowByBook(bookId);
        return bookMapper.deleteBook(bookId);
    }

    //根据id查找图书
    public Book findBookById(String bookId){
        return bookMapper.findBookById(bookId);
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
    public List<Book> findAllBookByTarget(String target) {
        List<Book> bookByName= bookMapper.getBookByName(target);
        List<Book> bookByAuthor = bookMapper.getBookByAuthor(target);
        List<Book> bookByType = bookMapper.getBookByType(target);
        List<Book> bookByPublisher = bookMapper.getBookByPublisher(target);
        List<Book> bookByNumber = bookMapper.getBookByNumber(target);
        List<Book> bookByDesc= bookMapper.getBookByDesc(target);
        List<Book> bookByISBN = bookMapper.getBookByISBN(target);

        // 用 LinkedHashMap 保证顺序，key 是 Book 的 id
        Map<Long, Book> bookMap = new LinkedHashMap<>();

        for (Book book : bookByName) {
            bookMap.put(book.getId(), book);
        }
        for (Book book : bookByAuthor) {
            bookMap.put(book.getId(), book);
        }
        for (Book book : bookByType) {
            bookMap.put(book.getId(), book);
        }
        for (Book book : bookByPublisher) {
            bookMap.put(book.getId(), book);
        }
        for (Book book : bookByNumber) {
            bookMap.put(book.getId(), book);
        }
        for (Book book : bookByDesc) {
            bookMap.put(book.getId(), book);
        }
        for (Book book : bookByISBN) {
            bookMap.put(book.getId(), book);
        }

        // 返回不重复的书籍列表
        return new ArrayList<>(bookMap.values());
    }
}

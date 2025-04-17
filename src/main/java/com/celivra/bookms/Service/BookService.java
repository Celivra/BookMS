package com.celivra.bookms.Service;

import com.celivra.bookms.Entity.Book;
import com.celivra.bookms.Mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {

    @Autowired
    BookMapper bookMapper;

    public Book findBookByName(String bookName){
        return bookMapper.findBookByName(bookName);
    }

    public Book findBookById(String bookId){
        return bookMapper.findBookById(bookId);
    }

    public boolean updateBook(Book book){
        return bookMapper.updateBookInfo(book);
    }

    public List<Book> getAllBooks() {
        return bookMapper.getAllBooks();
    }
    public List<Book> findAllBookByTarget(String target) {
        List<Book> bookByName= bookMapper.getBookByName(target);
        List<Book> bookByAuthor = bookMapper.getBookByAuthor(target);
        List<Book> bookByType = bookMapper.getBookByType(target);
        List<Book> bookByPublisher = bookMapper.getBookByPublisher(target);
        List<Book> bookByNumber = bookMapper.getBookByNumber(target);
        List<Book> bookByDesc= bookMapper.getBookByDesc(target);

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

        // 返回不重复的书籍列表
        return new ArrayList<>(bookMap.values());
    }
}

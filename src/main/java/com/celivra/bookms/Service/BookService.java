package com.celivra.bookms.Service;

import com.celivra.bookms.Entity.Book;
import com.celivra.bookms.Mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}

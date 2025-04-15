package com.celivra.bookms.Mapper;

import com.celivra.bookms.Entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    Book findBookByName(String bookName);
    Book findBookById(String bookId);
    List<Book> getAllBooks();
}

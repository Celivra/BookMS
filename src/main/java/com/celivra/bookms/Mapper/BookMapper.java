package com.celivra.bookms.Mapper;

import com.celivra.bookms.Entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    //根据书名获得书籍信息
    Book findBookByName(String bookName);
    //根据书籍id获取书籍信息
    Book findBookById(String bookId);
    //更新书籍信息
    boolean updateBookInfo(Book book);
    //获取所有书籍的信息
    List<Book> getAllBooks();
}

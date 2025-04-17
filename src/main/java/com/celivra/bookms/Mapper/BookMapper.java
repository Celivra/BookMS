package com.celivra.bookms.Mapper;

import com.celivra.bookms.Entity.Book;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface BookMapper {
    @Insert("insert into book(bookname, author, booktype, publisher, booknumber, description) " +
            "values(#{bookName}, #{author}, #{bookType}, #{publisher}, #{bookNumber}, #{description})")
    boolean addBook(Book book);
    //根据书名获得书籍信息
    @Select("select * from book where bookname = #{bookName}")
    Book findBookByName(String bookName);
    //根据书籍id获取书籍信息
    @Select("select * from book where id=#{bookId}")
    Book findBookById(String bookId);
    //更新书籍信息
    @Update("update book set id = #{id}, bookname = #{bookName}, author = #{author}," +
            "booktype = #{bookType}, publisher = #{publisher}, booknumber = #{bookNumber}, description = #{description}" +
            "where id = #{id}")
    boolean updateBookInfo(Book book);
    //获取所有书籍的信息
    @Select("select * from book;")
    List<Book> getAllBooks();

    @Select("select * from book where bookname like concat('%', #{target}, '%')")
    List<Book> getBookByName(String target);
    @Select("select * from book where author like concat('%', #{target}, '%')")
    List<Book> getBookByAuthor(String target);
    @Select("select * from book where booktype like concat('%', #{target}, '%')")
    List<Book> getBookByType(String target);
    @Select("select * from book where publisher like concat('%', #{target}, '%')")
    List<Book> getBookByPublisher(String target);
    @Select("select * from book where booknumber like concat('%', #{target}, '%')")
    List<Book> getBookByNumber(String target);
    @Select("select * from book where description like concat('%', #{target}, '%')")
    List<Book> getBookByDesc(String target);
}

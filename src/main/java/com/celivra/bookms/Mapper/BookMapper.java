package com.celivra.bookms.Mapper;

import com.celivra.bookms.Entity.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {
    @Insert("insert into book(bookname, author, booktype, publisher, booknumber, description, isbn, publishedDate) " +
            "values(#{bookName}, #{author}, #{bookType}, #{publisher}, #{bookNumber}, #{description}, #{ISBN}, #{publishedDate})")
    boolean addBook(Book book);

    @Delete("delete from book where id = #{bookId}")
    boolean deleteBook(String bookId);

    //根据ISBN获得书籍信息
    @Select("select * from book where isbn = #{isbn}")
    Book findBookByISBN(String isbn);

    //根据书籍id获取书籍信息
    @Select("select * from book where id=#{bookId}")
    Book findBookById(String bookId);
    //更新书籍信息
    @Update("update book set id = #{id}, bookname = #{bookName}, author = #{author}," +
            "booktype = #{bookType}, publisher = #{publisher}, booknumber = #{bookNumber}, description = #{description}," +
            "isbn = #{ISBN}, publishedDate = #{publishedDate} " +
            "where id = #{id}")
    boolean updateBookInfo(Book book);
    //获取所有书籍的信息
    @Select("select * from book;")
    List<Book> getAllBooks();

    @Select("select * from book where bookname like concat('%', #{target}, '%') or " +
            "author like concat('%', #{target}, '%') or " +
            "booktype like concat('%', #{target}, '%') or " +
            "publisher like concat('%', #{target}, '%') or " +
            "booknumber like concat('%', #{target}, '%') or " +
            "description like concat('%', #{target}, '%') or " +
            "isbn like concat('%', #{target}, '%')")
    List<Book> findBookByTarget(String target);
}

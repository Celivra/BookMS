package com.celivra.bookms.Entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Book {
    private Long id;//图书id
    private String bookName;//书名
    private String author;//作者
    private String publisher;//出版社
    private String bookType;//书籍类型
    private int bookNumber;//书籍数量
    private String description;//书籍描述
    private String ISBN;
    private LocalDate publishedDate;
}

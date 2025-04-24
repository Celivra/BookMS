package com.celivra.bookms.Entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Book {
    private Long id;
    private String bookName, author, publisher, bookType, description, ISBN;
    private int bookNumber;
    private LocalDate publishedDate;
}

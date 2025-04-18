package com.celivra.bookms.Entity;

public class Book {
    private Long id;//图书id
    private String bookName;//书名
    private String author;//作者
    private String publisher;//出版社
    private String bookType;//书籍类型
    private int bookNumber;//书籍数量
    private String description;//书籍描述

    public Book() {}
    public Book(String bookName, String author, String bookType, String publisher, int bookNumber, String description) {
        this.id = null;
        this.bookName = bookName;
        this.author = author;
        this.publisher = publisher;
        this.bookType = bookType;
        this.bookNumber = bookNumber;
        this.description = description;
    }
    public Book(Long id, String bookName, String author, String bookType, String publisher, int bookNumber, String description) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.publisher = publisher;
        this.bookType = bookType;
        this.bookNumber = bookNumber;
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public void setBookNumber(int bookNumber) {
        this.bookNumber = bookNumber;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getBookType() {
        return bookType;
    }

    public int getBookNumber() {
        return bookNumber;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", bookType='" + bookType + '\'' +
                ", bookNumber=" + bookNumber +
                ", description='" + description + '\'' +
                '}';
    }
}

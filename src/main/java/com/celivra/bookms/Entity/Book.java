package com.celivra.bookms.Entity;

public class Book {
    private Long id;
    private String bookName;
    private String author;
    private String publisher;
    private String bookType;
    private int bookNumber;
    private String description;

    public Book() {}
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

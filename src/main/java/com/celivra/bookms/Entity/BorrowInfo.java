package com.celivra.bookms.Entity;

public class BorrowInfo {
    String bookName, author, borrowDate, returnDate;

    public BorrowInfo() {}
    public BorrowInfo(String bookName, String author, String borrowDate, String returnDate) {
        this.bookName = bookName;
        this.author = author;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    @Override
    public String toString() {
        if(this.returnDate != null) {
            return "《"+this.bookName + "》- 作者:" + this.author + ", 借阅时间:" + this.borrowDate + ", 归还时间：" + this.returnDate;
        }
        return "《"+this.bookName + "》- 作者:" + this.author + ", 借阅时间:" + this.borrowDate + ", 尚未归还";
    }
}

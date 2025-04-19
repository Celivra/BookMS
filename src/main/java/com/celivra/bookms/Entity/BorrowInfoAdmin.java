package com.celivra.bookms.Entity;

public class BorrowInfoAdmin {
    String username;
    String bookName;
    String bookAuthor;
    String borrowDate;
    String returnDate;

    public BorrowInfoAdmin() {}
    public BorrowInfoAdmin(String username, String bookName, String bookAuthor, String borrowDate, String returnDate) {
        this.username = username;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getUsername() {
        return username;
    }

    public String getBookName() {
        return bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    @Override
    public String toString() {
        if(returnDate != null) {
            return "用户:"+username + "#  《"+ bookName + "》- 作者:" + bookAuthor + ",  借阅时间:" + borrowDate + ",  归还时间:" + returnDate;
        }
        return "用户:"+username + "#  《"+ bookName + "》- 作者:" + bookAuthor + ",  借阅时间:" + borrowDate + ",  尚未归还";
    }
}

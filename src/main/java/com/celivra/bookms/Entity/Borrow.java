package com.celivra.bookms.Entity;

public class Borrow {
    Long userid, bookid;//借阅人id、被借的图书id
    String borrowDate, returnDate;//借阅日期，归还日期

    public Borrow(){}
    public Borrow(Long userid, Long bookid, String borrowDate, String returnDate) {
        this.userid = userid;
        this.bookid = bookid;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public void setBookid(Long bookid) {
        this.bookid = bookid;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public Long getUserid() {
        return userid;
    }

    public Long getBookid() {
        return bookid;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "userid=" + userid +
                ", bookid=" + bookid +
                ", borrowDate='" + borrowDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                '}';
    }
}
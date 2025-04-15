package com.celivra.bookms.Entity;

import java.sql.Date;

public class Borrow {
    Long userid, bookid;
    Date borrowDate, returnDate;

    public Borrow() {}
    public Borrow(Long userid, Long bookid, Date borrowDate, Date returnDate) {
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

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Long getUserid() {
        return userid;
    }

    public Long getBookid() {
        return bookid;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    @Override
    public String toString() {
        return "borrow{" +
                "userid=" + userid +
                ", bookid=" + bookid +
                ", borrowDate=" + borrowDate +
                ", returnDate=" + returnDate +
                '}';
    }
}

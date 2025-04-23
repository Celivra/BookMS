package com.celivra.bookms.Entity;

import lombok.Data;

@Data
public class Borrow {
    Long userid, bookid;//借阅人id、被借的图书id
    String borrowDate, returnDate;//借阅日期，归还日期

    public Borrow(Long userid, Long bookid, String borrowDate, String returnDate) {
        this.userid = userid;
        this.bookid = bookid;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }
}
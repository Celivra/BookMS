package com.celivra.bookms.Entity;

import lombok.Data;

@Data
public class BorrowInfoAdmin {
    String username;
    String bookName;
    String bookAuthor;
    String borrowDate;
    String returnDate;

    public BorrowInfoAdmin(String username, String bookName, String bookAuthor, String borrowDate, String returnDate) {
        this.username = username;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        if(returnDate != null) {
            return "用户:"+username + "#  《"+ bookName + "》- 作者:" + bookAuthor + ",  借阅时间:" + borrowDate + ",  归还时间:" + returnDate;
        }
        return "用户:"+username + "#  《"+ bookName + "》- 作者:" + bookAuthor + ",  借阅时间:" + borrowDate + ",  尚未归还";
    }
}

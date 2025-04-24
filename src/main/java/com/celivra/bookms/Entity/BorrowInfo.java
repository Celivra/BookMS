package com.celivra.bookms.Entity;

import lombok.Data;

@Data
public class BorrowInfo {
    private String bookName, author, borrowDate, returnDate;

    public BorrowInfo(String bookName, String author, String borrowDate, String returnDate) {
        this.bookName = bookName;
        this.author = author;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        if(this.returnDate != null) {
            return "《"+this.bookName + "》- 作者:" + this.author + ", 借阅时间:" + this.borrowDate + ", 归还时间：" + this.returnDate;
        }
        return "《"+this.bookName + "》- 作者:" + this.author + ", 借阅时间:" + this.borrowDate + ", 尚未归还";
    }
}

package com.celivra.bookms.Entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Ticket {
    private Long id, userId;
    private String ticketName, ticketRank, content, reply;
    private LocalDate createDate, replyDate;
    private boolean isClosed; //status  false:waiting true:complete

    public boolean isClosed() {
        return isClosed;
    }

    public Ticket(Long id, String ticketName, String ticketRank, String content, String reply, Long userId, LocalDate createDate, LocalDate replyDate, boolean isClosed) {
        this.id = id;
        this.userId = userId;
        this.ticketName = ticketName;
        this.ticketRank = ticketRank;
        this.content = content;
        this.reply = reply;
        this.createDate = createDate;
        this.replyDate = replyDate;
        this.isClosed = isClosed;
    }

    public Ticket(String ordername, String orderrank, String content, Long userid){
        this.ticketName = ordername;
        this.ticketRank = orderrank;
        this.content = content;
        this.userId = userid;
        this.createDate = LocalDate.now();
        this.replyDate = null;
        this.isClosed = false;
    }
}

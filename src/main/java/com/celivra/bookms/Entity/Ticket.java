package com.celivra.bookms.Entity;

import lombok.Data;

@Data
public class Ticket {
    private Long id, userId;
    private String ticketName, ticketRank, content, reply;
    public Ticket(long id , String ticketName, String ticketRank, String content, String reply, Long userId) {
        this.id = id;
        this.ticketName = ticketName;
        this.ticketRank = ticketRank;
        this.content = content;
        this.reply = reply;
        this.userId = userId;
    }
    public Ticket(String ordername, String orderrank, String content, Long userid){
        this.ticketName = ordername;
        this.ticketRank = orderrank;
        this.content = content;
        this.userId = userid;
    }
}

package com.celivra.bookms.Entity;

import lombok.Data;

@Data
public class WorkOrder {
    private Long id, userId;
    private String orderName, orderRank, content, reply;
    public WorkOrder(long id , String orderName, String orderRank, String content, String reply, Long userId) {
        this.id = id;
        this.orderName = orderName;
        this.orderRank = orderRank;
        this.content = content;
        this.reply = reply;
        this.userId = userId;
    }
    public WorkOrder(String ordername, String orderrank, String content, Long userid){
        this.orderName = ordername;
        this.orderRank = orderrank;
        this.content = content;
        this.userId = userid;
    }
}

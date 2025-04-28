package com.celivra.bookms.Entity;

import lombok.Data;

@Data
public class WorkOrder {
    private Long id;
    private String orderName, rank, content, reply;
    public WorkOrder(String orderName, String rank, String content) {
        this.orderName = orderName;
        this.rank = rank;
        this.content = content;
    }
}

package com.celivra.bookms.Service;

import com.celivra.bookms.Entity.Ticket;
import com.celivra.bookms.Mapper.TicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    /*==================实例化Mapper===================*/
    @Autowired
    TicketMapper ticketMapper;
    /*===================实例化结束===================*/

    public boolean addTicket(Ticket ticket) {

        /*========================判断添加的各个工单属性是否为空==============================*/
        if(ticket.getTicketName()== null || ticket.getTicketName().trim().isEmpty() ||
           ticket.getContent() == null || ticket.getContent().trim().isEmpty()) return false;
        /*=================================判断结束=======================================*/

        return ticketMapper.addTicket(ticket);
    }

    public boolean updateTicket(Ticket ticket) {
        return ticketMapper.updateTicket(ticket);
    }

    public Ticket getTicketById(String id) {
        return ticketMapper.getTicketById(id);
    }

    public List<Ticket> getAllTicket(Long userId) {
        return ticketMapper.getAllTicket(userId.toString());
    }

    public List<Ticket> getAllTicket() {
        return ticketMapper.getAllTicket();
    }
}

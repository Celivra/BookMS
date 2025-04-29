package com.celivra.bookms.Service;

import com.celivra.bookms.Entity.Ticket;
import com.celivra.bookms.Mapper.TicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    @Autowired
    TicketMapper ticketMapper;

    public boolean addWorkOrder(Ticket ticket) {
        return ticketMapper.addTicket(ticket);
    }

    public boolean updateWorkOrder(Ticket ticket) {
        return ticketMapper.updateTicket(ticket);
    }

    public List<Ticket> getAllWorkOrders(Long userId) {
        return ticketMapper.getTicket(userId.toString());
    }

    public List<Ticket> getNoReplyWorkOrder() {
        return ticketMapper.getNoReplyTicket();
    }
}

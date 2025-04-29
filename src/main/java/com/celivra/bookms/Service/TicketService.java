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

    public boolean addTicket(Ticket ticket) {
        return ticketMapper.addTicket(ticket);
    }

    public boolean updateTicket(Ticket ticket) {
        return ticketMapper.updateTicket(ticket);
    }

    public List<Ticket> getAllTicket(Long userId) {
        return ticketMapper.getAllTicket(userId.toString());
    }

    public List<Ticket> getNoReplyTicket() {
        return ticketMapper.getNoReplyTicket();
    }
}

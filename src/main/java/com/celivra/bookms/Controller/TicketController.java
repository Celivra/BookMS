package com.celivra.bookms.Controller;

import com.celivra.bookms.Entity.User;
import com.celivra.bookms.Entity.Ticket;
import com.celivra.bookms.Service.TicketService;
import com.fasterxml.jackson.core.TreeCodec;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TicketController {
    @Autowired
    TicketService ticketService;
    @Autowired
    private TreeCodec treeCodec;

    @PostMapping("/addTicket")
    public String addWorkOrder(@RequestParam String ticketName,
                               @RequestParam String ticketRank,
                               @RequestParam String content,
                               HttpServletRequest request, RedirectAttributes reAModel) {

        User user = (User) request.getSession().getAttribute("user");
        Ticket ticket = new Ticket(ticketName, ticketRank, content, user.getId());
        ticketService.addTicket(ticket);
        reAModel.addFlashAttribute("activeSection", "ticket");
        return "redirect:/";
    }

    @PostMapping("/closeTicket")
    public String closeTicket(@RequestParam String id, RedirectAttributes reAModel, HttpServletRequest request) {
        Ticket ticket = ticketService.getTicketById(id);
        User user = (User) request.getSession().getAttribute("user");
        if(!user.getId().equals(ticket.getUserId())) {
            reAModel.addFlashAttribute("CantCloseTicket", "这不是你可以关闭的工单");
        }
        if(!ticket.isClosed()) {
            ticket.setClosed(true);
            ticketService.updateTicket(ticket);
            reAModel.addFlashAttribute("TicketIsClosed", "已经被关闭了");
        }
        reAModel.addFlashAttribute("activeSection", "ticket");
        return "redirect:/";
    }

    @PostMapping("/replyTicket")
    public String replyTicket(@RequestParam String id, @RequestParam String reply, RedirectAttributes reAModel) {

        Ticket ticket = ticketService.getTicketById(id);
        ticket.setReply(reply);
        ticket.setStatus(true);//complete
        ticket.setClosed(true);//closed the ticket
        ticketService.updateTicket(ticket);
        reAModel.addFlashAttribute("activeSection", "ticket");
        return "redirect:/";
    }
}

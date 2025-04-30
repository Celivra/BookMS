package com.celivra.bookms.Controller;

import com.celivra.bookms.Entity.User;
import com.celivra.bookms.Entity.Ticket;
import com.celivra.bookms.Service.TicketService;
import com.celivra.bookms.Util.DateUtil;
import com.fasterxml.jackson.core.TreeCodec;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

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
        if(!ticketService.addTicket(ticket)){
            reAModel.addFlashAttribute("TicketIsNull", "内容不可以为空！");
        }
        reAModel.addFlashAttribute("activeSection", "ticket");
        return "redirect:/";
    }

    @PostMapping("/closeTicket")
    public String closeTicket(@RequestParam String id, RedirectAttributes reAModel, HttpServletRequest request) {
        Ticket ticket = ticketService.getTicketById(id);
        if(ticket == null) {
            reAModel.addFlashAttribute("CantFindTicket", "找不到工单");
            reAModel.addFlashAttribute("activeSection", "ticket");
            return "redirect:/";
        }
        User user = (User) request.getSession().getAttribute("user");
        if(!user.getId().equals(ticket.getUserId())) {
            reAModel.addFlashAttribute("CantCloseTicket", "这不是你可以关闭的工单");
        }
        if(!ticket.isClosed()) {
            ticket.setClosed(true);
            ticket.setStatus(true);
            ticketService.updateTicket(ticket);
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
        ticket.setReplyDate(LocalDate.now());
        ticketService.updateTicket(ticket);
        reAModel.addFlashAttribute("activeSection", "ticket");
        return "redirect:/";
    }
}

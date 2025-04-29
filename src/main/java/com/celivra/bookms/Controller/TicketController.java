package com.celivra.bookms.Controller;

import com.celivra.bookms.Entity.User;
import com.celivra.bookms.Entity.Ticket;
import com.celivra.bookms.Service.TicketService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TicketController {
    @Autowired
    TicketService ticketService;

    @PostMapping("/addTicket")
    public String addWorkOrder(@RequestParam String ticketName,
                               @RequestParam String ticketRank,
                               @RequestParam String content,
                               HttpServletRequest request, RedirectAttributes reAModel) {

        User user = (User) request.getSession().getAttribute("user");
        Ticket ticket = new Ticket(ticketName, ticketRank, content, user.getId());
        ticketService.addWorkOrder(ticket);
        reAModel.addFlashAttribute("activeSection", "ticket");
        return "redirect:/";
    }
}

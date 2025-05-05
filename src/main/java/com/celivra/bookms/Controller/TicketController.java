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

import java.time.LocalDate;

@Controller
public class TicketController {

    /*===========实例化Service对象===============*/
    @Autowired
    private TicketService ticketService;
    /*===============实例化结束=================*/

    @PostMapping("/addTicket")
    public String addWorkOrder(@RequestParam String ticketName,
                               @RequestParam String ticketRank,
                               @RequestParam String content,
                               HttpServletRequest request, RedirectAttributes reAModel) {

        User user = (User) request.getSession().getAttribute("user");

        /*==================================创建工单记录========================================*/
        Ticket ticket = new Ticket(ticketName, ticketRank, content, user.getId());
        /*-------------------------------如果创建成功就添加属性-----------------------------------*/
        if(!ticketService.addTicket(ticket)){
            reAModel.addFlashAttribute("Ticket", "内容不可以为空！");
        }
        /*==================================创建工单结束========================================*/

        reAModel.addFlashAttribute("activeSection", "ticket");
        return "redirect:/";
    }

    @PostMapping("/closeTicket")
    public String closeTicket(@RequestParam String id, RedirectAttributes reAModel, HttpServletRequest request) {
        /*====================为确保安全，首先判断传来的id对应的工单是否存在==========================*/
        Ticket ticket = ticketService.getTicketById(id);
        if(ticket == null) {
            reAModel.addFlashAttribute("Ticket", "找不到工单");
            reAModel.addFlashAttribute("activeSection", "ticket");
            return "redirect:/";
        }

        User user = (User) request.getSession().getAttribute("user");

        /*-----------------------判断当前所关闭的工单是否是该用户所创建的-----------------------------*/
        if(!user.getId().equals(ticket.getUserId())) {
            reAModel.addFlashAttribute("Ticket", "这不是你可以关闭的工单");
        }
        /*=================================安全判断结束=========================================*/



        /*===================================关闭工单==========================================*/
        if(!ticket.isClosed()) {
            ticket.setClosed(true);
            ticketService.updateTicket(ticket);
        }
        /*=================================关闭工单结束========================================*/
        reAModel.addFlashAttribute("activeSection", "ticket");
        return "redirect:/";
    }

    @PostMapping("/replyTicket")
    public String replyTicket(@RequestParam String id, @RequestParam String reply, RedirectAttributes reAModel) {

        /*==================根据id获取工单，将工单的reply设置为传来的reply=========================*/
        Ticket ticket = ticketService.getTicketById(id);
        ticket.setReply(reply);
        ticket.setClosed(true);//closed the ticket
        ticket.setReplyDate(LocalDate.now());
        ticketService.updateTicket(ticket);
        /*================================回复结束============================================*/

        reAModel.addFlashAttribute("activeSection", "ticket");
        return "redirect:/";
    }
}

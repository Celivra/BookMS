package com.celivra.bookms.Controller;

import com.celivra.bookms.Entity.*;
import com.celivra.bookms.Service.BookService;
import com.celivra.bookms.Service.BorrowService;
import com.celivra.bookms.Service.UserService;
import com.celivra.bookms.Service.TicketService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

//页面跳转
@Controller
public class PageController {

    /*===========实例化Service对象===============*/
    @Autowired
    private BookService bookService;
    @Autowired
    private BorrowService borrowService;
    @Autowired
    private UserService userService;
    @Autowired
    private TicketService ticketService;
    /*===============实例化结束=================*/

    //控制台页面
    @GetMapping("/")
    public String dashboard(HttpServletRequest req, Model model) {

        /*======================获取所有图书===========================*/
        List<Book> bookList = bookService.getAllBooks();
        model.addAttribute("books", bookList);
        /*====================获取所有图书结束==========================*/

        User user = (User) req.getSession().getAttribute("user");

        /*======================根据当前用户判断是否为管理员用户,并添加属性===========================*/
        if(user == null){
            List<User> userList = userService.getAllUsers();
            List<BorrowInfoAdmin> borrowInfoAdmins = borrowService.getAllBorrows();
            List<Ticket> ticketList = ticketService.getAllTicket();
            model.addAttribute("users", userList);
            model.addAttribute("borrowInfo", borrowInfoAdmins);
            model.addAttribute("tickets", ticketList);
            return "admin";
        }
        /*---------------------若不是管理员用户则添加普通用户页面需要的属性-------------------------*/
        List<Book> userbooks = borrowService.getUserBorrowedBooks(user.getId().toString());
        List<BorrowInfo> borrowInfos = borrowService.getAllUserBorrows(user.getId().toString());
        List<Ticket> ticketList = ticketService.getAllTicketByUserId(user.getId());
        model.addAttribute("tickets", ticketList);
        model.addAttribute("borrowInfo", borrowInfos);
        model.addAttribute("userBooks", userbooks);
        return "dashboard";
        /*=================================添加属性结束======================================*/
    }
    //登入界面
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    //注册界面
    @GetMapping("/register")
    public String Register(){
        return "register";
    }

    //登出
    @GetMapping("/logout")
    public String logout(HttpServletRequest req){

        /*======================如果存在xx属性，就将xx设为null===========================*/
        if(req.getSession().getAttribute("admin") != null){
            req.getSession().setAttribute("admin", null);
        }
        if(req.getSession().getAttribute("user") != null){
            req.getSession().setAttribute("user", null);
        }
        return "redirect:/";
        /*================================清除结束===================================*/
    }
}

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

    @Autowired
    BookService bookService;
    @Autowired
    BorrowService borrowService;
    @Autowired
    UserService userService;
    @Autowired
    private TicketService ticketService;

    //控制台页面
    @GetMapping("/")
    public String dashboard(HttpServletRequest req, Model model) {
        //加载/页面时获取所有书籍的信息
        List<Book> bookList = bookService.getAllBooks();
        model.addAttribute("books", bookList);
        //  如果是admin用户则跳转到admin的页面
        if(req.getSession().getAttribute("admin") != null){
            List<User> userList = userService.getAllUsers();
            List<BorrowInfoAdmin> borrowInfoAdmins = borrowService.getAllBorrows();
            model.addAttribute("users", userList);
            model.addAttribute("borrowInfo", borrowInfoAdmins);
            return "admin";
        }

        //获取当前用户的信息
        User user = (User) req.getSession().getAttribute("user");
        //将当前用户所借到的书全部读取下来
        List<Book> userbooks = borrowService.getUserBorrowedBooks(user.getId().toString());
        //获取当前用户的所有借阅信息
        List<BorrowInfo> borrowInfos = borrowService.getAllUserBorrows(user.getId().toString());
        List<Ticket> ticketList = ticketService.getAllWorkOrders(user.getId());

        //将获取到的所有数据放到model里，供前端调用
        model.addAttribute("WorkOrder", ticketList);
        model.addAttribute("borrowInfo", borrowInfos);
        model.addAttribute("userbooks", userbooks);
        return "dashboard";
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
        //首先判断session里是否存在admin或者user的attribute，如果存在就设为null
        if(req.getSession().getAttribute("admin") != null){
            req.getSession().setAttribute("admin", null);
        }
        if(req.getSession().getAttribute("user") != null){
            req.getSession().setAttribute("user", null);
        }
        return "redirect:/";
    }

}

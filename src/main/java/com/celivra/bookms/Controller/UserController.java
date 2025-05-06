package com.celivra.bookms.Controller;

import com.celivra.bookms.Entity.Book;
import com.celivra.bookms.Entity.BorrowInfoAdmin;
import com.celivra.bookms.Entity.Ticket;
import com.celivra.bookms.Entity.User;
import com.celivra.bookms.Service.BookService;
import com.celivra.bookms.Service.BorrowService;
import com.celivra.bookms.Service.TicketService;
import com.celivra.bookms.Service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

//有关提交的接口
@Controller
public class UserController {

    /*===========实例化Service对象===============*/
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;
    @Autowired
    private BorrowService borrowService;
    @Autowired
    private TicketService ticketService;
    /*===============实例化结束=================*/

    //登入操作
    @PostMapping("/doLogin")
    public String login( @RequestParam String username, @RequestParam String password,
                         HttpServletRequest request, RedirectAttributes reAttributes) {

        User user = userService.getByUsername(username);


        /*===========================验证获取到的用户================================*/
        if(user == null){
            reAttributes.addFlashAttribute("doLogin", "没有这个用户!");
            return "redirect:/login";
        }
        if(!user.getPassword().equals(password)) {
            reAttributes.addFlashAttribute("doLogin", "密码错误!");
            return "redirect:/login";
        }
        /*==============================验证结束===================================*/


        /*==========================判断是否为管理员账户==============================*/
        String userAttribute = (user.getPower() == 10)?"admin":"user";
        request.getSession().setAttribute(userAttribute, user);
        /*==============================判断结束====================================*/

        return "redirect:/";
    }

    //注册操作
    @PostMapping("/doRegister")
    public String doRegister(@ModelAttribute User user, RedirectAttributes reAttributes) {

        /*==================将传来的user传给userService进行处理========================*/
        int returnValue = userService.addUser(user);
        /*-------------------------根据返回值添加属性---------------------------------*/
        String key = "doRegister";
        String value = returnValue == 1? "注册成功":"用户已存在";
        reAttributes.addFlashAttribute(key, value);
        /*===========================添加用户结束==================================*/

        return "redirect:/register";
    }

    //更新用户
    @PostMapping("/updateUser")
    public String updateUser(
            /*===========================传来的参数有些可以没有==================================*/
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String newPassword,
            @RequestParam(required = false) String newPhone,
            @RequestParam(required = false) String newEmail,
            @RequestParam(required = false, defaultValue = "false") boolean newPower,
            /*===============================接受传参结束=====================================*/
            HttpServletRequest request,
            RedirectAttributes reAttributes) {
        User user;

        /*========================根据有无userId来选择获取用户信息的方式==========================*/
        if(userId == null){
            user = (User) request.getSession().getAttribute("user");
        }else{
            user = userService.getByUserId(userId);
        }
        /*==================================获取用户信息结束===================================*/


        /*============================判断每个参数是否有传参然后赋值==============================*/
        if(newPassword != null) user.setPassword(newPassword);
        if(newPhone != null) user.setPhone(newPhone);
        if(newEmail != null) user.setEmail(newEmail);
        user.setPower(newPower?10:0);
        /*===================================赋新值结束=======================================*/


        /*==================================更新用户数据======================================*/
        if(userService.updateUser(user)){
            reAttributes.addFlashAttribute("UpdateUser", "更新成功");
        }else{
            reAttributes.addFlashAttribute("UpdateUser", "更新失败");
        }
        /*====================================更新结束=======================================*/

        /*============================检测是否更新密码，退出登入=================================*/
        if(newPassword != null){
            request.getSession().removeAttribute("user");
            reAttributes.addFlashAttribute("UpdatePasswd", "检测到密码更改，请重新登入。");
            return "redirect:/login";
        }
        /*=================================退出登入结束=======================================*/


        User thisUser = (User)request.getSession().getAttribute("user");

        /*================根据用户类型，添加不同的id属性，以供前端打开相对应的div====================*/
        if(thisUser != null){
            reAttributes.addFlashAttribute("activeSection", "profile");
        }else{
            reAttributes.addFlashAttribute("activeSection", "users");
        }
        /*=================================添加属性结束======================================*/

        return "redirect:/";
    }

    @PostMapping("/deleteUser")
    public String deleteUser( @RequestParam String userId, RedirectAttributes reAModel) {
        userService.deleteUser(userId);
        reAModel.addFlashAttribute("activeSection", "users");
        return "redirect:/";
    }
    //更新admin密码
    @PostMapping("/changeAdminPassword")
    public String changeAdminPassword(@RequestParam String newPasswd, HttpServletRequest request, RedirectAttributes reAttributes) {
        User user = (User) request.getSession().getAttribute("admin");

        /*=======================修改密码，并将新的user添加到属性==============================*/
        user.setPassword(newPasswd);
        userService.updateUser(user);
        /*================================修改密码结束=====================================*/
        request.getSession().removeAttribute("admin");
        reAttributes.addFlashAttribute("UpdatePasswd", "检测到密码更改，请重新登入。");
        return "redirect:/login";
    }

    @GetMapping("/getUsers")
    public String getBooks(@RequestParam(value = "target",required = false) String target, HttpServletRequest request, Model model) {

        /*=============================根据target获取用户===================================*/
        List<User> userList= null;
        if(target != null) userList= userService.searchUser(target);
        else userList = userService.getAllUsers();
        /*================================获取用户结束=====================================*/


        /*===========================将admin所需要的属性添加================================*/
        List<Book> books = bookService.getAllBooks();
        List<BorrowInfoAdmin> borrowInfoAdmins = borrowService.getAllBorrows();
        List<Ticket> ticketList = ticketService.getAllTicket();
        model.addAttribute("users", userList);
        model.addAttribute("borrowInfo", borrowInfoAdmins);
        model.addAttribute("books", books);
        model.addAttribute("tickets", ticketList);
        model.addAttribute("activeSection", "users");
        /*===============================属性添加结束=====================================*/
        return "admin";
    }
}

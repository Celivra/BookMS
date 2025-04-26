package com.celivra.bookms.Controller;

import com.celivra.bookms.Entity.Book;
import com.celivra.bookms.Entity.BorrowInfoAdmin;
import com.celivra.bookms.Entity.User;
import com.celivra.bookms.Service.BookService;
import com.celivra.bookms.Service.BorrowService;
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
    @Autowired
    UserService userService;
    @Autowired
    BookService bookService;
    @Autowired
    BorrowService borrowService;

    //登入操作
    @PostMapping("/doLogin")
    public String login( @RequestParam String username, @RequestParam String password,
            HttpServletRequest request, RedirectAttributes reAttributes) {

        //根据用户名获取用户数据
        User user = userService.findByUsername(username);
        //判断有无这个用户或者密码是否匹配
        if(user != null && user.getPassword().equals(password)) {
            //判断是否为管理员账户, 创建不同的attribute
            if(user.getPower() == 10){
                request.getSession().setAttribute("admin", user);
            }else {
                request.getSession().setAttribute("user", user);
            }
            //重定向到/目录
            return "redirect:/";
        }
        //若没有进入if里，则密码错误 创建passworderror错误
        reAttributes.addFlashAttribute("PasswordError", true);
        //重定向到login页面
        return "redirect:/login";
    }

    //注册操作
    @PostMapping("/doRegister")
    public String doRegister(@ModelAttribute User user, RedirectAttributes reAttributes) {
        if(userService.addUser(user) == 1){
            reAttributes.addFlashAttribute("RegSuccess", "注册成功");
        }else if(userService.addUser(user) == 2){
            reAttributes.addFlashAttribute("RegUserIsExist", "用户已存在");
        }
        return "redirect:/register";
    }

    //更新用户
    @PostMapping("/updateUser")
    public String updateUser(
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String newPassword,
            @RequestParam(required = false) String newPhone,
            @RequestParam(required = false) String newEmail,
            @RequestParam(required = false, defaultValue = "false") boolean newPower,
            HttpServletRequest request,
            RedirectAttributes reAttributes) {
        User user;
        if(userId == null){
            user = (User) request.getSession().getAttribute("user");
        }else{
            user = userService.findByUserId(userId);
        }
        //如果newPassword为null则认为没有传password，所以不设置password
        //其他的一样
        if(newPassword != null) user.setPassword(newPassword);
        if(newPhone != null) user.setPhone(newPhone);
        if(newEmail != null) user.setEmail(newEmail);
        user.setPower(newPower?10:0);
        userService.updateUser(user);
        if(newPassword != null){
            request.getSession().removeAttribute("user");
            reAttributes.addFlashAttribute("UpdatePasswd", "检测到密码更改，请重新登入。");
            return "redirect:/login";
        }
        return "redirect:/";
    }

    @PostMapping("/deleteUser")
    public String deleteUser( @RequestParam String userId ){
        userService.deleteUser(userId);
        return "redirect:/";
    }
    //更新admin密码
    @PostMapping("/changeAdminPassword")
    public String changeAdminPassword(@RequestParam String newPasswd, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("admin");
        user.setPassword(newPasswd);
        if(userService.updateUser(user)) {
            request.getSession().setAttribute("admin", user);
        }
        return "redirect:/";
    }

    @GetMapping("/getUsers")
    public String getBooks(@RequestParam(value = "target",required = false) String target, HttpServletRequest request, Model model) {
        //根据target对books的各个字段进行查找
        List<User> userList= null;
        if(target != null) userList= userService.searchUser(target);
        else userList = userService.getAllUsers();

        // 获取当前用户的信息
        User user = (User) request.getSession().getAttribute("user");
        // 获取当前用户所借的书
        if(user != null){
            List<Book> userbooks = borrowService.getUserBorrowedBooks(user.getId().toString());
            model.addAttribute("userbooks", userbooks);
            return "dashboard";
        }

        List<Book> books = bookService.getAllBooks();
        List<BorrowInfoAdmin> borrowInfoAdmins = borrowService.getAllBorrows();
        model.addAttribute("users", userList);
        model.addAttribute("borrowInfo", borrowInfoAdmins);
        model.addAttribute("activeSection", "users");
        model.addAttribute("books", books);
        return "admin";
    }
}

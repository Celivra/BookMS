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
        User user = userService.getByUsername(username);

        if(user == null){
            reAttributes.addFlashAttribute("NoUsername", "没有这个用户!");
            return "redirect:/login";
        }
        if(!user.getPassword().equals(password)) {
            reAttributes.addFlashAttribute("PasswordError", "密码错误!");
            return "redirect:/login";
        }

        //判断是否为管理员账户, 创建不同的attribute
        if(user.getPower() == 10){
            request.getSession().setAttribute("admin", user);
        }else {
            request.getSession().setAttribute("user", user);
        }
        //重定向到/目录
        return "redirect:/";
    }

    //注册操作
    @PostMapping("/doRegister")
    public String doRegister(@ModelAttribute User user, RedirectAttributes reAttributes) {
        int returnValue = userService.addUser(user);
        String key = returnValue==1?"RegSuccess":"RegUserIsExist";
        String value = returnValue == 1? "注册成功":"用户已存在";
        reAttributes.addFlashAttribute(key, value);
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
            user = userService.getByUserId(userId);
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
        //获取一下user属性，看看是不是普通用户调用的这个controller
        User thisUser = (User)request.getSession().getAttribute("user");
        if(thisUser != null){
            reAttributes.addFlashAttribute("activeSection", "profile");
        }else{
            reAttributes.addFlashAttribute("activeSection", "users");
        }
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
